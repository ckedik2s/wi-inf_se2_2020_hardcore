package org.HardCore.model.dao;

import org.HardCore.model.objects.dto.UserDTO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
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

    public boolean addUser(UserDTO userDTO) {
        String sql = "INSERT INTO collhbrs.user VALUES (default,?,?)";
        PreparedStatement statement = this.getPreparedStatement(sql);

        try {
            statement.setString(1, userDTO.getEmail());
            statement.setString(2, userDTO.getPassword());
            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean addStudent(UserDTO userDTO) {
        String sql = "INSERT INTO collhbrs.student(id) VALUES (?)";
        PreparedStatement statement = this.getPreparedStatement(sql);

        try {
            statement.setInt(1, userDTO.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean addUnternehmen(UserDTO userDTO) {
        String sql = "INSERT INTO collhbrs.unternehmen(id) VALUES (?)";
        PreparedStatement statement = this.getPreparedStatement(sql);

        try {
            statement.setInt(1, userDTO.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public void deleteUser(UserDTO userDTO) {
        Statement statement = this.getStatement();
        try {

            //Lösche Student
            if (userDTO.hasRole("Student")) {
            statement.execute("DELETE " +
                        "FROM collhbrs.student s " +
                        "USING collhbrs.user u, collhbrs.user_to_rolle utr " +
                        "WHERE u.id = \'" + userDTO.getId() + "\' AND u.id = s.id AND u.id = utr.id;");
            }
            //Lösche Unternehmen
            if (userDTO.hasRole("Unternehmen")) {
            statement.execute(
                    "DELETE " +
                            "FROM collhbrs.unternehmen un " +
                            "USING collhbrs.user u, collhbrs.user_to_rolle utr " +
                            "WHERE u.id = \'" + userDTO.getId() + "\' AND u.id = un.id AND u.id = utr.id;");
            }
            //Lösche USER-To-Rolle Eintrag
            statement.execute("DELETE " +
                    "FROM collhbrs.user_to_rolle utr " + "WHERE utr.id =\'" + userDTO.getId() + "\';");
            //Lösche User
            statement.execute("DELETE " +
                    "FROM collhbrs.user u " +
                    "WHERE u.id = \'" + userDTO.getId() + "\';");

            System.out.println("Löschen erfolgreich");
        } catch (SQLException ex) {
            Logger.getLogger((RegisterDAO.class.getName())).log(Level.SEVERE, null, ex);
            System.out.println("Löschen nicht erfolgreich");
        }
    }
}
