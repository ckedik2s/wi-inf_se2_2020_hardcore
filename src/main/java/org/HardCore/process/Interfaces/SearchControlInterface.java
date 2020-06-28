package org.HardCore.process.Interfaces;

import org.HardCore.model.objects.dto.StellenanzeigeDetail;

import java.sql.SQLException;
import java.util.List;

public interface SearchControlInterface {

    List<StellenanzeigeDetail> getAnzeigenForUser() throws SQLException;

    List<StellenanzeigeDetail> getAnzeigenForSearch(String suchtext, String filter);
}
