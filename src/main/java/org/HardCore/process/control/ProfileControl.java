package org.HardCore.process.control;

import org.HardCore.model.dao.BewerbungDAO;
import org.HardCore.model.dao.StudentDAO;
import org.HardCore.model.dao.UnternehmenDAO;
import org.HardCore.model.objects.dto.BewerbungDTO;
import org.HardCore.model.objects.dto.StudentDTO;
import org.HardCore.model.objects.dto.UnternehmenDTO;
import org.HardCore.model.objects.dto.UserDTO;
import org.HardCore.process.Interfaces.ProfileControlInterface;
import org.HardCore.process.exceptions.ProfileException;

import java.util.List;

public class ProfileControl implements ProfileControlInterface {
    private static ProfileControl profileControl = null;

    private ProfileControl() {
    }

    public static ProfileControl getInstance() {
        if (profileControl == null) {
            profileControl = new ProfileControl();
        }
        return profileControl;
    }


    public void updateStudentData(StudentDTO studentDTO) throws ProfileException {
        boolean result =  StudentDAO.getInstance().updateStudent(studentDTO);
        if (result) {
            return;
        }
        throw new ProfileException();
    }

    public void updateUnternehmenData(UnternehmenDTO unternehmenDTO) throws ProfileException {
        boolean result = UnternehmenDAO.getInstance().updateUnternehmen(unternehmenDTO);
        if (result) {
            return;
        }
        throw new ProfileException();
    }

    public StudentDTO getStudent(UserDTO userDTO) {
        return StudentDAO.getInstance().getAllDataStudent(userDTO);
    }

    public UnternehmenDTO getUnternehmen(UserDTO userDTO) {
        return UnternehmenDAO.getInstance().getAllDataUnternehmen(userDTO);
    }

    public void setBewerbung(String text, StudentDTO studentDTO) throws ProfileException {
        boolean result =  BewerbungDAO.getInstance().createBewerbung(text, studentDTO);
        if (result) {
            return;
        }
        throw new ProfileException();
    }

    public List<BewerbungDTO> getBewerbung(StudentDTO studentDTO) {
        return BewerbungDAO.getInstance().getBewerbungenForStudent(studentDTO);
    }
}
