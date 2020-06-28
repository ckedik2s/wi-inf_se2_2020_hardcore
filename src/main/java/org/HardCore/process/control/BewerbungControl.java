package org.HardCore.process.control;

import com.vaadin.ui.Button;
import org.HardCore.model.dao.BewerbungDAO;
import org.HardCore.model.objects.dto.BewerbungDTO;
import org.HardCore.model.objects.dto.StellenanzeigeDetail;
import org.HardCore.model.objects.dto.StudentDTO;
import org.HardCore.model.objects.dto.UserDTO;
import org.HardCore.process.Interfaces.BewerbungControlInterface;
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

public class BewerbungControl implements BewerbungControlInterface {
    private static BewerbungControl bewerbungControl = null;

    private BewerbungControl() {

    }

    public static BewerbungControl getInstance() {
        if (bewerbungControl == null) {
            bewerbungControl = new BewerbungControl();
        }
        return bewerbungControl;
    }

    public int getLatestApply(UserDTO userDTO) throws DatabaseException {
        int id_bewerbung = 0;
        String sql = "SELECT max(id_bewerbung) " +
                "FROM collhbrs.bewerbung " +
                "WHERE id = ?";
        PreparedStatement statement = JDBCConnection.getInstance().getPreparedStatement(sql);
        ResultSet rs = null;
        try {
            statement.setInt(1, userDTO.getId());
            rs = statement.executeQuery();
            if (rs == null) {
                return id_bewerbung;
            }
            if (rs.next()) {
                id_bewerbung = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger((BewerbungDAO.class.getName())).log(Level.SEVERE, null, ex);
        }
        return id_bewerbung;
    }

    public void applyForStellenanzeige(StellenanzeigeDetail stellenanzeige, int id_bewerbung) throws DatabaseException {
        String sql = "INSERT INTO collhbrs.bewerbung_to_stellenanzeige (id_bewerbung, id_anzeige) " +
                "VALUES (?, ?);";
        PreparedStatement statement = JDBCConnection.getInstance().getPreparedStatement(sql);
        try {
            statement.setInt(1, id_bewerbung);
            statement.setInt(2, stellenanzeige.getId_anzeige());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger((BewerbungDAO.class.getName())).log(Level.SEVERE, null, ex);
        }
    }

    public void applyingIsAllowed() throws DatabaseException, BewerbungException {
        String sql = "SELECT sichtbar " +
                "FROM collhbrs.stellenanzeige_on_off";
        PreparedStatement statement = JDBCConnection.getInstance().getPreparedStatement(sql);
        ResultSet rs = null;
        try {
            rs = statement.executeQuery();
            if (rs == null) {
                return;
            }
            if ( rs.next() ) {
                if (rs.getBoolean(1)) {
                    return;
                }
                throw new BewerbungException();
            }
        } catch (SQLException ex) {
            Logger.getLogger((BewerbungDAO.class.getName())).log(Level.SEVERE, null, ex);
        }
    }

    public void checkAlreadyApplied(StellenanzeigeDetail stellenanzeigeDetail, UserDTO userDTO) throws BewerbungException, DatabaseException {
        StudentDTO studentDTO = new StudentDTO(userDTO);
        List<BewerbungDTO> list = BewerbungDAO.getInstance().getBewerbungenForStudent(studentDTO);
        String sql = "SELECT id_anzeige " +
                "FROM collhbrs.bewerbung_to_stellenanzeige " +
                "WHERE id_bewerbung = ? " +
                "AND id_anzeige = ?";
        PreparedStatement statement = JDBCConnection.getInstance().getPreparedStatement(sql);
        ResultSet rs = null;
        for (BewerbungDTO bewerbungDTO : list) {
            int id_bewerbung = bewerbungDTO.getId();
            try {
                statement.setInt(1, id_bewerbung);
                statement.setInt(2, stellenanzeigeDetail.getId_anzeige());
                rs = statement.executeQuery();
                if (rs == null) {
                    return;
                }
                if (rs.next()) {
                    throw new BewerbungException();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
    public void checkAllowed(StellenanzeigeDetail stellenanzeige, UserDTO userDTO, Button bewerbenButton) {
        try {
            applyingIsAllowed();
            checkAlreadyApplied(stellenanzeige, userDTO);
        } catch (DatabaseException e) {
            e.printStackTrace();
        } catch (BewerbungException e) {
            bewerbenButton.setVisible(false);
        }
        if (userDTO == null || !userDTO.hasRole(Roles.STUDENT)) {
            bewerbenButton.setVisible(false);
        }
    }

    public void createBewerbung(String bewerbungstext, UserDTO userDTO) throws BewerbungException {
        StudentDTO studentDTO = new StudentDTO(userDTO);
        boolean result = BewerbungDAO.getInstance().createBewerbung(bewerbungstext, studentDTO);
        if (result == false) {
            throw new BewerbungException();
        }
    }

    public BewerbungDTO getBewerbungForStellenanzeige(StellenanzeigeDetail selektiert, StudentDTO studentDTO) {
        List<BewerbungDTO> list = getBewerbungenForStudent(studentDTO);
        String sql = "SELECT id_bewerbung " +
                "FROM collhbrs.bewerbung_to_stellenanzeige " +
                "WHERE id_anzeige = ? " +
                "AND id_bewerbung = ? ";
        PreparedStatement statement = null;
        try {
            statement = JDBCConnection.getInstance().getPreparedStatement(sql);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        ResultSet rs = null;
        for (BewerbungDTO bewerbungDTO : list) {
            try {
                statement.setInt(1, selektiert.getId_anzeige());
                statement.setInt(2, bewerbungDTO.getId());
                rs = statement.executeQuery();
                if ( rs.next() ) {
                    return bewerbungDTO;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public List<BewerbungDTO> getBewerbungenForStudent(StudentDTO studentDTO) {
        return BewerbungDAO.getInstance().getBewerbungenForStudent(studentDTO);
    }

    public void deleteBewerbung(BewerbungDTO bewerbungDTO) throws BewerbungException {
        boolean result = BewerbungDAO.getInstance().deleteBewerbung(bewerbungDTO);
        if (result) {
            return;
        }
        throw new BewerbungException();
    }
}
