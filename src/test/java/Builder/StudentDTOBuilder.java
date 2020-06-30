package Builder;

import org.hardcore.model.objects.dto.StudentDTO;
import org.hardcore.model.objects.dto.UserDTO;

public class StudentDTOBuilder {

    private static StudentDTOBuilder builder = null;
    private StudentDTO studentDTO;
    private UserDTO userDTO;

    public static StudentDTOBuilder getInstance() {
        if(builder==null) {
            builder = new StudentDTOBuilder();
        }
        return builder;
    }
    public StudentDTOBuilder withAnrede(String anrede) {
        studentDTO.setAnrede(anrede);
        return this;	//return um chain zu bilden
    }
    public StudentDTOBuilder withNoAnrede() {
        studentDTO.setAnrede("");
        return this;
    }
    public StudentDTOBuilder withHochschule(String hochsch) {
        studentDTO.setHochschule(hochsch);
        return this;	//return um chain zu bilden
    }
    public StudentDTOBuilder withNoHochschule() {
        studentDTO.setHochschule("");
        return this;	//return um chain zu bilden
    }
    public StudentDTOBuilder withKenntnisse(String kennt) {
        studentDTO.setKenntnisse(kennt);
        return this; //return um chain zu bilden
    }
    public StudentDTOBuilder withNoKenntnisse() {
        studentDTO.setKenntnisse("");
        return this; //return um chain zu bilden
    }
    public StudentDTOBuilder withStudiengang(String studiengang) {
        studentDTO.setStudiengang(studiengang);
        return this;
    }
    public StudentDTOBuilder withNoStudiengang() {
        studentDTO.setStudiengang("");
        return this;
    }
    public StudentDTO thatsAll() {
        return new StudentDTO(userDTO);
    }
    public StudentDTO done() {
        return new StudentDTO(userDTO);
    }
    public StudentDTOBuilder createDefaultUser() {
        return this;
    }


}
