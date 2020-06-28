package org.HardCore.gui.windows;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import org.HardCore.model.objects.dto.BewerbungDTO;
import org.HardCore.process.exceptions.BewerbungException;
import org.HardCore.process.proxy.BewerbungControlProxy;
import org.HardCore.services.util.Views;

import javax.swing.plaf.basic.BasicOptionPaneUI;

public abstract class DeleteWindow extends Window {
    public DeleteWindow(String text, Object dto, Button.ClickListener listener) {
        center();

        VerticalLayout verticalLayout = new VerticalLayout();
        Panel panel = new Panel();
        panel.setWidth("700");
        panel.setContent(new Label( text, ContentMode.HTML));
        verticalLayout.addComponent(panel);

        //OK Button
        Button okButton = new Button("Ok");
        okButton.addClickListener(listener);

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
