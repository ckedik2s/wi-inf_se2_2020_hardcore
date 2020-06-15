package org.HardCore.model.dao;

import org.HardCore.model.objects.dto.User;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class RegisterDAO extends AbstractDAO {
    private static RegisterDAO dao = null;

    private RegisterDAO() {

    }

    public static RegisterDAO getInstance() {
        if (dao == null) {
            dao = new RegisterDAO();
        }
        return dao;
    }

    public boolean addUser(User user) {
        String sql = "INSERT INTO collhbrs.user VALUES (default,?,?)";
        PreparedStatement statement = this.getPreparedStatement(sql);

        try {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean addStudent(User user) {
        String sql = "INSERT INTO collhbrs.student(id) VALUES (?)";
        PreparedStatement statement = this.getPreparedStatement(sql);

        try {
            statement.setInt(1, user.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean addUnternehmen(User user) {
        String sql = "INSERT INTO collhbrs.unternehmen(id) VALUES (?)";
        PreparedStatement statement = this.getPreparedStatement(sql);

        try {
            statement.setInt(1, user.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public void deleteUser(User user) {
        Statement statement = this.getStatement();
        try {

            //Lösche Student
            if (user.hasRole("Student")) {
            statement.execute("DELETE " +
                        "FROM collhbrs.student s " +
                        "USING collhbrs.user u, collhbrs.user_to_rolle utr " +
                        "WHERE u.id = \'" + user.getId() + "\' AND u.id = s.id AND u.id = utr.id;");
            }
            //Lösche Unternehmen
            if (user.hasRole("Unternehmen")) {
            statement.execute(
                    "DELETE " +
                            "FROM collhbrs.unternehmen un " +
                            "USING collhbrs.user u, collhbrs.user_to_rolle utr " +
                            "WHERE u.id = \'" + user.getId() + "\' AND u.id = un.id AND u.id = utr.id;");
            }
            //Lösche USER-To-Rolle Eintrag
            statement.execute("DELETE " +
                    "FROM collhbrs.user_to_rolle utr " + "WHERE utr.id =\'" + user.getId() + "\';");
            //Lösche User
            statement.execute("DELETE " +
                    "FROM collhbrs.user u " +
                    "WHERE u.id = \'" + user.getId() + "\';");

            System.out.println("Löschen erfolgreich");
        } catch (SQLException ex) {
            Logger.getLogger((RegisterDAO.class.getName())).log(Level.SEVERE, null, ex);
            System.out.println("Löschen nicht erfolgreich");
        }
    }
}
