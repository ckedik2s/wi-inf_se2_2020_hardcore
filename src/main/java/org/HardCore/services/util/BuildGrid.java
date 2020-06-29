package org.HardCore.services.util;

import com.vaadin.ui.Grid;
import org.HardCore.model.objects.dto.StellenanzeigeDetail;

public class BuildGrid {
    public static void buildGrid(Grid<StellenanzeigeDetail> grid) {
        grid.removeAllColumns();
        grid.addColumn(StellenanzeigeDetail::getName).setCaption("Name");
        grid.addColumn(StellenanzeigeDetail::getArt).setCaption("Art");
        grid.addColumn(StellenanzeigeDetail::getBranche).setCaption("Branche");
        grid.addColumn(StellenanzeigeDetail::getStudiengang).setCaption("Studiengang");
        grid.addColumn(StellenanzeigeDetail::getOrt).setCaption("Ort");
        grid.addColumn(StellenanzeigeDetail::getZeitraum).setCaption("Ende der Ausschreibung");
    }
}
