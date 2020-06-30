package DTOTest;

import org.HardCore.model.objects.dto.StudentDTO;
import org.HardCore.model.objects.dto.UserDTO;
import org.junit.Test;

import static org.junit.Assert.*;

public class StudentDTOTest {

    UserDTO userDTO = new UserDTO();
    StudentDTO studentDTO = new StudentDTO(userDTO);

    @Test
    public void testVorname() {
        studentDTO.setVorname("Pat");
        assertEquals("Pat",studentDTO.getVorname());
    }
    @Test
    public void testName() {
        studentDTO.setName("Rick");
        assertEquals("Rick",studentDTO.getName());
    }
    @Test
    public void testId() {
        studentDTO.setId(1);
        assertEquals(1,studentDTO.getId());
    }
    @Test
    public void testEmail() {
        studentDTO.setEmail("lachs@t.de");
        assertEquals("lachs@t.de",studentDTO.getEmail());
    }
    @Test
    public void testPassword() {
        studentDTO.setPassword("kaese");
        assertEquals("kaese",studentDTO.getPassword());
    }
    @Test
    public void testAnrede() {
        studentDTO.setName("Rick");
        assertEquals("Rick",studentDTO.getName());
    }

    @Test
    public void testHochschule() {
        studentDTO.setHochschule("HBRS");
        assertEquals("HBRS",studentDTO.getHochschule());
    }
    @Test
    public void testSemester() {
        studentDTO.setSemester(5);
        assertNotEquals(java.util.Optional.of(5),studentDTO.getSemester());
    }
    @Test
    public void testKenntnisse() {
        studentDTO.setKenntnisse("Alles");
        assertEquals("Alles",studentDTO.getKenntnisse());
    }
    @Test
    public void testStudiengang() {
        studentDTO.setStudiengang("BWL");
        assertEquals("BWL",studentDTO.getStudiengang());
    }
}