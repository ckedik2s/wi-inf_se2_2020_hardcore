package org.HardCore.process.control;

import org.HardCore.model.dao.StudentDAO;
import org.HardCore.model.dao.UnternehmenDAO;
import org.HardCore.model.objects.dto.Student;
import org.HardCore.model.objects.dto.Unternehmen;
import org.HardCore.model.objects.dto.User;
import org.HardCore.process.control.exceptions.DatabaseException;

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
    public static void updateUnternehmenData(Unternehmen unternehmen, String firmennameValue, String ansprechpartnerValue, String strasseValue, String plzValue, String haus_nrValue, String zusatzValue, String ortValue, String brancheValue) throws DatabaseException{
        UnternehmenDAO unt_dao = UnternehmenDAO.getInstance();
        unt_dao.setUnternehmenFirmenname(unternehmen,firmennameValue);
        unt_dao.setUnternehmenAnsprechpartner(unternehmen,ansprechpartnerValue);
        unt_dao.setUnternehmenStrasse(unternehmen,strasseValue);
        unt_dao.setUnternehmenPLZ(unternehmen,plzValue);
        unt_dao.setUnternehmenHausnr(unternehmen,haus_nrValue);
        unt_dao.setUnternehmenZusatz(unternehmen,zusatzValue);
        unt_dao.setUnternehmenOrt(unternehmen,ortValue);
        unt_dao.setUnternehmenBranche(unternehmen,brancheValue);

    }
    public static Student getStudent(User user){
        return StudentDAO.getInstance().getAllDataStudent(user);
    }

    public static Unternehmen getUnternehmen(User user){ return UnternehmenDAO.getInstance().getAllDataUnternehmen(user);}
}
