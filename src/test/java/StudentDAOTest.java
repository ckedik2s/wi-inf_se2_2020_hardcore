import org.HardCore.model.dao.StudentDAO;
import org.HardCore.model.objects.dto.UserDTO;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class StudentDAOTest {

    private static StudentDAO studDAO;
    private UserDTO userDTO;
    private Object stud;

    @Test
    public void testCreate() {
        studDAO =  StudentDAO.getInstance();
        assertNotNull(studDAO);
    }
//    @Test
//    void testRead() {
//        user = new User();
//        studDAO.getAllDataStudent(user);
//        assertEquals((Unternehmen)stud,(Unternehmen)stud);
//    }
    @Test
    public void testUpdate() {

    }
    @Test
    public void testDelete() {

    }
}