package org.HardCore.gui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import org.HardCore.gui.components.TopPanel;
import org.HardCore.model.objects.dto.User;
import org.HardCore.services.util.Roles;

public class StellenanzeigeView extends VerticalLayout implements View {

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

        User user = (User) VaadinSession.getCurrent().getAttribute(Roles.CURRENT_USER);
        //User user = ( (MyUI) UI.getCurrent() ).getUser();

        this.setUp();
    }

    private void setUp() {

        //Top Layer
        this.addComponent( new TopPanel() );
        Label line = new Label("<hr>", ContentMode.HTML);
        this.addComponent(line);
        line.setSizeFull();

        //
    }
}
