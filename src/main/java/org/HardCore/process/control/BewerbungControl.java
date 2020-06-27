package org.HardCore.process.control;

import com.vaadin.ui.Button;
import org.HardCore.model.dao.BewerbungDAO;
import org.HardCore.model.objects.dto.Bewerbung;
import org.HardCore.model.objects.dto.StellenanzeigeDetail;
import org.HardCore.model.objects.dto.Student;
import org.HardCore.model.objects.dto.User;
import org.HardCore.process.control.exceptions.BewerbungException;
import org.HardCore.process.control.exceptions.DatabaseException;
import org.HardCore.services.db.JDBCConnection;
import org.HardCore.services.util.Roles;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BewerbungControl {
    private static BewerbungControl bewerbungControl = null;

    private BewerbungControl() {

    }

    public static BewerbungControl getInstance() {
        if (bewerbungControl == null) {
            bewerbungControl = new BewerbungControl();
        }
        return bewerbungControl;
    }

    public int getLatestApply(User user) throws DatabaseException {
        int id_bewerbung = 0;
        String sql = "SELECT max(id_bewerbung) " +
                "FROM collhbrs.bewerbung " +
                "WHERE id = ?";
        PreparedStatement statement = JDBCConnection.getInstance().getPreparedStatement(sql);
        ResultSet rs = null;
        try {
            statement.setInt(1, user.getId());
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

    private void applyingIsAllowed() throws DatabaseException, BewerbungException {
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

    private void checkAlreadyApplied(StellenanzeigeDetail stellenanzeigeDetail, User user) throws BewerbungException, DatabaseException {
        Student student = new Student(user);
        List<Bewerbung> list = BewerbungDAO.getInstance().getBewerbungenForStudent(student);
        String sql = "SELECT id_anzeige " +
                "FROM collhbrs.bewerbung_to_stellenanzeige " +
                "WHERE id_bewerbung = ? " +
                "AND id_anzeige = ?";
        PreparedStatement statement = JDBCConnection.getInstance().getPreparedStatement(sql);
        ResultSet rs = null;
        for (Bewerbung bewerbung : list) {
            int id_bewerbung = bewerbung.getId();
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
    public void checkAllowed(StellenanzeigeDetail stellenanzeige, User user, Button bewerbenButton) {
        try {
            applyingIsAllowed();
            checkAlreadyApplied(stellenanzeige, user);
        } catch (DatabaseException e) {
            e.printStackTrace();
        } catch (BewerbungException e) {
            bewerbenButton.setVisible(false);
        }
        if (user == null || !user.hasRole(Roles.STUDENT)) {
            bewerbenButton.setVisible(false);
        }
    }

    public void createBewerbung(String bewerbungstext, User user) throws BewerbungException {
        Student student = new Student(user);
        boolean result = BewerbungDAO.getInstance().createBewerbung(bewerbungstext, student);
        if (result == false) {
            throw new BewerbungException();
        }
    }

    public Bewerbung getBewerbungForStellenanzeige(StellenanzeigeDetail selektiert, Student student) {
        List<Bewerbung> list = getBewerbungenForStudent(student);
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
        for (Bewerbung bewerbung : list) {
            try {
                statement.setInt(1, selektiert.getId_anzeige());
                statement.setInt(2, bewerbung.getId());
                rs = statement.executeQuery();
                if ( rs.next() ) {
                    return bewerbung;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public List<Bewerbung> getBewerbungenForStudent(Student student) {
        return BewerbungDAO.getInstance().getBewerbungenForStudent(student);
    }

    public void deleteBewerbung(Bewerbung bewerbung) throws BewerbungException {
        boolean result = BewerbungDAO.getInstance().deleteBewerbung(bewerbung);
        if (result) {
            return;
        }
        throw new BewerbungException();
    }
}
