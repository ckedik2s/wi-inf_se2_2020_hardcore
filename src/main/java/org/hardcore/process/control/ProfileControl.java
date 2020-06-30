package org.hardcore.process.control;

import org.hardcore.model.dao.BewerbungDAO;
import org.hardcore.model.dao.StudentDAO;
import org.hardcore.model.dao.UnternehmenDAO;
import org.hardcore.model.objects.dto.BewerbungDTO;
import org.hardcore.model.objects.dto.StudentDTO;
import org.hardcore.model.objects.dto.UnternehmenDTO;
import org.hardcore.model.objects.dto.UserDTO;
import org.hardcore.process.Interfaces.ProfileControlInterface;
import org.hardcore.process.exceptions.ProfileException;

import java.sql.SQLException;
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

    public StudentDTO getStudent(UserDTO userDTO) throws SQLException {
        return StudentDAO.getInstance().getAllDataStudent(userDTO);
    }

    public UnternehmenDTO getUnternehmen(UserDTO userDTO) throws SQLException {
        return UnternehmenDAO.getInstance().getAllDataUnternehmen(userDTO);
    }

    public void setBewerbung(String text, StudentDTO studentDTO) throws ProfileException {
        boolean result =  BewerbungDAO.getInstance().createBewerbung(text, studentDTO);
        if (result) {
            return;
        }
        throw new ProfileException();
    }

    public List<BewerbungDTO> getBewerbung(StudentDTO studentDTO) throws SQLException {
        return BewerbungDAO.getInstance().getBewerbungenForStudent(studentDTO);
    }
}
