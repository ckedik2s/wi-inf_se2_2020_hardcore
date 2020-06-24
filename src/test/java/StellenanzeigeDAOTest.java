import org.HardCore.model.dao.StudentDAO;
import org.HardCore.model.objects.dto.User;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class StellenanzeigeDAOTest {

    private static StudentDAO stellenDAO;
    private User user;
    private Object stud;

    @Test
    public void testCreate() {
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
    public void testUpdate() {

    }
    @Test
    public void testDelete() {

    }
}