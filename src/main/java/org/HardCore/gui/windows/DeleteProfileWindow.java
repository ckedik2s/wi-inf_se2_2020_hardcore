package org.HardCore.gui.windows;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import org.HardCore.gui.ui.MyUI;
import org.HardCore.model.objects.dto.AbstractDTO;
import org.HardCore.model.objects.dto.UserDTO;
import org.HardCore.process.exceptions.StellenanzeigeException;
import org.HardCore.process.proxy.LoginControlProxy;
import org.HardCore.process.proxy.RegistrationControlProxy;
import org.HardCore.process.proxy.StellenanzeigeControlProxy;
import org.HardCore.services.util.Views;

public class DeleteProfileWindow extends Window {

    //Window zum Löschen von Usern
    public DeleteProfileWindow() {
        center();

        VerticalLayout verticalLayout = new VerticalLayout();
        Panel panel = new Panel();
        panel.setWidth("700");
        panel.setContent(new Label("Sind Sie sicher, dass Sie Ihr Profil löschen wollen? <br>" +
                "Dieser Vorgang ist endgültig und löscht auch alle mit Ihrem Porfil erzeugten Dokumente!", ContentMode.HTML));
        verticalLayout.addComponent(panel);

        //OK Button
        Button okButton = new Button("Ok");
        okButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                UserDTO userDTO = ((MyUI) UI.getCurrent()).getUserDTO();
                RegistrationControlProxy.getInstance().deleteUser(userDTO);
                LoginControlProxy.getInstance().logoutUser();
            }
        });

        //Abbruch Button
        Button abortButton = new Button("Abbrechen");
        abortButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                close();
            }
        });

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.addComponent(okButton);
        horizontalLayout.addComponent(abortButton);
        verticalLayout.addComponent(horizontalLayout);
        verticalLayout.setComponentAlignment(horizontalLayout, Alignment.MIDDLE_CENTER);
        setContent(verticalLayout);
    }
}
