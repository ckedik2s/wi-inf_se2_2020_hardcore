package org.HardCore.model.dao;

import org.HardCore.model.objects.dto.Bewerbung;
import org.HardCore.model.objects.dto.StellenanzeigeDetail;
import org.HardCore.model.objects.dto.Student;
import org.HardCore.model.objects.dto.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BewerbungDAO extends AbstractDAO {
    private static BewerbungDAO bewerbungDAO = null;

    private BewerbungDAO() {

    }

    public static BewerbungDAO getInstance() {
        if (bewerbungDAO == null) {
            bewerbungDAO = new BewerbungDAO();
        }
        return bewerbungDAO;
    }

    public Bewerbung getBewerbung(Student student) {
        Bewerbung bewerbung = new Bewerbung();
        Statement statement = getStatement();
        ResultSet rs = null;
        try {
            rs = statement.executeQuery("SELECT id_anzeige, freitext " +
                    "FROM collhbrs.bewerbung " +
                    "WHERE id =\'" + student.getId() + "\';");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        if (rs == null) {
            return null;
        }
        try {
            while (rs.next()) {
                bewerbung.setId(rs.getInt(1));
                bewerbung.setFreitext(rs.getString(2));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return bewerbung;
    }

    public boolean setBewerbung(String text, Student student) {
        String sql = "INSERT INTO collhbrs.anzeige (id, freitext) " +
                "VALUES (?, ?); ";
        PreparedStatement statement = this.getPreparedStatement(sql);
        try {
            statement.setInt(1, student.getId());
            statement.setString(2, text);
            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        }

    }

    public boolean deleteBewerbung(int id_anzeige) {
        String sql = "DELETE " +
                "FROM collhbrs.bewerbung_to_stellenanzeige " +
                "WHERE id_stellenanzeige = ? ;";
        PreparedStatement statement = this.getPreparedStatement(sql);
        try {
            statement.setInt(1, id_anzeige);
            statement.executeUpdate();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger((BewerbungDAO.class.getName())).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean applyForStellenanzeige(StellenanzeigeDetail stellenanzeige, Student student) {
        // TODO bewerben auf Stellenanzeige mit Bewerbung des users
        return true;
    }
}
