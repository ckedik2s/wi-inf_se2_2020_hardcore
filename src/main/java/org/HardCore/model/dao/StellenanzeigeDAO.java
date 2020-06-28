package org.HardCore.model.dao;

import com.vaadin.ui.Notification;
import org.HardCore.model.objects.dto.BewerbungDTO;
import org.HardCore.model.objects.dto.StellenanzeigeDetail;
import org.HardCore.model.objects.dto.StudentDTO;
import org.HardCore.model.objects.dto.UserDTO;
import org.HardCore.model.objects.entities.Stellenanzeige;
import org.HardCore.process.exceptions.DatabaseException;
import org.HardCore.process.proxy.StellenanzeigeControlProxy;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StellenanzeigeDAO extends AbstractDAO {
    private static StellenanzeigeDAO dao = null;

    private StellenanzeigeDAO() {
    }

    public static StellenanzeigeDAO getInstance() {
        if (dao == null) {
            dao = new StellenanzeigeDAO();
        }
        return dao;
    }

    public List<StellenanzeigeDetail> getStellenanzeigenForUnternehmen(UserDTO userDTO) throws SQLException {
        String sql = "SELECT id_anzeige, beschreibung, art, name, zeitraum, branche, studiengang, ort " +
                "FROM collhbrs.stellenanzeige " +
                "WHERE id = ? ;";
        PreparedStatement statement = this.getPreparedStatement(sql);
        ResultSet rs = null;

        try {
            statement.setInt(1,userDTO.getId());
            rs = statement.executeQuery();

        } catch (SQLException e) {
            Notification.show("Es ist ein SQL-Fehler aufgetreten. Bitte informieren Sie einen Administrator!");
        }
        List<StellenanzeigeDetail> list = new ArrayList<>();
        StellenanzeigeDetail stellenanzeigeDetail;

        try {
            while (true) {
                assert rs != null;
                if (!rs.next()) break;
                stellenanzeigeDetail = new StellenanzeigeDetail();
                stellenanzeigeDetail.setId_anzeige(rs.getInt(1));
                stellenanzeigeDetail.setBeschreibung(rs.getString(2));
                stellenanzeigeDetail.setArt(rs.getString(3));
                stellenanzeigeDetail.setName(rs.getString(4));
                stellenanzeigeDetail.setZeitraum(rs.getDate(5).toLocalDate());
                stellenanzeigeDetail.setBranche(rs.getString(6));
                stellenanzeigeDetail.setStudiengang(rs.getString(7));
                stellenanzeigeDetail.setOrt(rs.getString(8));
                try {
                    stellenanzeigeDetail.setAnzahl_bewerber(StellenanzeigeControlProxy.getInstance().getAnzahlBewerber(stellenanzeigeDetail));
                } catch (DatabaseException e) {
                    Notification.show("Es ist ein Datenbankfehler aufgetreten. Bitte informieren Sie einen Administrator!");
                }
                list.add(stellenanzeigeDetail);
            }
        } catch (SQLException e) {
            Notification.show("Es ist ein SQL-Fehler aufgetreten. Bitte informieren Sie einen Administrator!");
        } finally {
            assert rs != null;
            rs.close();
        }
        return list;
    }


    //Erstellt eine neue Stellenanzeige in der Datenbank
    public boolean createStellenanzeige(Stellenanzeige stellenanzeige, UserDTO userDTO) {
        String sql = "INSERT INTO collhbrs.stellenanzeige(id, beschreibung, art, name, zeitraum, branche, studiengang, ort)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement statement = this.getPreparedStatement(sql);

        try {
            statement.setInt(1, userDTO.getId());
            statement.setString(2, stellenanzeige.getBeschreibung());
            statement.setString(3, stellenanzeige.getArt());
            statement.setString(4, stellenanzeige.getName());
            statement.setObject(5, stellenanzeige.getZeitraum());
            statement.setString(6, stellenanzeige.getBranche());
            statement.setString(7, stellenanzeige.getStudiengang());
            statement.setString(8, stellenanzeige.getOrt());
            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        }

    }

    //Verändert eine bestehende Stellenanzeige in der Datenbank
    public boolean updateStellenanzeige(Stellenanzeige stellenanzeige) {
        String sql = "UPDATE collhbrs.stellenanzeige " +
                "SET beschreibung = ?, art = ?,  name = ?, zeitraum = ?, branche = ?, studiengang = ?, ort = ?  " +
                "WHERE collhbrs.stellenanzeige.id_anzeige = ? ;";
        PreparedStatement statement = this.getPreparedStatement(sql);
        try {
            statement.setString(1, stellenanzeige.getBeschreibung());
            statement.setString(2, stellenanzeige.getArt());
            statement.setString(3, stellenanzeige.getName());
            statement.setObject(4, stellenanzeige.getZeitraum());
            statement.setString(5, stellenanzeige.getBranche());
            statement.setString(6, stellenanzeige.getStudiengang());
            statement.setString(7, stellenanzeige.getOrt());
            statement.setInt(8, stellenanzeige.getId_anzeige());
            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }


    //Löscht eine Stellenanzeige aus der Datenbank
    public boolean deleteStellenanzeige(Stellenanzeige stellenanzeige) {
        String sql = "DELETE " +
                "FROM collhbrs.stellenanzeige " +
                "WHERE collhbrs.stellenanzeige.id_anzeige = ? ;";
        PreparedStatement statement = this.getPreparedStatement(sql);
        try {
            statement.setInt(1,stellenanzeige.getId_anzeige());
            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public List<StellenanzeigeDetail> getStellenanzeigenForSearch(String suchtext, String filter) throws SQLException {
        filter = filter.toLowerCase();
        PreparedStatement statement;
        ResultSet rs = null;
        if(suchtext.equals("")){
            String sql = "SELECT id_anzeige, beschreibung, art, name, zeitraum, branche, studiengang, ort " +
            "FROM collhbrs.stellenanzeige ;";
            statement = this.getPreparedStatement(sql);
            try {
                rs = statement.executeQuery();
            } catch (SQLException e) {
                Notification.show("Es ist ein SQL-Fehler aufgetreten. Bitte informieren Sie einen Administrator!");
            }
        }
        else {
            String sql = "SELECT id_anzeige, beschreibung, art, name, zeitraum, branche, studiengang, ort " +
                    "FROM collhbrs.stellenanzeige " +
                    "WHERE " + filter +" like ? ;";
            statement = this.getPreparedStatement(sql);


            try {
                statement.setString(1, "%" + suchtext + "%");
                rs = statement.executeQuery();
            } catch (SQLException e) {
                Notification.show("Es ist ein SQL-Fehler aufgetreten. Bitte informieren Sie einen Administrator!");
            }
        }

        List<StellenanzeigeDetail> list = new ArrayList<>();

        assert rs != null;
        buildList(rs, list);
        return list;
    }

    //Zeigt alle Stellenanzeigen an, auf die sich ein Student beworben hat
    public List<StellenanzeigeDetail> getStellenanzeigeforStudent(StudentDTO studentDTO) throws SQLException {
        String sql = "SELECT id_anzeige, beschreibung, art, name, zeitraum, branche, studiengang, ort " +
                    "FROM collhbrs.stellenanzeige " +
                    "WHERE id_anzeige = ( SELECT id_anzeige " +
                    "FROM collhbrs.bewerbung_to_stellenanzeige " +
                    "WHERE id_bewerbung = ? );";
        PreparedStatement statement = this.getPreparedStatement(sql);
        ResultSet rs = null;
        List<BewerbungDTO> list = BewerbungDAO.getInstance().getBewerbungenForStudent(studentDTO);
        List<StellenanzeigeDetail> listStellenanzeige = new ArrayList<>();
        for (BewerbungDTO bewerbungDTO : list) {
            int id_bewerbung = bewerbungDTO.getId();
            try {
                statement.setInt(1,id_bewerbung);
                rs = statement.executeQuery();
            } catch (SQLException e) {
                Notification.show("Es ist ein SQL-Fehler aufgetreten. Bitte informieren Sie einen Administrator!");
            }
            assert rs != null;
            buildList(rs, listStellenanzeige);
        }
        return listStellenanzeige;
    }

    private void buildList(ResultSet rs, List<StellenanzeigeDetail> listStellenanzeige) throws SQLException {
        StellenanzeigeDetail stellenanzeigeDetail;
        try {
            while (true) {
                assert rs != null;
                if (!rs.next()) break;
                stellenanzeigeDetail = new StellenanzeigeDetail();
                stellenanzeigeDetail.setId_anzeige(rs.getInt(1));
                stellenanzeigeDetail.setBeschreibung(rs.getString(2));
                stellenanzeigeDetail.setArt(rs.getString(3));
                stellenanzeigeDetail.setName(rs.getString(4));
                stellenanzeigeDetail.setZeitraum(rs.getDate(5).toLocalDate());
                stellenanzeigeDetail.setBranche(rs.getString(6));
                stellenanzeigeDetail.setStudiengang(rs.getString(7));
                stellenanzeigeDetail.setOrt(rs.getString(8));
                listStellenanzeige.add(stellenanzeigeDetail);
            }
        } catch (SQLException e) {
            Notification.show("Es ist ein SQL-Fehler aufgetreten. Bitte informieren Sie einen Administrator!");
        } finally {
            assert rs != null;
            rs.close();
        }
    }


}


