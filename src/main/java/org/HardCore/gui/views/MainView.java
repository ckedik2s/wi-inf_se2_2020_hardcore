package org.HardCore.gui.views;

import com.vaadin.data.HasValue;
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
import org.HardCore.model.dao.StellenanzeigeDAO;
import org.HardCore.model.objects.dto.StellenanzeigeDetail;
import org.HardCore.model.objects.dto.User;
import org.HardCore.process.control.SearchControl;
import org.HardCore.services.util.Roles;

import java.util.List;

public class MainView extends VerticalLayout implements View {

    private StellenanzeigeDetail selektiert = null;
    private List<StellenanzeigeDetail> list;
    private String suchtext;

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

        //ShowButton
        Button showButton = new Button("Anzeigen", VaadinIcons.ENTER);
        showButton.setEnabled(false);
        showButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {

            }
        });

        //Tabelle
        final Grid<StellenanzeigeDetail> grid = new Grid<>("Ihre Treffer");
        grid.setSizeFull();
        grid.setHeightMode(HeightMode.UNDEFINED);
        grid.addColumn(StellenanzeigeDetail::getName).setCaption("Name");
        grid.addColumn(StellenanzeigeDetail::getArt).setCaption("Art");
        grid.addColumn(StellenanzeigeDetail::getBranche).setCaption("Branche");
        grid.addColumn(StellenanzeigeDetail::getStudiengang).setCaption("Studiengang");
        grid.addColumn(StellenanzeigeDetail::getZeitraum).setCaption("Ende der Ausschreibung");
        SingleSelect<StellenanzeigeDetail> selection = grid.asSingleSelect();
        grid.addSelectionListener(new SelectionListener<StellenanzeigeDetail>() {
            @Override
            public void selectionChange(SelectionEvent<StellenanzeigeDetail> event) {
                System.out.println("Zeile selektiert: " + selection.getValue());
                selektiert = selection.getValue();
                showButton.setEnabled(true);
            }
        });

        //SearchButton
        Button searchButton = new Button("Suchen", VaadinIcons.SEARCH);

        //Combobox
        final ComboBox<String> comboBox = new ComboBox<>();
        comboBox.setPlaceholder("Sortieren nach");
        comboBox.setItems("Name");



        //Suchfeld
        final TextField search = new TextField();
        search.addValueChangeListener(new HasValue.ValueChangeListener<String>() {
            @Override
            public void valueChange(HasValue.ValueChangeEvent<String> valueChangeEvent) {
                if (search.getValue().length() > 1) {
                    suchtext = search.getValue();
                    list = SearchControl.getInstance().getAnzeigenForSearch(suchtext);
                    grid.setItems();
                    grid.setItems(list);
                    addComponent(grid);
                    addComponent(showButton);
                    setComponentAlignment(showButton, Alignment.MIDDLE_CENTER);
                } else {
                    removeComponent(grid);
                    removeComponent(showButton);
                }
            }
        });

        //SearchButton Config
        searchButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                suchtext = search.getValue();
                if (suchtext.equals("")) {
                    Notification.show(null, "Bitte geben Sie einen Suchbegriff ein", Notification.Type.WARNING_MESSAGE);
                }
            }
        });

        //Horizontal Layout
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.addComponent(comboBox);
        horizontalLayout.addComponent(search);
        horizontalLayout.addComponent(searchButton);
        horizontalLayout.setComponentAlignment(search, Alignment.MIDDLE_CENTER);
        horizontalLayout.setComponentAlignment(searchButton, Alignment.MIDDLE_CENTER);

        //Darstellen
        this.addComponent(horizontalLayout);
        this.setComponentAlignment(horizontalLayout, Alignment.MIDDLE_CENTER);
    }


}
