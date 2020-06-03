package org.HardCore.gui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import javafx.scene.control.DatePicker;
import org.HardCore.gui.components.TopPanel;
import org.HardCore.gui.ui.MyUI;
import org.HardCore.gui.windows.ConfirmationWindow;
import org.HardCore.model.objects.dto.Student;
import org.HardCore.model.objects.dto.Unternehmen;
import org.HardCore.model.objects.dto.User;
import org.HardCore.process.control.ProfileControl;
import org.HardCore.process.control.exceptions.DatabaseException;
import org.HardCore.services.util.Roles;

import java.time.LocalDate;

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

        //Felder erzeugen
        TextField userName = new TextField("Name");
        TextField userVorname = new TextField("Vorname");
        NativeSelect<String> userAnrede = new NativeSelect<>("Anrede");
        userAnrede.setItems("Herr", "Frau");
        DateField userGebDatum = new DateField("Geburtsdatum");
        Label meinProfil = new Label("Mein Profil");

        if(user.hasRole(Roles.STUDENT)){
            Student student = new Student(user);
            userName.setValue(student.getAnrede());
            userVorname.setValue(student.getVorname());
            userAnrede.setValue(student.getAnrede());
        } else {
            Unternehmen unternehmen = new Unternehmen(user);
            userName.setCaption("Unternehmensname");
            userName.setValue(unternehmen.getName());
        }

        Button overwriteBtn = new Button("Daten aktualisieren");

        //Event Nutzer löschen
        Button deleteButton = new Button("Profil löschen");
        deleteButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                UI.getCurrent().addWindow(new ConfirmationWindow("Sind Sie sicher, dass Sie Ihr Profil löschen wollen? Dieser Vorgang ist endgültig!"));
            }
        });

        //Event Nutzerdaten updaten
        overwriteBtn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                UI.getCurrent().addWindow(new ConfirmationWindow("Sollen alle Daten überschrieben werden?"));

                if(user.hasRole(Roles.STUDENT)) {
                    String anrede = userAnrede.getValue();
                    String name = userName.getValue();
                    String vorname = userVorname.getValue();

                    try {
                        ProfileControl.updateStudentData((Student) user, anrede, name, vorname);
                    } catch (DatabaseException e) {
                        Notification.show("DB-Fehler!", e.getReason(), Notification.Type.ERROR_MESSAGE);
                    }
                } else {
                    String firmenname = userName.getValue();

                    try{
                        ProfileControl.updateUnternehmenData((Unternehmen) user, firmenname);
                    } catch (DatabaseException e) {
                        Notification.show("DB-Fehler!", e.getReason(), Notification.Type.ERROR_MESSAGE);
                    }
                }
            }
        });

        if(user.hasRole(Roles.STUDENT)) {
            this.addComponent(meinProfil);
            this.addComponent(userAnrede);
            this.addComponent(userName);
            this.addComponent(userVorname);
            this.addComponent(userGebDatum);
            this.addComponent(overwriteBtn);
            this.addComponent(deleteButton);
        } else if(user.hasRole(Roles.UNTERNEHMEN)){
            meinProfil.setValue("Mein Unternehmensprofil");
            this.addComponent(meinProfil);
            this.addComponent(userName);
            this.addComponent(overwriteBtn);
            this.addComponent(deleteButton);
        }
    }
}
