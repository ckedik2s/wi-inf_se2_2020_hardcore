import org.HardCore.model.dao.StudentDAO;
import org.HardCore.model.objects.dto.Unternehmen;
import org.HardCore.model.objects.dto.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StellenanzeigeDAOTest {

    private static StudentDAO stellenDAO;
    private User user;
    private Object stud;

    @Test
    static void testCreate() {
        stellenDAO =  StudentDAO.getInstance();
        assertNotNull(stellenDAO);
    }
//    @Test
//    void testRead() {
//        user = new User();
//        stellenDAO.getAllDataStudent(user);
//        assertEquals((Unternehmen)stud,(Unternehmen)stud);
//    }
    @Test
    void testUpdate() {

    }
    @Test
    void testDelete() {

    }
}