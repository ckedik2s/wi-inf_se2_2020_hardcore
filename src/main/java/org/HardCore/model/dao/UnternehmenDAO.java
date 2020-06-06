package org.HardCore.model.dao;

import org.HardCore.model.objects.dto.Student;
import org.HardCore.model.objects.dto.Unternehmen;
import org.HardCore.model.objects.dto.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
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

    public boolean setUnternehmenFirmenname(Unternehmen unternehmen, String name_unternehmen) {
        String sql = "UPDATE collhbrs.unternehmen set name_unternehmen = \'" + name_unternehmen + "\' where collhbrs.unternehmen.id = \'" + unternehmen.getId() + "\';";
        PreparedStatement statement = this.getPreparedStatement(sql);

        try {
            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean setUnternehmenAnsprechpartner(Unternehmen unternehmen, String ansprechpartner) {
        String sql = "UPDATE collhbrs.unternehmen set ansprechpartner = \'" + ansprechpartner + "\' where collhbrs.unternehmen.id = \'" + unternehmen.getId() + "\';";
        PreparedStatement statement = this.getPreparedStatement(sql);

        try {
            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean setUnternehmenStrasse(Unternehmen unternehmen, String strasse) {
        String sql = "UPDATE collhbrs.unternehmen set strasse = \'" + strasse + "\' where collhbrs.unternehmen.id = \'" + unternehmen.getId() + "\';";
        PreparedStatement statement = this.getPreparedStatement(sql);

        try {
            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean setUnternehmenPLZ(Unternehmen unternehmen, String plz) {
        String sql = "UPDATE collhbrs.unternehmen set plz = \'" + plz + "\' where collhbrs.unternehmen.id = \'" + unternehmen.getId() + "\';";
        PreparedStatement statement = this.getPreparedStatement(sql);

        try {
            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean setUnternehmenHausnr(Unternehmen unternehmen, String hausnr) {
        String sql = "UPDATE collhbrs.unternehmen set hausnr = \'" + hausnr + "\' where collhbrs.unternehmen.id = \'" + unternehmen.getId() + "\';";
        PreparedStatement statement = this.getPreparedStatement(sql);

        try {
            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean setUnternehmenZusatz(Unternehmen unternehmen, String zusatz) {
        String sql = "UPDATE collhbrs.unternehmen set zusatz = \'" + zusatz + "\' where collhbrs.unternehmen.id = \'" + unternehmen.getId() + "\';";
        PreparedStatement statement = this.getPreparedStatement(sql);

        try {
            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean setUnternehmenOrt(Unternehmen unternehmen, String ort) {
        String sql = "UPDATE collhbrs.unternehmen set ort = \'" + ort + "\' where collhbrs.unternehmen.id = \'" + unternehmen.getId() + "\';";
        PreparedStatement statement = this.getPreparedStatement(sql);

        try {
            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean setUnternehmenBranche(Unternehmen unternehmen, String branche) {
        String sql = "UPDATE collhbrs.unternehmen set branche = \'" + branche + "\' where collhbrs.unternehmen.id = \'" + unternehmen.getId() + "\';";
        PreparedStatement statement = this.getPreparedStatement(sql);

        try {
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

                un.setFirmenname(rs.getString(2));
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
