package org.HardCore.gui.windows;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import org.HardCore.model.objects.dto.AbstractDTO;
import org.HardCore.model.objects.dto.BewerbungDTO;
import org.HardCore.process.exceptions.BewerbungException;
import org.HardCore.process.proxy.BewerbungControlProxy;
import org.HardCore.services.util.Views;

import javax.swing.plaf.basic.BasicOptionPaneUI;

public class DeleteWindow extends Window {
    private String text;
    private AbstractDTO dto;
    private Button.ClickListener listener;

    public DeleteWindow() {

    }

    public DeleteWindow(String text, AbstractDTO dto, Button.ClickListener listener) {
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

    public DeleteWindow(DeleteWindow window) {
        center();

        VerticalLayout verticalLayout = new VerticalLayout();
        Panel panel = new Panel();
        panel.setWidth("700");
        panel.setContent(new Label( window.getText(), ContentMode.HTML));
        verticalLayout.addComponent(panel);

        //OK Button
        Button okButton = new Button("Ok");
        okButton.addClickListener(window.getListener());

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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public AbstractDTO getDto() {
        return dto;
    }

    public void setDto(AbstractDTO dto) {
        this.dto = dto;
    }

    public Button.ClickListener getListener() {
        return listener;
    }

    public void setListener(Button.ClickListener listener) {
        this.listener = listener;
    }
}
