package org.HardCore.model.dao;

import org.HardCore.model.objects.dto.Student;
import org.HardCore.model.objects.dto.User;
import org.HardCore.services.util.Roles;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class StudentDAO extends AbstractDAO{

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
}
