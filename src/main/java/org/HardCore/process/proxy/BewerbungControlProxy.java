package org.HardCore.process.proxy;

import com.vaadin.ui.Button;
import org.HardCore.model.dao.BewerbungDAO;
import org.HardCore.model.objects.dto.BewerbungDTO;
import org.HardCore.model.objects.dto.StellenanzeigeDetail;
import org.HardCore.model.objects.dto.StudentDTO;
import org.HardCore.model.objects.dto.UserDTO;
import org.HardCore.process.Interfaces.BewerbungControlInterface;
import org.HardCore.process.control.BewerbungControl;
import org.HardCore.process.exceptions.BewerbungException;
import org.HardCore.process.exceptions.DatabaseException;
import org.HardCore.services.db.JDBCConnection;
import org.HardCore.services.util.Roles;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BewerbungControlProxy implements BewerbungControlInterface {
    private static BewerbungControlProxy bewerbungControl = null;

    private BewerbungControlProxy() {

    }

    public static BewerbungControlProxy getInstance() {
        if (bewerbungControl == null) {
            bewerbungControl = new BewerbungControlProxy();
        }
        return bewerbungControl;
    }

    public int getLatestApply(UserDTO userDTO) throws DatabaseException {
        return BewerbungControl.getInstance().getLatestApply(userDTO);
    }

    public void applyForStellenanzeige(StellenanzeigeDetail stellenanzeige, int id_bewerbung) throws DatabaseException {
        BewerbungControl.getInstance().applyForStellenanzeige(stellenanzeige, id_bewerbung);
    }

    public void applyingIsAllowed() throws DatabaseException, BewerbungException {
        BewerbungControl.getInstance().applyingIsAllowed();
    }

    public void checkAlreadyApplied(StellenanzeigeDetail stellenanzeigeDetail, UserDTO userDTO) throws BewerbungException, DatabaseException {
        BewerbungControl.getInstance().checkAlreadyApplied(stellenanzeigeDetail, userDTO);

    }
    public void checkAllowed(StellenanzeigeDetail stellenanzeige, UserDTO userDTO, Button bewerbenButton) {
        BewerbungControl.getInstance().checkAllowed(stellenanzeige, userDTO, bewerbenButton);
    }

    public void createBewerbung(String bewerbungstext, UserDTO userDTO) throws BewerbungException {
        BewerbungControl.getInstance().createBewerbung(bewerbungstext, userDTO);
    }

    public BewerbungDTO getBewerbungForStellenanzeige(StellenanzeigeDetail selektiert, StudentDTO studentDTO) {
        return BewerbungControl.getInstance().getBewerbungForStellenanzeige(selektiert, studentDTO);
    }

    public List<BewerbungDTO> getBewerbungenForStudent(StudentDTO studentDTO) {
        return BewerbungControl.getInstance().getBewerbungenForStudent(studentDTO);
    }

    public void deleteBewerbung(BewerbungDTO bewerbungDTO) throws BewerbungException {
        BewerbungControl.getInstance().deleteBewerbung(bewerbungDTO);
    }
}
