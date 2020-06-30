package org.HardCore.gui.windows;

import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import org.HardCore.model.objects.dto.BewerbungDTO;
import org.HardCore.process.exceptions.BewerbungException;
import org.HardCore.process.proxy.BewerbungControlProxy;
import org.HardCore.services.util.Views;

public class DeleteBewerbungWindow extends DeleteWindow {
    //Window zum Löschen von Bewerbungen auf Stellenanzeigen

    public DeleteBewerbungWindow(BewerbungDTO bewerbungDTO) {
        this.setText("Sind Sie sicher, dass Sie Ihre Bewerbung auf diese Stellenanzeige löschen wollen? <br>" +
                "Dieser Vorgang ist unumkehrbar!");
        this.setDto(bewerbungDTO);
        this.setListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                try {
                    BewerbungControlProxy.getInstance().deleteBewerbung(bewerbungDTO);
                } catch (BewerbungException e) {
                    Notification.show("DB-Fehler", "Löschen war nicht erfolgreich!", Notification.Type.ERROR_MESSAGE);
                }
                Notification.show("Bewerbung gelöscht!", Notification.Type.HUMANIZED_MESSAGE);
                UI.getCurrent().getNavigator().navigateTo(Views.BEWERBUNG);
                for (Window w : UI.getCurrent().getWindows()) {
                    w.close();
                }
            }
        });
    }
}

