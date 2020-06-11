package org.HardCore.model.dao;

import org.HardCore.model.objects.dto.StellenanzeigeDetail;
import org.HardCore.model.objects.dto.User;
import org.HardCore.model.objects.entities.Stellenanzeige;

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
        StellenanzeigeDetail stellenanzeigeDetail= null;

        try {
            while( rs.next() ) {
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

    public boolean updateStellenanzeige(Stellenanzeige stellenanzeige) {
        //TODO
        return true;
    }

    public boolean deleteStellenanzeige(Stellenanzeige stellenanzeige) {
        //TODO
        return true;
    }
}
