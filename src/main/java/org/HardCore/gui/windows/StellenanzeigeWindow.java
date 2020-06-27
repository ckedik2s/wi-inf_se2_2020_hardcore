package org.HardCore.gui.windows;

import com.vaadin.ui.*;
import org.HardCore.model.objects.dto.StellenanzeigeDetail;
import org.HardCore.model.objects.dto.Unternehmen;
import org.HardCore.model.objects.dto.User;
import org.HardCore.process.control.BewerbungControl;
import org.HardCore.process.control.StellenanzeigeControl;
import org.HardCore.services.util.Roles;

import java.time.format.DateTimeFormatter;
import java.util.List;

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

        //Ort
        TextField ort = new TextField("Ort");
        ort.setValue(stellenanzeige.getOrt());
        ort.setReadOnly(true);

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
        BewerbungControl.getInstance().checkAllowed(stellenanzeige, user, bewerbenButton);
        bewerbenButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                UI.getCurrent().addWindow(new FreitextWindow(stellenanzeige, user));
                close();
            }
        });

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
        verticalLayout.addComponent(ort);
        verticalLayout.addComponent(zeitraum);
        verticalLayout.addComponent(beschreibung);
        verticalLayout.addComponent(horizontalLayout);
        verticalLayout.setComponentAlignment(horizontalLayout, Alignment.MIDDLE_CENTER);

        setContent(verticalLayout);
    }

    public StellenanzeigeWindow(StellenanzeigeDetail stellenanzeige, Grid<StellenanzeigeDetail> grid, Unternehmen unternehmen) {
        super(stellenanzeige.getName());
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

        //Ort
        TextField ort = new TextField("Ort");
        ort.setValue(stellenanzeige.getOrt());

        //Zeitraum
        DateField zeitraum = new DateField("Ende der Ausschreibung");
        zeitraum.setValue(stellenanzeige.getZeitraum());

        //Beschreibung
        TextArea beschreibung = new TextArea("Beschreibung");
        beschreibung.setValue(stellenanzeige.getBeschreibung());

        //SaveButton
        Button saveButton = new Button("Speichern");
        saveButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                stellenanzeige.setName(name.getValue());
                stellenanzeige.setArt(art.getValue());
                stellenanzeige.setBranche(branche.getValue());
                stellenanzeige.setStudiengang(studiengang.getValue());
                stellenanzeige.setOrt(ort.getValue());
                stellenanzeige.setZeitraum(zeitraum.getValue());
                stellenanzeige.setBeschreibung(beschreibung.getValue());
                boolean result = StellenanzeigeControl.getInstance().updateStellenanzeige(stellenanzeige);
                if (result == true) {
                    UI.getCurrent().addWindow(new ConfirmationWindow("Stellenanzeige erfolgreich gespeichert"));
                    List<StellenanzeigeDetail> list = StellenanzeigeControl.getInstance().getAnzeigenForUnternehmen(unternehmen);
                    try {
                        grid.setItems();
                        grid.setItems(list);
                    } catch (Exception e) {
                        System.out.println("Fehler StellenanzeigenWindow");
                        e.printStackTrace();
                    }
                    close();
                } else {
                    Notification.show("Es ist ein Fehler aufgetreten. Bitte versuchen Sie es erneut!", Notification.Type.ERROR_MESSAGE);
                }
            }
        });

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
        verticalLayout.addComponent(ort);
        verticalLayout.addComponent(zeitraum);
        verticalLayout.addComponent(beschreibung);
        verticalLayout.addComponent(horizontalLayout);
        verticalLayout.setComponentAlignment(horizontalLayout, Alignment.MIDDLE_CENTER);

        setContent(verticalLayout);
    }
}
