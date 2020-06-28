package org.HardCore.process.proxy;

import org.HardCore.model.objects.dto.StellenanzeigeDetail;
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

    public List<StellenanzeigeDetail> getAnzeigenForUnternehmen(UnternehmenDTO unternehmenDTO) throws SQLException {
        return StellenanzeigeControl.getInstance().getAnzeigenForUnternehmen(unternehmenDTO);
    }

    public List<StellenanzeigeDetail> getAnzeigenForStudent(StudentDTO studentDTO) throws SQLException {
        return StellenanzeigeControl.getInstance().getAnzeigenForStudent(studentDTO);
    }
    public void createStellenanzeige(StellenanzeigeDetail stellenanzeigeDetail) throws StellenanzeigeException {
        StellenanzeigeControl.getInstance().createStellenanzeige(stellenanzeigeDetail);
    }
    public void updateStellenanzeige(StellenanzeigeDetail stellenanzeigeDetail) throws StellenanzeigeException {
        StellenanzeigeControl.getInstance().updateStellenanzeige(stellenanzeigeDetail);
    }

    public void deleteStellenanzeige(StellenanzeigeDetail stellenanzeigeDetail) throws StellenanzeigeException {
        StellenanzeigeControl.getInstance().deleteStellenanzeige(stellenanzeigeDetail);
    }

    public List<StellenanzeigeDetail> getAnzeigenForSearch(String suchtext, String filter) throws SQLException {
        return StellenanzeigeControl.getInstance().getAnzeigenForSearch(suchtext, filter);
    }

    public int getAnzahlBewerber(StellenanzeigeDetail stellenanzeigeDetail) throws DatabaseException {
        return StellenanzeigeControl.getInstance().getAnzahlBewerber(stellenanzeigeDetail);
    }
}
