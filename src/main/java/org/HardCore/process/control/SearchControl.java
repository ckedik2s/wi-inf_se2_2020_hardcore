package org.HardCore.process.control;

import org.HardCore.model.objects.dto.StellenanzeigeDetail;

import java.util.List;

public class SearchControl {
    private static SearchControl search = null;

    public static SearchControl getInstance() {
        if (search == null) {
            search = new SearchControl();
        }
        return search;
    }

    private SearchControl() {

    }

    public List<StellenanzeigeDetail> getAnzeigenForUser() {
        return StellenanzeigeControl.getInstance().getAnzeigenForUnternehmen();
    }

    public List<StellenanzeigeDetail> getAnzeigeForStudent(){
        return StellenanzeigeControl.getInstance().getAnzeigenForStudent();
    }

    public List<StellenanzeigeDetail> getAnzeigenForSearch(String suchtext) {
        return StellenanzeigeControl.getInstance().getAnzeigenForSearch(suchtext);
    }
}
