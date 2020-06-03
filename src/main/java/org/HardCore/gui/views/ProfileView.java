package org.HardCore.gui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.sass.internal.parser.function.DarkenFunctionGenerator;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import org.HardCore.gui.components.TopPanel;
import org.HardCore.gui.ui.MyUI;
import org.HardCore.gui.windows.ConfirmationWindow;
import org.HardCore.model.objects.dto.Student;
import org.HardCore.model.objects.dto.Unternehmen;
import org.HardCore.model.objects.dto.User;
import org.HardCore.process.control.ProfileControl;
import org.HardCore.process.control.exceptions.DatabaseException;
import org.HardCore.services.util.Roles;
import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class ProfileView extends VerticalLayout implements View {

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        User user = ( (MyUI) UI.getCurrent() ).getUser();
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

        if(user.hasRole(Roles.STUDENT)){
            //Werte einsetzen
            Student student = new Student(user);
            anrede.setValue(student.getAnrede());
            vorname.setValue(student.getVorname());
            name.setValue(student.getAnrede());
            hochschule.setValue(student.getHochschule());
            semester.setValue(String.valueOf(student.getSemester()));
            gebDatum.setValue(student.getGebDatum());
            kenntnisse.setValue(student.getKenntnisse());
            studiengang.setValue(student.getStudiengang());
            //Restlichen Werte default setzen

        } else {
            //Werte Setzen
           /* Unternehmen unternehmen = new Unternehmen(user);
            firmenname.setValue(unternehmen.getFirmenname());
            ansprechpartner.setValue(unternehmen.getAnsprechpartner());
            strasse.setValue(unternehmen.getStrasse());
            plz.setValue(unternehmen.getPlz());
            haus_nr.setValue(unternehmen.getHaus_nr());
            zusatz.setValue(unternehmen.getZusatz());
            ort.setValue(unternehmen.getOrt());
            branche.setValue(unternehmen.getBranche());
            //Restlichen Werte default setzen*/

        }

        Button overwriteBtn = new Button("Daten aktualisieren");

        //Event Nutzer löschen
        Button deleteButton = new Button("Profil löschen");
        deleteButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                UI.getCurrent().addWindow(new ConfirmationWindow("Sind Sie sicher, dass Sie Ihr Profil löschen wollen? Dieser Vorgang ist endgültig!"));
                ProfileControl.deleteUser(user);
            }
        });

        //Event Nutzerdaten updaten
        overwriteBtn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                UI.getCurrent().addWindow(new ConfirmationWindow("Sollen alle Daten überschrieben werden?"));

                if(user.hasRole(Roles.STUDENT)) {
                    String new_anrede = anrede.getValue();
                    String new_vorname = vorname.getValue();
                    String new_name = name.getValue();
                    String new_hochschule = hochschule.getValue();
                    String new_semester = semester.getValue();
                    LocalDate new_gebDatum = gebDatum.getValue();
                    String new_kenntnisse = kenntnisse.getValue();
                    String new_studiengang = studiengang.getValue();
                    Student student = new Student(user);
                    try {
                        ProfileControl.updateStudentData(student, new_anrede, new_vorname, new_name, new_hochschule,new_semester, new_gebDatum, new_kenntnisse, new_studiengang);
                    } catch (DatabaseException e) {
                        Notification.show("DB-Fehler!", e.getReason(), Notification.Type.ERROR_MESSAGE);
                    }
                } else {
                    String new_firmenname = firmenname.getValue();
                    Unternehmen unternehmen = new Unternehmen(user);
                    try{
                        ProfileControl.updateUnternehmenData(unternehmen, new_firmenname);
                    } catch (DatabaseException e) {
                        Notification.show("DB-Fehler!", e.getReason(), Notification.Type.ERROR_MESSAGE);
                    }
                }
            }
        });

        if(user.hasRole(Roles.STUDENT)) {
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
        } else if(user.hasRole(Roles.UNTERNEHMEN)){
            meinProfil.setValue("Mein Unternehmensprofil");
            this.addComponent(meinProfil);
            this.addComponent(firmenname);
            this.addComponent(overwriteBtn);
            this.addComponent(deleteButton);
        }
    }
}
