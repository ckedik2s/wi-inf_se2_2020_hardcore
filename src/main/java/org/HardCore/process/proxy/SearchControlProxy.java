package org.HardCore.process.proxy;

import org.HardCore.model.objects.dto.StellenanzeigeDetail;
import org.HardCore.process.Interfaces.SearchControlInterface;
import org.HardCore.process.control.SearchControl;

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
