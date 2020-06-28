package org.HardCore.model.dao;

import org.HardCore.model.objects.dto.RoleDTO;
import org.HardCore.model.objects.dto.UserDTO;
import org.HardCore.services.util.Roles;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDAO extends AbstractDAO{
    private static RoleDAO dao = null;

    private RoleDAO() {

    }

    public static RoleDAO getInstance() {
        if (dao == null) {
            dao = new RoleDAO();
        }
        return dao;
    }

    public List<RoleDTO> getRolesForUser(UserDTO userDTO) {
        String sql = "SELECT rolle " +
                "FROM collhbrs.user_to_rolle " +
                "WHERE id = ? ";
        PreparedStatement statement = getPreparedStatement(sql);

        ResultSet rs = null;

        try {
            statement.setInt(1,userDTO.getId());
            rs = statement.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        if (rs == null) {
            return null;
        }

        List<RoleDTO> liste = new ArrayList<>();
        RoleDTO role = null;

        try {
            while (rs.next()) {
                role = new RoleDTO();
                role.setBezeichnung(rs.getString(1));
                liste.add(role);
            }
        } catch (SQLException ex) {
            return null;
        }
        return liste;
    }

    public boolean setRolesForStudent(UserDTO userDTO) {
        String sql = "INSERT INTO collhbrs.user_to_rolle VALUES (?,?)";
        PreparedStatement statement = this.getPreparedStatement(sql);

        try {
            statement.setInt(1, userDTO.getId());
            statement.setString(2, Roles.STUDENT);
            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    public boolean setRolesForUnternehmen(UserDTO userDTO) {
        String sql = "INSERT INTO collhbrs.user_to_rolle VALUES (?,?)";
        PreparedStatement statement = this.getPreparedStatement(sql);

        try {
            statement.setInt(1, userDTO.getId());
            statement.setString(2, Roles.UNTERNEHMEN);
            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
 }
