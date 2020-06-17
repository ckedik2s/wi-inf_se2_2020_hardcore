package org.HardCore.process.control;

import org.HardCore.model.dao.BewerbungDAO;
import org.HardCore.model.dao.StudentDAO;
import org.HardCore.model.dao.UnternehmenDAO;
import org.HardCore.model.objects.dto.Bewerbung;
import org.HardCore.model.objects.dto.Student;
import org.HardCore.model.objects.dto.Unternehmen;
import org.HardCore.model.objects.dto.User;

public class ProfileControl {
    private static ProfileControl profileControl = null;

    private ProfileControl() {
    }

    public static ProfileControl getInstance() {
        if (profileControl == null) {
            profileControl = new ProfileControl();
        }
        return profileControl;
    }


    public boolean updateStudentData(Student student) {
        return StudentDAO.getInstance().updateStudent(student);
    }

    public boolean updateUnternehmenData(Unternehmen unternehmen) {
        return UnternehmenDAO.getInstance().updateUnternehmen(unternehmen);
    }

    public Student getStudent(User user) {
        return StudentDAO.getInstance().getAllDataStudent(user);
    }

    public Unternehmen getUnternehmen(User user) {
        return UnternehmenDAO.getInstance().getAllDataUnternehmen(user);
    }

    public boolean setBewerbung(String text, Student student) {
        return BewerbungDAO.getInstance().setBewerbung(text, student);
    }

    public Bewerbung getBewerbung(Student student) {
        return BewerbungDAO.getInstance().getBewerbung(student);
    }
}
