package org.HardCore.process.Interfaces;

import org.HardCore.model.objects.dto.StellenanzeigeDetail;
import org.HardCore.model.objects.dto.StudentDTO;
import org.HardCore.model.objects.dto.UnternehmenDTO;
import org.HardCore.process.exceptions.DatabaseException;
import org.HardCore.process.exceptions.StellenanzeigeException;

import java.sql.SQLException;
import java.util.List;

public interface StellenanzeigeControlInterface {

    List<StellenanzeigeDetail> getAnzeigenForUnternehmen(UnternehmenDTO unternehmenDTO) throws SQLException;

    List<StellenanzeigeDetail> getAnzeigenForStudent(StudentDTO studentDTO) throws SQLException;

    void createStellenanzeige(StellenanzeigeDetail stellenanzeigeDetail) throws StellenanzeigeException;

    void updateStellenanzeige(StellenanzeigeDetail stellenanzeigeDetail) throws StellenanzeigeException;

    void deleteStellenanzeige(StellenanzeigeDetail stellenanzeigeDetail) throws StellenanzeigeException;

    List<StellenanzeigeDetail> getAnzeigenForSearch(String suchtext, String filter) throws SQLException;

    int getAnzahlBewerber(StellenanzeigeDetail stellenanzeigeDetail) throws DatabaseException;
}
