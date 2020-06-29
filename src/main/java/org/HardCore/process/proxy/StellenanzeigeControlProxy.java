package org.HardCore.process.proxy;

import org.HardCore.model.objects.dto.StellenanzeigeDTO;
import org.HardCore.model.objects.dto.StudentDTO;
import org.HardCore.model.objects.dto.UnternehmenDTO;
import org.HardCore.process.Interfaces.StellenanzeigeControlInterface;
import org.HardCore.process.control.StellenanzeigeControl;
import org.HardCore.process.exceptions.DatabaseException;
import org.HardCore.process.exceptions.StellenanzeigeException;

import java.sql.SQLException;
import java.util.List;

public class StellenanzeigeControlProxy implements StellenanzeigeControlInterface {
    private static StellenanzeigeControlProxy search = null;

    public static StellenanzeigeControlProxy getInstance() {
        if (search == null) {
            search = new StellenanzeigeControlProxy();
        }
        return search;
    }

    private StellenanzeigeControlProxy() {

    }

    public List<StellenanzeigeDTO> getAnzeigenForUnternehmen(UnternehmenDTO unternehmenDTO) throws SQLException {
        return StellenanzeigeControl.getInstance().getAnzeigenForUnternehmen(unternehmenDTO);
    }

    public List<StellenanzeigeDTO> getAnzeigenForStudent(StudentDTO studentDTO) throws SQLException {
        return StellenanzeigeControl.getInstance().getAnzeigenForStudent(studentDTO);
    }
    public void createStellenanzeige(StellenanzeigeDTO stellenanzeigeDTO) throws StellenanzeigeException {
        StellenanzeigeControl.getInstance().createStellenanzeige(stellenanzeigeDTO);
    }
    public void updateStellenanzeige(StellenanzeigeDTO stellenanzeigeDTO) throws StellenanzeigeException {
        StellenanzeigeControl.getInstance().updateStellenanzeige(stellenanzeigeDTO);
    }

    public void deleteStellenanzeige(StellenanzeigeDTO stellenanzeigeDTO) throws StellenanzeigeException {
        StellenanzeigeControl.getInstance().deleteStellenanzeige(stellenanzeigeDTO);
    }

    public List<StellenanzeigeDTO> getAnzeigenForSearch(String suchtext, String filter) throws SQLException {
        return StellenanzeigeControl.getInstance().getAnzeigenForSearch(suchtext, filter);
    }

    public int getAnzahlBewerber(StellenanzeigeDTO stellenanzeigeDTO) throws DatabaseException, SQLException {
        return StellenanzeigeControl.getInstance().getAnzahlBewerber(stellenanzeigeDTO);
    }
}
