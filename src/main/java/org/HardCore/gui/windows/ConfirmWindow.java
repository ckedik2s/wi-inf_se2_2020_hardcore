package org.HardCore.gui.windows;

import com.vaadin.ui.*;
import org.HardCore.gui.ui.MyUI;
import org.HardCore.model.dao.RegisterDAO;
import org.HardCore.model.objects.dto.User;
import org.HardCore.process.control.LoginControl;
import org.HardCore.process.control.RegistrationControl;
import org.HardCore.services.util.Views;

public class ConfirmWindow extends Window {
    //Window zum Löschen von Usern
    public ConfirmWindow( String text) {
        center();

        VerticalLayout content = new VerticalLayout();
        content.addComponent(new Label( text ));
        content.setMargin(true);
        setContent(content);

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

        Button abortButton = new Button("Abbrechen");
        abortButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                close();
            }
        });

        content.addComponent(okButton);
        content.addComponent(abortButton);
    }
}
