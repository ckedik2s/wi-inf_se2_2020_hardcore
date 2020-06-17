package org.HardCore.gui.views;

import com.vaadin.event.selection.SelectionEvent;
import com.vaadin.event.selection.SelectionListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.*;
import org.HardCore.gui.components.TopPanel;
import org.HardCore.gui.ui.MyUI;
import org.HardCore.model.objects.dto.StellenanzeigeDetail;
import org.HardCore.model.objects.dto.User;
import org.HardCore.process.control.SearchControl;
import org.HardCore.process.control.StellenanzeigeControl;

import java.util.List;

public class

BewerbungView extends VerticalLayout implements View {

    private StellenanzeigeDetail selektiert;
    private List<StellenanzeigeDetail> list;

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

        User user = ( (MyUI) UI.getCurrent() ).getUser();

        this.setUp(user);
    }

    private void setUp(User user) {

        //Top Layer
        this.addComponent( new TopPanel() );
        Label line = new Label("<hr>", ContentMode.HTML);
        this.addComponent(line);
        line.setSizeFull();

        //Tabelle
        final Grid<StellenanzeigeDetail> grid = new Grid<>("Ihre Bewerbungen");
        grid.setSizeFull();
        grid.setHeightMode(HeightMode.UNDEFINED);
        SingleSelect<StellenanzeigeDetail> selection = grid.asSingleSelect();

        //Tabelle füllen
        list = SearchControl.getInstance().getAnzeigeForStudent();
        grid.removeAllColumns();
        grid.setItems(list);
        grid.addColumn(StellenanzeigeDetail::getName).setCaption("Name");
        grid.addColumn(StellenanzeigeDetail::getArt).setCaption("Art");
        grid.addColumn(StellenanzeigeDetail::getBranche).setCaption("Branche");
        grid.addColumn(StellenanzeigeDetail::getStudiengang).setCaption("Studiengang");
        grid.addColumn(StellenanzeigeDetail::getZeitraum).setCaption("Ende der Ausschreibung");

        //DeleteButton
        Button deleteButton = new Button("Löschen");
        deleteButton.setEnabled(false);

        //Tabellen Select Config
        grid.addSelectionListener(new SelectionListener<StellenanzeigeDetail>() {
            @Override
            public void selectionChange(SelectionEvent<StellenanzeigeDetail> event) {
                if (selection == null) {
                    return;
                }
                else {
                    System.out.println("Zeile selektiert: " + selection.getValue());
                    selektiert = selection.getValue();
                    deleteButton.setEnabled(true);
                }
            }
        });

        //deleteButton Config
        deleteButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                StellenanzeigeControl.getInstance().deleteStellenanzeige(selektiert);
                deleteButton.setEnabled(false);
                grid.setItems();
                list = StellenanzeigeControl.getInstance().getAnzeigenForStudent();
                try {
                    grid.setItems(list);
                } catch (Exception e) {
                    System.out.println("Fehler 1");
                    e.printStackTrace();
                }
            }
        });

        //HorizontalLayout
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        //horizontalLayout.addComponent(showButton);
        //horizontalLayout.addComponent(createButton);
        horizontalLayout.addComponent(deleteButton);

        //Darstellung
        addComponent(grid);
        addComponent(horizontalLayout);
        setComponentAlignment(horizontalLayout, Alignment.MIDDLE_CENTER);
    }

}
