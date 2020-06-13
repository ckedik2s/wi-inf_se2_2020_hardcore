package org.HardCore.model.dao;

import org.HardCore.model.objects.dto.Student;
import org.HardCore.model.objects.dto.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentDAO extends AbstractDAO {

    private static StudentDAO dao = null;

    private StudentDAO() {

    }

    public static StudentDAO getInstance() {
        if (dao == null) {
            dao = new StudentDAO();
        }
        return dao;
    }

    public boolean updateStudent(Student student) {
        String sql = "UPDATE collhbrs.student " +
                "SET anrede = ?, vorname = ?, name = ?, hochschule = ?, " +
                "semester = ?, gebdatum = ?, kenntnisse = ?, studiengang = ?" +
                "WHERE collhbrs.student.id = ?;";
        PreparedStatement statement = this.getPreparedStatement(sql);
        try {
            statement.setString(1, student.getAnrede());
            statement.setString(2, student.getVorname());
            statement.setString(3, student.getName());
            statement.setString(4, student.getHochschule());
            statement.setInt(5, student.getSemester());
            statement.setObject(6, student.getGebDatum());
            statement.setString(7, student.getKenntnisse());
            statement.setString(8, student.getStudiengang());
            statement.setInt(9, student.getId());
            statement.executeUpdate();
            return true;

        } catch (SQLException ex) {
            return false;
        }
    }

    public Student getAllDataStudent(User user) {
        Statement statement = this.getStatement();
        ResultSet rs = null;
        try {
            rs = statement.executeQuery("SELECT * " +
                    "FROM collhbrs.student " +
                    "WHERE collhbrs.student.id = \'" + user.getId() + "\';");

        } catch (SQLException ex) {
            Logger.getLogger((StudentDAO.class.getName())).log(Level.SEVERE, null, ex);
            return null;
        }

        try {
            Student student = new Student(user);
            while (rs.next()) {
                student.setAnrede(rs.getString(2));
                student.setVorname(rs.getString(3));
                student.setName(rs.getString(4));
                student.setHochschule(rs.getString(5));
                student.setSemester((Integer) rs.getInt(6));
                student.setGebDatum(rs.getDate(7).toLocalDate());
                student.setKenntnisse(rs.getString(8));
                student.setStudiengang(rs.getString(9));
            }
            return student;
        } catch (SQLException ex) {
            Logger.getLogger((StudentDAO.class.getName())).log(Level.SEVERE, null, ex);
            return null;
        }


    }
}
