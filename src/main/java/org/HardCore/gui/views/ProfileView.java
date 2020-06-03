package org.HardCore.gui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import org.HardCore.gui.components.TopPanel;
import org.HardCore.gui.ui.MyUI;
import org.HardCore.gui.windows.DeleteProfileWindow;
import org.HardCore.model.objects.dto.User;

public class ProfileView extends VerticalLayout implements View {

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

        User user = ((MyUI) UI.getCurrent()).getUser();

        this.setUp();
    }

    private void setUp() {
        //Top Layer
        this.addComponent(new TopPanel());
        Label line = new Label("<hr>", ContentMode.HTML);
        this.addComponent(line);
        line.setSizeFull();

        Button deleteButton = new Button("Profil löschen");
        deleteButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                UI.getCurrent().addWindow(new DeleteProfileWindow("Sind Sie sicher, dass Sie Ihr Profil löschen wollen? Dieser Vorgang ist endgültig!"));
            }
        });

        this.addComponent(deleteButton);
    }
}
