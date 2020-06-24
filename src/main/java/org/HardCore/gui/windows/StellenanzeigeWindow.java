package org.HardCore.gui.windows;

import com.vaadin.ui.*;
import org.HardCore.model.objects.dto.StellenanzeigeDetail;
import org.HardCore.model.objects.dto.Student;
import org.HardCore.model.objects.dto.User;
import org.HardCore.process.control.BewerbungControl;
import org.HardCore.services.util.Roles;

import java.time.format.DateTimeFormatter;

public class StellenanzeigeWindow extends Window {

    public StellenanzeigeWindow(StellenanzeigeDetail stellenanzeige, User user) {
        super(stellenanzeige.getName());
        center();

        //Name
        TextField name = new TextField("Titel");
        name.setValue(stellenanzeige.getName());
        name.setReadOnly(true);

        //Art
        TextField art = new TextField("Art");
        art.setValue(stellenanzeige.getArt());
        art.setReadOnly(true);

        //Branche
        TextField branche = new TextField("Branche");
        branche.setValue(stellenanzeige.getBranche());
        branche.setReadOnly(true);

        //Studiengang
        TextField studiengang = new TextField("Studiengang");
        studiengang.setValue(stellenanzeige.getStudiengang());
        studiengang.setReadOnly(true);

        //Zeitraum
        TextField zeitraum = new TextField("Ende der Ausschreibung");
        zeitraum.setValue(stellenanzeige.getZeitraum().format(DateTimeFormatter.ofPattern("dd.MM.yy")));
        zeitraum.setReadOnly(true);

        //Beschreibung
        TextArea beschreibung = new TextArea("Beschreibung");
        beschreibung.setValue(stellenanzeige.getBeschreibung());
        beschreibung.setReadOnly(true);

        //OkButton
        Button okButton = new Button("Ok");
        okButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                close();
            }
        });

        //BewerbenButton
        Button bewerbenButton = new Button("Bewerben");
        if ( user == null || !user.hasRole(Roles.STUDENT) ) {
            bewerbenButton.setVisible(false);
            bewerbenButton.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {
                    BewerbungControl.getInstance().applyForStellenanzeige(stellenanzeige, user);
                }
            });
        }


        //Horizontal
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.addComponent(okButton);
        horizontalLayout.addComponent(bewerbenButton);


        //Vertikal
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.addComponent(name);
        verticalLayout.addComponent(art);
        verticalLayout.addComponent(branche);
        verticalLayout.addComponent(studiengang);
        verticalLayout.addComponent(zeitraum);
        verticalLayout.addComponent(beschreibung);
        verticalLayout.addComponent(horizontalLayout);
        verticalLayout.setComponentAlignment(horizontalLayout, Alignment.MIDDLE_CENTER);

        setContent(verticalLayout);
    }
}
