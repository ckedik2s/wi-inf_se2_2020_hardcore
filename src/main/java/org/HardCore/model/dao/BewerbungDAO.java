package org.HardCore.model.dao;

import org.HardCore.model.objects.dto.Bewerbung;
import org.HardCore.model.objects.dto.Student;
import org.HardCore.process.control.exceptions.DatabaseException;
import org.HardCore.services.db.JDBCConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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

    public Bewerbung getBewerbung(int id_bewerbung) throws DatabaseException {
        String sql = "SELECT id_bewerbung, freitext " +
                "FROM collhbrs.bewerbung " +
                "WHERE id_bewerbung = ?";
        PreparedStatement statement = JDBCConnection.getInstance().getPreparedStatement(sql);
        ResultSet rs = null;
        Bewerbung bewerbung = null;
        try {
            statement.setInt(1, id_bewerbung);
            rs = statement.executeQuery();
            if( rs.next() ) {
                bewerbung = new Bewerbung();
                bewerbung.setId(id_bewerbung);
                bewerbung.setFreitext(rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bewerbung;
    }

    public List<Bewerbung> getBewerbungenForStudent(Student student) {
        List<Bewerbung> list = new ArrayList<>();
        Statement statement = getStatement();
        ResultSet rs = null;
        try {
            rs = statement.executeQuery("SELECT id_bewerbung, freitext " +
                    "FROM collhbrs.bewerbung " +
                    "WHERE id =\'" + student.getId() + "\';");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        if (rs == null) {
            return null;
        }
        Bewerbung bewerbung = null;
        try {
            while (rs.next()) {
                bewerbung = new Bewerbung();
                bewerbung.setId(rs.getInt(1));
                bewerbung.setFreitext(rs.getString(2));
                list.add(bewerbung);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public boolean createBewerbung(String text, Student student) {
        String sql = "INSERT INTO collhbrs.bewerbung (id, freitext) " +
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

    public boolean deleteBewerbung(Bewerbung bewerbung) {
        String sql = "DELETE " +
                "FROM collhbrs.bewerbung " +
                "WHERE id_bewerbung = ?";
        PreparedStatement statement = this.getPreparedStatement(sql);
        try {
            statement.setInt(1, bewerbung.getId());
            statement.executeUpdate();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger((BewerbungDAO.class.getName())).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
