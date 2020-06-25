package org.HardCore.process.control;

import com.vaadin.ui.Button;
import org.HardCore.model.dao.AbstractDAO;
import org.HardCore.model.dao.BewerbungDAO;
import org.HardCore.model.objects.dto.Bewerbung;
import org.HardCore.model.objects.dto.StellenanzeigeDetail;
import org.HardCore.model.objects.dto.Student;
import org.HardCore.model.objects.dto.User;
import org.HardCore.process.control.exceptions.BewerbungException;
import org.HardCore.process.control.exceptions.DatabaseException;
import org.HardCore.services.util.Roles;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BewerbungControl extends AbstractDAO {
    private static BewerbungControl bewerbungControl = null;

    private BewerbungControl() {

    }

    public static BewerbungControl getInstance() {
        if (bewerbungControl == null) {
            bewerbungControl = new BewerbungControl();
        }
        return bewerbungControl;
    }

    public void setBewerbung(Bewerbung bewerbung) {

    }

    public void applyForStellenanzeige(StellenanzeigeDetail stellenanzeige, User user) throws DatabaseException {
        String sql = "INSERT into collhbrs.bewerbung_to_stellenanzeige (id_student, id_stellenanzeige) " +
                "VALUES (?, ?);";
        PreparedStatement statement = this.getPreparedStatement(sql);
        try {
            statement.setInt(1, stellenanzeige.getId());
            statement.setInt(2, user.getId());
            statement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger((BewerbungDAO.class.getName())).log(Level.SEVERE, null, ex);
            throw new DatabaseException("Anfrage auf Stellenanzeige fehlgeschlagen!");

        }


    }

    public void applyingIsAllowed() throws BewerbungException {

        String sql = "SELECT sichtbar " +
                "FROM collhbrs.stellenanzeige_on_off " +
                "WHERE zeile = ? ;";
        PreparedStatement statement = this.getPreparedStatement(sql);
        ResultSet rs = null;
        try {
            statement.setInt(1, 1);
            rs = statement.executeQuery();
            if (!rs.getBoolean(1)) {
                throw new BewerbungException();
            }

        } catch (SQLException ex) {
            Logger.getLogger((BewerbungDAO.class.getName())).log(Level.SEVERE, null, ex);

        }
    }

    public void checkAllowed(User user, Button bewerbenButton) {
        if (user == null || !user.hasRole(Roles.STUDENT)) {
            bewerbenButton.setVisible(false);
        }
    }

    public void createBewerbung(String bewerbungstext, User user) throws BewerbungException {
        Student student = new Student(user);
        boolean result = BewerbungDAO.getInstance().setBewerbung(bewerbungstext, student);
        if (result == false) {
            throw new BewerbungException();
        }
    }
}
