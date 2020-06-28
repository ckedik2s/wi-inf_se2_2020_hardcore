package org.HardCore.process.proxy;

import com.vaadin.ui.UI;
import org.HardCore.gui.ui.MyUI;
import org.HardCore.model.dao.StellenanzeigeDAO;
import org.HardCore.model.factory.StellenanzeigeFactory;
import org.HardCore.model.objects.dto.StellenanzeigeDetail;
import org.HardCore.model.objects.dto.StudentDTO;
import org.HardCore.model.objects.dto.UnternehmenDTO;
import org.HardCore.model.objects.dto.UserDTO;
import org.HardCore.model.objects.entities.Stellenanzeige;
import org.HardCore.process.Interfaces.StellenanzeigeControlInterface;
import org.HardCore.process.control.StellenanzeigeControl;
import org.HardCore.process.exceptions.DatabaseException;
import org.HardCore.process.exceptions.StellenanzeigeException;
import org.HardCore.services.db.JDBCConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

    public List<StellenanzeigeDetail> getAnzeigenForUnternehmen(UnternehmenDTO unternehmenDTO) {
        return StellenanzeigeControl.getInstance().getAnzeigenForUnternehmen(unternehmenDTO);
    }

    public List<StellenanzeigeDetail> getAnzeigenForStudent(StudentDTO studentDTO) {
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

    public List<StellenanzeigeDetail> getAnzeigenForSearch(String suchtext, String filter) {
        return StellenanzeigeControl.getInstance().getAnzeigenForSearch(suchtext, filter);
    }

    public int getAnzahlBewerber(StellenanzeigeDetail stellenanzeigeDetail) throws DatabaseException {
        return StellenanzeigeControl.getInstance().getAnzahlBewerber(stellenanzeigeDetail);
    }
}
