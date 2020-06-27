package org.HardCore.gui.windows;

import com.vaadin.ui.*;
import org.HardCore.model.objects.dto.StellenanzeigeDetail;
import org.HardCore.model.objects.dto.User;
import org.HardCore.process.control.BewerbungControl;
import org.HardCore.process.control.exceptions.BewerbungException;
import org.HardCore.process.control.exceptions.DatabaseException;

public class FreitextWindow extends Window {

    public FreitextWindow(StellenanzeigeDetail stellenanzeige, User user) {
        super(stellenanzeige.getName());
        center();

        //Bewerbungstext
        TextArea bewerbung = new TextArea("Bewerbungstext");
        bewerbung.setSizeUndefined();

        //ApplyButton
        Button applyButton = new Button("Einreichen");
        applyButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                String bewerbungstext = bewerbung.getValue();

                try {
                    BewerbungControl.getInstance().createBewerbung(bewerbungstext, user);
                    int id_bewerbung = BewerbungControl.getInstance().getLatestApply(user);
                    BewerbungControl.getInstance().applyForStellenanzeige(stellenanzeige, id_bewerbung);
                }
                catch (DatabaseException db) {
                    Notification.show("Es ist ein Fehler bei der Bewerbung aufgetreten. Bitte versuchen Sie es erneut!", Notification.Type.ERROR_MESSAGE);
                    return;
                }
                catch (BewerbungException e) {
                    Notification.show("Bewerbung konnte nicht eingereicht werden.", Notification.Type.WARNING_MESSAGE);
                    return;
                }
                UI.getCurrent().addWindow(new ConfirmationWindow("Bewerbung erfolgreich abgegeben!"));
                close();
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
        horizontalLayout.addComponent(applyButton);
        horizontalLayout.addComponent(abortButton);

        //Vertikal
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.addComponent(bewerbung);
        verticalLayout.addComponent(horizontalLayout);
        verticalLayout.setComponentAlignment(horizontalLayout, Alignment.MIDDLE_CENTER);

        setContent(verticalLayout);

    }
}
