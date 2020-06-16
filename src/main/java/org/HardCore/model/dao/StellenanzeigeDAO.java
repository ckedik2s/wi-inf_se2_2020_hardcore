package org.HardCore.model.dao;

import org.HardCore.model.objects.dto.StellenanzeigeDetail;
import org.HardCore.model.objects.dto.User;
import org.HardCore.model.objects.entities.Stellenanzeige;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

    public List<StellenanzeigeDetail> getAnzeigenForUser(User user) {
        Statement statement = this.getStatement();
        ResultSet rs = null;

        try {
            rs = statement.executeQuery("SELECT id_anzeige, beschreibung, art, name, zeitraum, branche, studiengang " +
                    "FROM collhbrs.stellenanzeige " +
                    "WHERE id = \'" + user.getId() + "\'");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (rs == null) {
            return null;
        }

        List<StellenanzeigeDetail> list = new ArrayList<>();
        StellenanzeigeDetail stellenanzeigeDetail = null;

        try {
            while (rs.next()) {
                stellenanzeigeDetail = new StellenanzeigeDetail();
                stellenanzeigeDetail.setId_anzeige(rs.getInt(1));
                stellenanzeigeDetail.setBeschreibung(rs.getString(2));
                stellenanzeigeDetail.setArt(rs.getString(3));
                stellenanzeigeDetail.setName(rs.getString(4));
                stellenanzeigeDetail.setZeitraum(rs.getDate(5).toLocalDate());
                stellenanzeigeDetail.setBranche(rs.getString(6));
                stellenanzeigeDetail.setStudiengang(rs.getString(7));
                list.add(stellenanzeigeDetail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //Erstellt eine neue Stellenanzeige in der Datenbank
    public boolean createStellenanzeige(Stellenanzeige stellenanzeige, User user) {
        String sql = "INSERT INTO collhbrs.stellenanzeige(id, beschreibung, art, name, zeitraum,branche, studiengang)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement statement = this.getPreparedStatement(sql);

        try {
            statement.setInt(1, user.getId());
            statement.setString(2, stellenanzeige.getBeschreibung());
            statement.setString(3, stellenanzeige.getArt());
            statement.setString(4, stellenanzeige.getName());
            statement.setObject(5, stellenanzeige.getZeitraum());
            statement.setString(6, stellenanzeige.getBranche());
            statement.setString(7, stellenanzeige.getStudiengang());
            statement.executeUpdate();
            return true;

        } catch (SQLException ex) {
            return false;
        }

    }

    //Verändert eine bestehende Stellenanzeige in der Datenbank
    public boolean updateStellenanzeige(Stellenanzeige stellenanzeige) {
        String sql = "UPDATE collhbrs.stellenanzeige " +
                "SET beschreibung = ?, art = ?,  name = ?, zeitraum = ?, branche = ?, studiengang = ?  " +
                "WHERE collhbrs.stellenanzeige.id_anzeige = ? ;";
        PreparedStatement statement = this.getPreparedStatement(sql);
        try {
            statement.setString(1, stellenanzeige.getBeschreibung());
            statement.setString(2, stellenanzeige.getArt());
            statement.setString(3, stellenanzeige.getName());
            statement.setObject(4, stellenanzeige.getZeitraum());
            statement.setString(5, stellenanzeige.getBranche());
            statement.setString(6, stellenanzeige.getStudiengang());
            statement.setInt(7, stellenanzeige.getId_anzeige());
            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }


    //Löscht eine Stellenanzeige aus der Datenbank
    public boolean deleteStellenanzeige(Stellenanzeige stellenanzeige) {
        Statement statement = this.getStatement();
        try {
            statement.executeQuery("DELETE " +
                    "FROM collhbrs.stellenanzeige " +
                    "WHERE collhbrs.stellenanzeige.id_anzeige = \'" + stellenanzeige.getId_anzeige() + "\';");
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public List<StellenanzeigeDetail> getAnzeigenForSearch(String suchtext) {
        Statement statement = this.getStatement();
        ResultSet rs = null;

        try {
            rs = statement.executeQuery("SELECT id_anzeige, beschreibung, art, name, zeitraum, branche, studiengang " +
                    "FROM collhbrs.stellenanzeige " +
                    "WHERE name like \'%" + suchtext + "%\'");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (rs == null) {
            return null;
        }

        List<StellenanzeigeDetail> list = new ArrayList<>();
        StellenanzeigeDetail stellenanzeigeDetail = null;

        try {
            while (rs.next()) {
                stellenanzeigeDetail = new StellenanzeigeDetail();
                stellenanzeigeDetail.setId_anzeige(rs.getInt(1));
                stellenanzeigeDetail.setBeschreibung(rs.getString(2));
                stellenanzeigeDetail.setArt(rs.getString(3));
                stellenanzeigeDetail.setName(rs.getString(4));
                stellenanzeigeDetail.setZeitraum(rs.getDate(5).toLocalDate());
                stellenanzeigeDetail.setBranche(rs.getString(6));
                stellenanzeigeDetail.setStudiengang(rs.getString(7));
                list.add(stellenanzeigeDetail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}

