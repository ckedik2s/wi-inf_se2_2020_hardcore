import org.HardCore.model.dao.StudentDAO;
import org.HardCore.model.dao.UserDAO;
import org.HardCore.model.objects.dto.Unternehmen;
import org.HardCore.model.objects.dto.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentDAOTest {

    private static StudentDAO studDAO;
    private User user;
    private Object stud;

    @Test
    static void testCreate() {
        studDAO =  StudentDAO.getInstance();
        assertNotNull(studDAO);
    }
    @Test
    void testRead() {
        user = new User();
        studDAO.getAllDataStudent(user);
        assertEquals((Unternehmen)stud,(Unternehmen)stud);
    }
    @Test
    void testUpdate() {

    }
    @Test
    void testDelete() {

    }
}