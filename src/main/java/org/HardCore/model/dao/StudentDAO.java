package org.HardCore.model.dao;

import org.HardCore.model.objects.dto.Student;
import org.HardCore.model.objects.dto.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
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

    public boolean setStudentAnrede(Student student, String anrede) {
        String sql = "UPDATE collhbrs.student set anrede = \'" + anrede + "\' where collhbrs.student.id = \'" + student.getId() + "\';";
        PreparedStatement statement = this.getPreparedStatement(sql);

        try {
            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }


    public boolean setStudentVorname(Student student, String vorname) {
        String sql = "UPDATE collhbrs.student set vorname = \'" + vorname + "\' where collhbrs.student.id = \'" + student.getId() + "\';";
        PreparedStatement statement = this.getPreparedStatement(sql);

        try {
            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean setStudentName(Student student, String name) {
        String sql = "UPDATE collhbrs.student set name = \'" + name + "\' where collhbrs.student.id = \'" + student.getId() + "\';";
        PreparedStatement statement = this.getPreparedStatement(sql);

        try {
            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean setStudentHochschule(Student student, String hochschule) {
        String sql = "UPDATE collhbrs.student set hochschule = \'" + hochschule + "\' where collhbrs.student.id = \'" + student.getId() + "\';";
        PreparedStatement statement = this.getPreparedStatement(sql);

        try {
            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean setStudentSemester(Student student, String semester) {
        String sql = "UPDATE collhbrs.student set semester = \'" + semester + "\' where collhbrs.student.id = \'" + student.getId() + "\';";
        PreparedStatement statement = this.getPreparedStatement(sql);

        try {
            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean setStudentGebDatum(Student student, LocalDate gebdatum) {
        String sql = "UPDATE collhbrs.student set gebdatum = \'" + gebdatum + "\' where collhbrs.student.id = \'" + student.getId() + "\';";
        PreparedStatement statement = this.getPreparedStatement(sql);

        try {
            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean setStudentKenntnisse(Student student, String kenntnisse) {
        String sql = "UPDATE collhbrs.student set kenntnisse = \'" + kenntnisse + "\' where collhbrs.student.id = \'" + student.getId() + "\';";
        PreparedStatement statement = this.getPreparedStatement(sql);

        try {
            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean setStudentStudiengang(Student student, String studiengang) {
        String sql = "UPDATE collhbrs.student set studiengang = \'" + studiengang + "\' where collhbrs.student.id = \'" + student.getId() + "\';";
        PreparedStatement statement = this.getPreparedStatement(sql);

        try {
            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public Student getAllDataStudent(User user) {

        Statement statement = this.getStatement();
        ResultSet rs = null;
        System.out.println(user.getId());
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
