package org.HardCore.gui.windows;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import org.HardCore.model.objects.dto.StellenanzeigeDetail;
import org.HardCore.process.exceptions.BewerbungException;
import org.HardCore.process.exceptions.StellenanzeigeException;
import org.HardCore.process.proxy.BewerbungControlProxy;
import org.HardCore.process.proxy.StellenanzeigeControlProxy;
import org.HardCore.services.util.Views;

public class DeleteStellenanzeigeWindow extends DeleteWindow{
    //Window zum Löschen von Stellenanzeigen

    public DeleteStellenanzeigeWindow(StellenanzeigeDetail stellenanzeigeDetail) {
        this.setText("Sind Sie sicher, dass Sie die Stellenanzeige löschen wollen? <br>" +
                "Dieser Vorgang ist unumkehrbar!");
        this.setDto(stellenanzeigeDetail);
        this.setListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                try {
                    StellenanzeigeControlProxy.getInstance().deleteStellenanzeige(stellenanzeigeDetail);
                } catch (StellenanzeigeException e) {
                    Notification.show("Es ist ein Fehler aufgetreten. Bitte versuchen Sie es erneut!", Notification.Type.ERROR_MESSAGE);
                }
                UI.getCurrent().getNavigator().navigateTo(Views.STELLENANZEIGE);
                for (Window w : UI.getCurrent().getWindows()) {
                    w.close();
                }
            }
        });
    }
}

