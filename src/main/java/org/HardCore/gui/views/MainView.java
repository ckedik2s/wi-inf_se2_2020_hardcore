package org.HardCore.gui.views;

import com.vaadin.data.HasValue;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.selection.SelectionEvent;
import com.vaadin.event.selection.SelectionListener;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.*;
import org.HardCore.gui.components.TopPanel;
import org.HardCore.gui.ui.MyUI;
import org.HardCore.gui.windows.StellenanzeigeWindow;
import org.HardCore.model.objects.dto.StellenanzeigeDetail;
import org.HardCore.model.objects.dto.UserDTO;
import org.HardCore.process.proxy.SearchControlProxy;

import java.util.List;

public class MainView extends VerticalLayout implements View {

    private StellenanzeigeDetail selektiert = null;
    private List<StellenanzeigeDetail> list;
    private String suchtext;


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

        //User user = (User) VaadinSession.getCurrent().getAttribute(Roles.CURRENT_USER);
        UserDTO userDTO = ( (MyUI)UI.getCurrent() ).getUserDTO();

        this.setUp();
    }

    private void setUp() {
        UserDTO userDTO = ( (MyUI)UI.getCurrent() ).getUserDTO();
        //Top Layer
        this.addComponent( new TopPanel() );
        Label line = new Label("<hr>", ContentMode.HTML);
        this.addComponent(line);
        line.setSizeFull();

        //Tabelle
        final Grid<StellenanzeigeDetail> grid = new Grid<>("Ihre Treffer");
        grid.setSizeFull();
        grid.setHeightMode(HeightMode.UNDEFINED);
        grid.addColumn(StellenanzeigeDetail::getName).setCaption("Name");
        grid.addColumn(StellenanzeigeDetail::getArt).setCaption("Art");
        grid.addColumn(StellenanzeigeDetail::getBranche).setCaption("Branche");
        grid.addColumn(StellenanzeigeDetail::getStudiengang).setCaption("Studiengang");
        grid.addColumn(StellenanzeigeDetail::getOrt).setCaption("Ort");
        grid.addColumn(StellenanzeigeDetail::getZeitraum).setCaption("Ende der Ausschreibung");
        SingleSelect<StellenanzeigeDetail> selection = grid.asSingleSelect();

        //DetailButton
        Button detailButton = new Button("Details", VaadinIcons.ENTER);
        detailButton.setEnabled(false);
        detailButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                selektiert = selection.getValue();
                UI.getCurrent().addWindow( new StellenanzeigeWindow(selektiert, userDTO) );
            }
        });

        //SearchButton
        Button searchButton = new Button("Suchen", VaadinIcons.SEARCH);
        searchButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        //Combobox
        final ComboBox<String> comboBox = new ComboBox<>();
        comboBox.setPlaceholder("Filtern nach");
        comboBox.setItems("Name", "Art", "Branche", "Studiengang", "Ort");

        //SelectionListener Tabelle
        grid.addSelectionListener(new SelectionListener<StellenanzeigeDetail>() {
            @Override
            public void selectionChange(SelectionEvent<StellenanzeigeDetail> event) {
                System.out.println("Zeile selektiert: " + selection.getValue());
                selektiert = selection.getValue();
                detailButton.setEnabled(true);
            }
        });

        //Suchfeld
        final TextField search = new TextField();
        search.setWidth("300");
        search.addValueChangeListener(new HasValue.ValueChangeListener<String>() {
            @Override
            public void valueChange(HasValue.ValueChangeEvent<String> valueChangeEvent) {
                search(search, comboBox, grid, detailButton);
            }
        });

        //SearchButton Config
        searchButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                if(suchtext.equals("")) {
                    return;
                }
                search(search, comboBox, grid, detailButton);
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

    private void search(TextField search, ComboBox<String> comboBox, Grid<StellenanzeigeDetail> grid, Button showButton) {
        if (search.getValue().length() > 1) {
            suchtext = search.getValue();
            String filter = comboBox.getValue();
            list = SearchControlProxy.getInstance().getAnzeigenForSearch(suchtext, filter);
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


}
