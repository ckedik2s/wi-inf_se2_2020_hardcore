package org.hardcore.process.Interfaces;

import org.hardcore.model.objects.dto.StellenanzeigeDTO;

import java.sql.SQLException;
import java.util.List;

public interface SearchControlInterface {

    List<StellenanzeigeDTO> getAnzeigenForUser() throws SQLException;

    List<StellenanzeigeDTO> getAnzeigenForSearch(String suchtext, String filter) throws SQLException;
}
