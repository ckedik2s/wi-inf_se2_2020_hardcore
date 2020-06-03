package org.HardCore.process.control;

import com.vaadin.ui.UI;
import org.HardCore.gui.ui.MyUI;
import org.HardCore.gui.windows.ConfirmationWindow;
import org.HardCore.model.dao.RegisterDAO;
import org.HardCore.model.dao.StudentDAO;
import org.HardCore.model.objects.dto.Student;
import org.HardCore.model.objects.dto.Unternehmen;
import org.HardCore.model.objects.dto.User;
import org.HardCore.process.control.exceptions.DatabaseException;
import org.HardCore.process.control.exceptions.EmailInUseException;
import org.HardCore.process.control.exceptions.EmptyFieldException;
import org.HardCore.process.control.exceptions.NoEqualPasswordException;
import org.HardCore.services.db.JDBCConnection;
import org.HardCore.services.util.Views;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class ProfileControl {


    public static void updateStudentData(Student student, String new_anrede, String new_vorname, String new_name, String new_hochschule, String new_semester, LocalDate new_gebDatum, String new_kenntnisse, String new_studiengang) throws DatabaseException{
        StudentDAO stu_dao = StudentDAO.getInstance();
        stu_dao.setStudentAnrede(student, new_anrede);
        stu_dao.setStudentVorname(student,new_vorname);
        stu_dao.setStudentName(student, new_name);
        stu_dao.setStudentHochschule(student, new_hochschule);
        stu_dao.setStudentSemester(student,new_semester);
        stu_dao.setStudentGebDatum(student, new_gebDatum);
        stu_dao.setStudentKenntnisse(student, new_kenntnisse);
        stu_dao.setStudentStudiengang(student, new_studiengang);
    }
    public static void updateUnternehmenData(Unternehmen unternehmen, String firmenname) throws DatabaseException{
        unternehmen.setName(firmenname);
    }

    public static void deleteUser(User user){
        RegisterDAO.getInstance().deleteUser(user);
        ( (MyUI)UI.getCurrent() ).setUser(null);
        UI.getCurrent().getNavigator().navigateTo(Views.MAIN);
    }
}
