package org.HardCore.gui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import org.HardCore.gui.components.TopPanel;
import org.HardCore.gui.ui.MyUI;
import org.HardCore.gui.windows.ConfirmationWindow;
import org.HardCore.gui.windows.DeleteProfileWindow;
import org.HardCore.model.objects.dto.Student;
import org.HardCore.model.objects.dto.Unternehmen;
import org.HardCore.model.objects.dto.User;
import org.HardCore.process.control.ProfileControl;
import org.HardCore.services.util.Roles;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ProfileView extends VerticalLayout implements View {

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        User user = ((MyUI) UI.getCurrent()).getUser();
        this.setUp();
    }

    private void setUp() {
        //Top Layer
        this.addComponent(new TopPanel());
        Label line = new Label("<hr>", ContentMode.HTML);
        this.addComponent(line);
        line.setSizeFull();

        User user = ((MyUI) UI.getCurrent()).getUser();

        //TODO alle Felder müssen eingabencheck haben für fehleingaben
        //TODO keine Textfelder bei Hausnummern
        //Felder Student erzeugen
        final NativeSelect<String> anrede = new NativeSelect<>("Anrede");
        anrede.setItems("Herr", "Frau");

        final TextField vorname = new TextField("Vorname");
        vorname.setPlaceholder("Max");

        TextField name = new TextField("Name");
        name.setPlaceholder("Mustermann");

        TextField hochschule = new TextField("Hochschule");
        hochschule.setPlaceholder("HBRS");

        TextField semester = new TextField("Semester");
        semester.setPlaceholder("1");

        DateField gebDatum = new DateField("Geburtsdatum");
        gebDatum.setValue(LocalDate.now());

        TextField kenntnisse = new TextField("Kenntnisse");
        kenntnisse.setPlaceholder("Java, SQL");

        TextField studiengang = new TextField("Studiengang");
        studiengang.setPlaceholder("Informatik");
        //Upload profilbild = new Upload("Profilbild", reciever);

        Label meinProfil = new Label("Mein Profil");

        //Felder Unternehmen erzeugen
        TextField firmenname = new TextField("Firmenname");
        firmenname.setPlaceholder("Firma Exa");

        TextField ansprechpartner = new TextField("Ansprechpartner");
        ansprechpartner.setPlaceholder("Herr Max Mustermann");

        TextField strasse = new TextField("Strasse");
        strasse.setPlaceholder("Beispielweg");

        TextField haus_nr = new TextField("Hausnummer");
        haus_nr.setPlaceholder("1");

        TextField zusatz = new TextField("Zusatz");
        zusatz.setPlaceholder("a");

        TextField ort = new TextField("Ort");
        ort.setPlaceholder("Bonn");

        TextField plz = new TextField("Plz");
        plz.setPlaceholder("53123");

        TextField branche = new TextField("Branche");
        branche.setPlaceholder("IT");

        Label meinUnternehmen = new Label("Mein Unternehmensprofil");

        if (user.hasRole(Roles.STUDENT)) {
            //Werte einsetzen
            Student student = ProfileControl.getInstance().getStudent(user);
            if (student.getAnrede() != null) {
                anrede.setValue(student.getAnrede());
            }
            if (student.getVorname() != null) {
                vorname.setValue(student.getVorname());
            }
            if (student.getName() != null) {
                name.setValue(student.getName());
            }
            if (student.getHochschule() != null) {
                hochschule.setValue(student.getHochschule());
            }
            if (student.getSemester() != null) {
                semester.setValue(String.valueOf(student.getSemester()));
            }
            if (student.getGebDatum() != null) {
                gebDatum.setValue(student.getGebDatum());
            }
            if (student.getKenntnisse() != null) {
                kenntnisse.setValue(student.getKenntnisse());
            }
            if (student.getStudiengang() != null) {
                studiengang.setValue(student.getStudiengang());
            }
        } else {
            //Werte Setzen
            Unternehmen unternehmen = ProfileControl.getInstance().getUnternehmen(user);
            if (unternehmen.getName() != null) {
                firmenname.setValue(unternehmen.getName());
            }
            if (unternehmen.getAnsprechpartner() != null) {
                ansprechpartner.setValue(unternehmen.getAnsprechpartner());
            }
            if (unternehmen.getStrasse() != null) {
                strasse.setValue(unternehmen.getStrasse());
            }
            if (unternehmen.getPlz() != null) {
                plz.setValue(String.valueOf(unternehmen.getPlz()));
            }
            if (unternehmen.getHaus_nr() != null) {
                haus_nr.setValue(String.valueOf(unternehmen.getHaus_nr()));
            }
            if (unternehmen.getZusatz() != null) {
                zusatz.setValue(unternehmen.getZusatz());
            }
            if (unternehmen.getOrt() != null) {
                ort.setValue(unternehmen.getOrt());
            }
            if (unternehmen.getBranche() != null) {
                branche.setValue(unternehmen.getBranche());
            }
        }

        //Event Nutzer löschen
        Button deleteButton = new Button("Profil löschen");
        deleteButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                UI.getCurrent().addWindow(new DeleteProfileWindow());
            }
        });

        //Event Nutzerdaten updaten
        Button overwriteBtn = new Button("Daten aktualisieren");
        overwriteBtn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                //UI.getCurrent().addWindow(new ConfirmationWindow("Sollen alle Daten aktualisiert werden?"));
                boolean result = false;
                if (user.hasRole(Roles.STUDENT)) {
                    Student student = new Student(user);
                    student.setAnrede(anrede.getValue());
                    student.setVorname(vorname.getValue());
                    student.setName(name.getValue());
                    student.setHochschule(hochschule.getValue());
                    student.setSemester(Integer.valueOf(semester.getValue()));
                    student.setGebDatum(gebDatum.getValue());
                    student.setKenntnisse(kenntnisse.getValue());
                    student.setStudiengang(studiengang.getValue());

                    result = ProfileControl.getInstance().updateStudentData(student);
                    if (result != true) {
                        Notification.show("Es ist ein Fehler aufgetreten. Bitte versuchen Sie es erneut!", Notification.Type.ERROR_MESSAGE);
                    }

                } else {
                    Unternehmen unternehmen = new Unternehmen(user);
                    unternehmen.setName(firmenname.getValue());
                    unternehmen.setAnsprechpartner(ansprechpartner.getValue());
                    unternehmen.setStrasse(strasse.getValue());
                    unternehmen.setPlz(Integer.valueOf(plz.getValue()));
                    unternehmen.setHaus_nr(Integer.valueOf(haus_nr.getValue()));
                    unternehmen.setZusatz(zusatz.getValue());
                    unternehmen.setBranche(branche.getValue());
                    unternehmen.setOrt(ort.getValue());
                    result = ProfileControl.getInstance().updateUnternehmenData(unternehmen);
                    if (result != true) {
                        Notification.show("Es ist ein Fehler aufgetreten. Bitte versuchen Sie es erneut!", Notification.Type.ERROR_MESSAGE);
                    }

                }
                if (result) {
                    UI.getCurrent().addWindow(new ConfirmationWindow("Ihr Profil wurde erfolgreich aktualisiert!"));
                }
            }
        });

        // Horizontal Strasse
        HorizontalLayout horizontalLayoutStrasse = new HorizontalLayout();
        horizontalLayoutStrasse.addComponent(strasse);
        horizontalLayoutStrasse.addComponent(haus_nr);
        horizontalLayoutStrasse.addComponent(zusatz);

        //Horizontal Ort
        HorizontalLayout horizontalLayoutOrt = new HorizontalLayout();
        horizontalLayoutOrt.addComponent(ort);
        horizontalLayoutOrt.addComponent(plz);

        //Horizontal Name
        HorizontalLayout horizontalLayoutName = new HorizontalLayout();
        horizontalLayoutName.addComponent(vorname);
        horizontalLayoutName.addComponent(name);

        //horizontal Uni
        HorizontalLayout horizontalLayoutUni = new HorizontalLayout();
        horizontalLayoutUni.addComponent(hochschule);
        horizontalLayoutUni.addComponent(studiengang);
        horizontalLayoutUni.addComponent(semester);

        if (user.hasRole(Roles.STUDENT)) {
            this.addComponent(meinProfil);
            this.addComponent(anrede);
            this.addComponent(horizontalLayoutName);
            this.addComponent(horizontalLayoutUni);
            this.addComponent(kenntnisse);
            this.addComponent(gebDatum);
            this.addComponent(overwriteBtn);
            this.addComponent(deleteButton);
        } else {
            this.addComponent(meinUnternehmen);
            this.addComponent(firmenname);
            this.addComponent(ansprechpartner);
            this.addComponent(horizontalLayoutStrasse);
            this.addComponent(horizontalLayoutOrt);
            this.addComponent(branche);
            this.addComponent(overwriteBtn);
            this.addComponent(deleteButton);
        }
    }
}
