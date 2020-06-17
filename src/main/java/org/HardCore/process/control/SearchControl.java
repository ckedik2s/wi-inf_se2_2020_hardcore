package org.HardCore.process.control;

import com.vaadin.ui.UI;
import org.HardCore.gui.ui.MyUI;
import org.HardCore.model.dao.StellenanzeigeDAO;
import org.HardCore.model.objects.dto.StellenanzeigeDetail;
import org.HardCore.model.objects.entities.Stellenanzeige;
import org.HardCore.model.objects.dto.User;

import java.util.List;

public class SearchControl {
    public static SearchControl search = null;

    public static SearchControl getInstance() {
        if (search == null) {
            search = new SearchControl();
        }
        return search;
    }

    private SearchControl() {

    }

    public List<StellenanzeigeDetail> getAnzeigenForUser() {
        return StellenanzeigeControl.getInstance().getAnzeigenForUser();
    }

    public List<StellenanzeigeDetail> getAnzeigenForSearch(String suchtext) {
        return StellenanzeigeControl.getInstance().getAnzeigenForSearch(suchtext);
    }
}
