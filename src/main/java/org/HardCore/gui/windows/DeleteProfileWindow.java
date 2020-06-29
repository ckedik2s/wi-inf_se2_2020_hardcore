package org.HardCore.gui.windows;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import org.HardCore.gui.ui.MyUI;
import org.HardCore.model.objects.dto.AbstractDTO;
import org.HardCore.model.objects.dto.UserDTO;
import org.HardCore.process.exceptions.BewerbungException;
import org.HardCore.process.exceptions.StellenanzeigeException;
import org.HardCore.process.proxy.BewerbungControlProxy;
import org.HardCore.process.proxy.LoginControlProxy;
import org.HardCore.process.proxy.RegistrationControlProxy;
import org.HardCore.process.proxy.StellenanzeigeControlProxy;
import org.HardCore.services.util.Views;

public class DeleteProfileWindow extends DeleteWindow {

    //Window zum Löschen von Usern
    public DeleteProfileWindow() {
        this.setText("Sind Sie sicher, dass Sie Ihr Profil löschen wollen? <br>" +
                "Dieser Vorgang ist endgültig und löscht auch alle mit Ihrem Porfil erzeugten Dokumente!");
        this.setListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                UserDTO userDTO = ((MyUI) UI.getCurrent()).getUserDTO();
                RegistrationControlProxy.getInstance().deleteUser(userDTO);
                LoginControlProxy.getInstance().logoutUser();
            }
        });
    }
}
