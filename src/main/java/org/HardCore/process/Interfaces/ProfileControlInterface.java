package org.HardCore.process.Interfaces;

import org.HardCore.model.objects.dto.BewerbungDTO;
import org.HardCore.model.objects.dto.StudentDTO;
import org.HardCore.model.objects.dto.UnternehmenDTO;
import org.HardCore.model.objects.dto.UserDTO;
import org.HardCore.process.exceptions.ProfileException;

import java.util.List;

public interface ProfileControlInterface {

    void updateStudentData(StudentDTO studentDTO) throws ProfileException;

    void updateUnternehmenData(UnternehmenDTO unternehmenDTO) throws ProfileException;

    StudentDTO getStudent(UserDTO userDTO);

    UnternehmenDTO getUnternehmen(UserDTO userDTO);

    void setBewerbung(String text, StudentDTO studentDTO) throws ProfileException;

    List<BewerbungDTO> getBewerbung(StudentDTO studentDTO);
}
