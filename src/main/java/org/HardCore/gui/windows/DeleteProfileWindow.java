package org.HardCore.gui.windows;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import org.HardCore.gui.ui.MyUI;
import org.HardCore.model.dao.RegisterDAO;
import org.HardCore.model.objects.dto.User;
import org.HardCore.process.control.LoginControl;
import org.HardCore.process.control.RegistrationControl;
import org.HardCore.services.util.Views;

public class DeleteProfileWindow extends Window {
    //Window zum Löschen von Usern
    public DeleteProfileWindow() {
        center();

        VerticalLayout verticalLayout = new VerticalLayout();
        Panel panel = new Panel();
        panel.setWidth("700");
        panel.setContent(new Label( "Sind Sie sicher, dass Sie Ihr Profil löschen wollen? <br>" +
                "Dieser Vorgang ist endgültig und löscht auch alle mit Ihrem Porfil erzeugten Dokumente!", ContentMode.HTML));
        verticalLayout.addComponent(panel);

        //OK Button
        Button okButton = new Button("Ok");
        okButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                User user = ((MyUI) UI.getCurrent()).getUser();
                RegistrationControl.getInstance().deleteUser(user);
                LoginControl.logoutUser();
                UI.getCurrent().getNavigator().navigateTo(Views.LOGIN);
                close();
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
