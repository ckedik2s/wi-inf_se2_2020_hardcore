package org.HardCore.process.control;

import org.HardCore.model.dao.StudentDAO;
import org.HardCore.model.dao.UnternehmenDAO;
import org.HardCore.model.objects.dto.Student;
import org.HardCore.model.objects.dto.Unternehmen;
import org.HardCore.model.objects.dto.User;

public class ProfileControl {


    public static boolean updateStudentData(Student student) {
        return StudentDAO.getInstance().updateStudent(student);
    }

    public static boolean updateUnternehmenData(Unternehmen unternehmen) {
        return UnternehmenDAO.getInstance().updateUnternehmen(unternehmen);
    }

    public static Student getStudent(User user) {
        return StudentDAO.getInstance().getAllDataStudent(user);
    }

    public static Unternehmen getUnternehmen(User user) {
        return UnternehmenDAO.getInstance().getAllDataUnternehmen(user);
    }
}
