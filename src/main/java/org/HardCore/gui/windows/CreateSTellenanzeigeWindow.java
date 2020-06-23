package org.HardCore.gui.windows;

import com.vaadin.ui.*;
import org.HardCore.model.objects.dto.StellenanzeigeDetail;
import org.HardCore.model.objects.dto.User;
import org.HardCore.process.control.StellenanzeigeControl;

import java.util.List;

public class CreateStellenanzeigeWindow extends Window {

    public CreateStellenanzeigeWindow(StellenanzeigeDetail stellenanzeige, Grid<StellenanzeigeDetail> grid) {
        super("Ihre Stellenanzeige");
        center();

        //Name
        TextField name = new TextField("Titel");
        name.setValue(stellenanzeige.getName());

        //Art
        TextField art = new TextField("Art");
        art.setValue(stellenanzeige.getArt());

        //Branche
        TextField branche = new TextField("Branche");
        branche.setValue(stellenanzeige.getBranche());

        //Studiengang
        TextField studiengang = new TextField("Studiengang");
        studiengang.setValue(stellenanzeige.getStudiengang());

        //Zeitraum
        DateField zeitraum = new DateField("Ende der Ausschreibung");
        zeitraum.setValue(stellenanzeige.getZeitraum());

        //Beschreibung
        TextArea beschreibung = new TextArea("Beschreibung");
        beschreibung.setValue(stellenanzeige.getBeschreibung());

        //saveButton Config
        Button saveButton = new Button("Speichern");
        saveButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                stellenanzeige.setName(name.getValue());
                stellenanzeige.setArt(art.getValue());
                stellenanzeige.setBranche(branche.getValue());
                stellenanzeige.setStudiengang(studiengang.getValue());
                stellenanzeige.setZeitraum(zeitraum.getValue());
                stellenanzeige.setBeschreibung(beschreibung.getValue());

                boolean result = StellenanzeigeControl.getInstance().createStellenanzeige(stellenanzeige);
                if (result == true) {
                    UI.getCurrent().addWindow(new ConfirmationWindow("Stellenanzeige erfolgreich gespeichert"));
                    List<StellenanzeigeDetail> list = StellenanzeigeControl.getInstance().getAnzeigenForUnternehmen();
                    try {
                        grid.setItems();
                        grid.setItems(list);
                    } catch (Exception e) {
                        System.out.println("Fehler 1");
                        e.printStackTrace();
                    }
                    close();
                } else {
                    Notification.show("Es ist ein Fehler aufgetreten. Bitte versuchen Sie es erneut!", Notification.Type.ERROR_MESSAGE);
                }
            }
        });

        //abortButton Config
        Button abortButton = new Button("Abbrechen");
        abortButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                close();
            }
        });

        //Horizontal
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.addComponent(saveButton);
        horizontalLayout.addComponent(abortButton);

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
