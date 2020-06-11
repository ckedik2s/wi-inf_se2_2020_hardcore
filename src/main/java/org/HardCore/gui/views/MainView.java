package org.HardCore.gui.views;

import com.vaadin.event.selection.SelectionEvent;
import com.vaadin.event.selection.SelectionListener;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.*;
import org.HardCore.gui.components.TopPanel;
import org.HardCore.model.objects.entities.Stellenanzeige;
import org.HardCore.model.objects.dto.User;
import org.HardCore.services.util.Roles;

public class MainView extends VerticalLayout implements View {

    private Stellenanzeige selektiert = null;

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

        User user = (User) VaadinSession.getCurrent().getAttribute(Roles.CURRENT_USER);
        //User user = ( (MyUI)UI.getCurrent() ).getUser();

        this.setUp();
    }

    private void setUp() {
        //Top Layer
        this.addComponent( new TopPanel() );
        Label line = new Label("<hr>", ContentMode.HTML);
        this.addComponent(line);
        line.setSizeFull();

        //Suchfeld
        final TextField search = new TextField();

        //Suchbutton
        Button searchButton = new Button("Suchen", VaadinIcons.SEARCH);

        //Anzeigenbutton
        Button showButton = new Button("Anzeigen", VaadinIcons.ENTER);

        //Tabelle
        final Grid<Stellenanzeige> grid = new Grid<>("Ihre Treffer");
        grid.setSizeFull();
        grid.setHeightMode(HeightMode.UNDEFINED);
        SingleSelect<Stellenanzeige> selection = grid.asSingleSelect();
        grid.addSelectionListener(new SelectionListener<Stellenanzeige>() {
            @Override
            public void selectionChange(SelectionEvent<Stellenanzeige> event) {
                System.out.println("Zeile selektiert: " + selection.getValue());
                selektiert = selection.getValue();
            }
        });

        //ShowButton Config
        searchButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                String suchtext = search.getValue();
                if (suchtext.equals("")) {
                    Notification.show(null, "Bitte geben Sie einen Suchbegriff ein", Notification.Type.WARNING_MESSAGE);
                }
                else {
                    addComponent(grid);
                }
            }
        });







        //Horizontal Layout
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.addComponent(search);
        horizontalLayout.addComponent(searchButton);
        horizontalLayout.setComponentAlignment(search, Alignment.MIDDLE_CENTER);
        horizontalLayout.setComponentAlignment(searchButton, Alignment.MIDDLE_CENTER);

        //Darstellen
        this.addComponent(horizontalLayout);
        this.setComponentAlignment(horizontalLayout, Alignment.MIDDLE_CENTER);
    }


}
