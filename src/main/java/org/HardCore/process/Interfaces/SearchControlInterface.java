package org.HardCore.process.Interfaces;

import org.HardCore.model.objects.dto.StellenanzeigeDetail;

import java.util.List;

public interface SearchControlInterface {

    List<StellenanzeigeDetail> getAnzeigenForUser();

    List<StellenanzeigeDetail> getAnzeigenForSearch(String suchtext, String filter);
}
