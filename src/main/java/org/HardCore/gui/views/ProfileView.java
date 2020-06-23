package org.HardCore.gui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import org.HardCore.gui.components.TopPanel;
import org.HardCore.gui.ui.MyUI;
import org.HardCore.gui.windows.DeleteProfileWindow;
import org.HardCore.model.objects.dto.Student;
import org.HardCore.model.objects.dto.Unternehmen;
import org.HardCore.model.objects.dto.User;
import org.HardCore.process.control.ProfileControl;
import org.HardCore.services.util.Roles;

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

        //Felder Student erzeugen
        final NativeSelect<String> anrede = new NativeSelect<>("Anrede");
        anrede.setItems("Herr", "Frau");
        final TextField vorname = new TextField("Vorname");
        TextField name = new TextField("Name");
        TextField hochschule = new TextField("Hochschule");
        TextField semester = new TextField("Semester");
        DateField gebDatum = new DateField("Geburtsdatum");
        TextField kenntnisse = new TextField("Kenntnisse");
        TextField studiengang = new TextField("Studiengang");
        //Upload profilbild = new Upload("Profilbild", reciever);
        Label meinProfil = new Label("Mein Profil");

        //Felder Unternehmen erzeugen
        TextField firmenname = new TextField("Firmenname");
        TextField ansprechpartner = new TextField("Ansprechpartner");
        TextField strasse = new TextField("Strasse");
        TextField plz = new TextField("Plz");
        TextField haus_nr = new TextField("Hausnummer");
        TextField zusatz = new TextField("Zusatz");
        TextField ort = new TextField("Ort");
        TextField branche = new TextField("Branche");
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
            //Unternehmen unternehmen = new Unternehmen(user);
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

        Button overwriteBtn = new Button("Daten aktualisieren");

        //Event Nutzer löschen
        Button deleteButton = new Button("Profil löschen");
        deleteButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                UI.getCurrent().addWindow(new DeleteProfileWindow());
            }
        });

        //Event Nutzerdaten updaten
        overwriteBtn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                //UI.getCurrent().addWindow(new ConfirmationWindow("Sollen alle Daten aktualisiert werden?"));

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

                    boolean result = ProfileControl.getInstance().updateStudentData(student);
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
                    boolean result = ProfileControl.getInstance().updateUnternehmenData(unternehmen);
                    if (result != true) {
                        Notification.show("Es ist ein Fehler aufgetreten. Bitte versuchen Sie es erneut!", Notification.Type.ERROR_MESSAGE);
                    }

                }
            }
        });

        if (user.hasRole(Roles.STUDENT)) {
            this.addComponent(meinProfil);
            this.addComponent(anrede);
            this.addComponent(vorname);
            this.addComponent(name);
            this.addComponent(hochschule);
            this.addComponent(semester);
            this.addComponent(gebDatum);
            this.addComponent(kenntnisse);
            this.addComponent(studiengang);
            this.addComponent(overwriteBtn);
            this.addComponent(deleteButton);
        } else {
            this.addComponent(meinUnternehmen);
            this.addComponent(firmenname);
            this.addComponent(ansprechpartner);
            this.addComponent(strasse);
            this.addComponent(plz);
            this.addComponent(haus_nr);
            this.addComponent(zusatz);
            this.addComponent(ort);
            this.addComponent(branche);
            this.addComponent(overwriteBtn);
            this.addComponent(deleteButton);
        }
    }
}
