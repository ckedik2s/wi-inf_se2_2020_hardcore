package org.HardCore.model.dao;

import org.HardCore.model.objects.dto.Unternehmen;
import org.HardCore.model.objects.dto.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UnternehmenDAO extends AbstractDAO {

    private static UnternehmenDAO dao = null;

    private UnternehmenDAO() {

    }

    public static UnternehmenDAO getInstance() {
        if (dao == null) {
            dao = new UnternehmenDAO();
        }
        return dao;
    }

    public boolean updateUnternehmen(Unternehmen unternehmen) {
        String sql = "UPDATE collhbrs.unternehmen " +
                "SET name_unternehmen = ?, ansprechpartner = ?, strasse = ?, plz = ?, " +
                "hausnr = ?, zusatz = ?, ort = ?, branche = ? " +
                "WHERE collhbrs.unternehmen.id = ? ;";

        PreparedStatement statement = this.getPreparedStatement(sql);
        try {
            statement.setString(1, unternehmen.getName());
            statement.setString(2, unternehmen.getAnsprechpartner());
            statement.setString(3, unternehmen.getStrasse());
            statement.setInt(4, unternehmen.getPlz());
            statement.setInt(5, unternehmen.getHaus_nr());
            statement.setString(6, unternehmen.getZusatz());
            statement.setString(7, unternehmen.getOrt());
            statement.setString(8, unternehmen.getBranche());
            statement.setInt(9, unternehmen.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public Unternehmen getAllDataUnternehmen(User user) {
        Statement statement = this.getStatement();
        ResultSet rs = null;
        System.out.println(user.getId());
        try {
            rs = statement.executeQuery("SELECT * " +
                    "FROM collhbrs.unternehmen " +
                    "WHERE collhbrs.unternehmen.id = \'" + user.getId() + "\';");

        } catch (SQLException ex) {
            Logger.getLogger((UnternehmenDAO.class.getName())).log(Level.SEVERE, null, ex);
            return null;
        }
        Unternehmen un = new Unternehmen(user);
        try {
            while (rs.next()) {

                un.setName(rs.getString(2));
                un.setAnsprechpartner(rs.getString(3));
                un.setStrasse(rs.getString(4));
                un.setPlz((Integer) rs.getInt(5));
                un.setHaus_nr((Integer) rs.getInt(6));
                un.setZusatz(rs.getString(7));
                un.setOrt(rs.getString(8));
                un.setBranche(rs.getString(9));

            }

        } catch (SQLException ex) {
            Logger.getLogger((UnternehmenDAO.class.getName())).log(Level.SEVERE, null, ex);
            return null;
        }
        return un;
    }
}
