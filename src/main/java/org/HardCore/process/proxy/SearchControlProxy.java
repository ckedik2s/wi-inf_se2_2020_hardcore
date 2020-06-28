package org.HardCore.process.proxy;

import com.vaadin.ui.UI;
import org.HardCore.gui.ui.MyUI;
import org.HardCore.model.objects.dto.StellenanzeigeDetail;
import org.HardCore.model.objects.dto.StudentDTO;
import org.HardCore.model.objects.dto.UnternehmenDTO;
import org.HardCore.model.objects.dto.UserDTO;
import org.HardCore.process.Interfaces.SearchControlInterface;
import org.HardCore.process.control.SearchControl;
import org.HardCore.services.util.Roles;

import java.util.List;

public class SearchControlProxy implements SearchControlInterface {
    private static SearchControlProxy search = null;

    public static SearchControlProxy getInstance() {
        if (search == null) {
            search = new SearchControlProxy();
        }
        return search;
    }

    private SearchControlProxy() {

    }

    public List<StellenanzeigeDetail> getAnzeigenForUser() {
        return SearchControl.getInstance().getAnzeigenForUser();
    }

    public List<StellenanzeigeDetail> getAnzeigenForSearch(String suchtext, String filter) {
        return SearchControl.getInstance().getAnzeigenForSearch(suchtext, filter);
    }
}
