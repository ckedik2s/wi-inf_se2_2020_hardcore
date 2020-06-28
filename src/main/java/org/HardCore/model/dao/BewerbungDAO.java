package org.HardCore.model.dao;

import com.vaadin.ui.Notification;
import org.HardCore.model.objects.dto.BewerbungDTO;
import org.HardCore.model.objects.dto.StudentDTO;
import org.HardCore.process.exceptions.BewerbungException;
import org.HardCore.process.exceptions.DatabaseException;
import org.HardCore.services.db.JDBCConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public BewerbungDTO getBewerbung(int id_bewerbung) throws DatabaseException, SQLException {
        String sql = "SELECT id_bewerbung, freitext " +
                "FROM collhbrs.bewerbung " +
                "WHERE id_bewerbung = ?";
        PreparedStatement statement = JDBCConnection.getInstance().getPreparedStatement(sql);
        ResultSet rs = null;
        BewerbungDTO bewerbungDTO = null;
        try {
            statement.setInt(1, id_bewerbung);
            rs = statement.executeQuery();
            if (rs == null) {
                throw new BewerbungException();
            }
            if (rs.next()) {
                bewerbungDTO = new BewerbungDTO();
                bewerbungDTO.setId(id_bewerbung);
                bewerbungDTO.setFreitext(rs.getString(2));
            }
        } catch (SQLException e) {
            Notification.show("Es ist ein SQL-Fehler aufgetreten. Bitte informieren Sie einen Administrator!");
        } catch (BewerbungException e) {
            Notification.show("Eintrag nicht vorhanden!");
        } finally {
            assert rs != null;
            rs.close();
        }
        return bewerbungDTO;
    }

    public List<BewerbungDTO> getBewerbungenForStudent(StudentDTO studentDTO) throws SQLException {
        String sql = "SELECT id_bewerbung, freitext " +
                "FROM collhbrs.bewerbung " +
                "WHERE id = ? ;";
        List<BewerbungDTO> list = new ArrayList<>();
        PreparedStatement statement = this.getPreparedStatement(sql);
        ResultSet rs = null;
        try {
            statement.setInt(1, studentDTO.getId());
            rs = statement.executeQuery();
            if (rs == null) {
                throw new BewerbungException();
            }
        } catch (SQLException ex) {
            Notification.show("Es ist ein SQL-Fehler aufgetreten. Bitte informieren Sie einen Administrator!");
        } catch (BewerbungException e) {
            Notification.show("Eintrag nicht vorhanden!");
        }
        BewerbungDTO bewerbungDTO = null;
        try {
            while (rs.next()) {
                bewerbungDTO = new BewerbungDTO();
                bewerbungDTO.setId(rs.getInt(1));
                bewerbungDTO.setFreitext(rs.getString(2));
                list.add(bewerbungDTO);

            }
        } catch (SQLException ex) {
            Notification.show("Es ist ein SQL-Fehler aufgetreten. Bitte informieren Sie einen Administrator!");
        }
        finally{
            rs.close();
        }
        return list;
    }

    public boolean createBewerbung(String text, StudentDTO studentDTO) {
        String sql = "INSERT INTO collhbrs.bewerbung (id, freitext) " +
                "VALUES (?, ?); ";
        PreparedStatement statement = this.getPreparedStatement(sql);
        try {
            statement.setInt(1, studentDTO.getId());
            statement.setString(2, text);
            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        }

    }

    public boolean deleteBewerbung(BewerbungDTO bewerbungDTO) {
        String sql = "DELETE " +
                "FROM collhbrs.bewerbung " +
                "WHERE id_bewerbung = ?";
        PreparedStatement statement = this.getPreparedStatement(sql);
        try {
            statement.setInt(1, bewerbungDTO.getId());
            statement.executeUpdate();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger((BewerbungDAO.class.getName())).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
