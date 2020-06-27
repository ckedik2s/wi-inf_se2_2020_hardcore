import org.HardCore.model.dao.UnternehmenDAO;
import org.HardCore.model.objects.dto.UserDTO;
import org.junit.Test;

public class UnternehmenDAOTest {

    private static UnternehmenDAO uDAO;
    private UserDTO userDTO;
    private Object un;

    @Test
    public void testCreate() {
        uDAO =  UnternehmenDAO.getInstance();
        assertNotNull(uDAO);
    }

    public void assertNotNull(UnternehmenDAO uDAO) {
    }

    //    @Test
//    void testRead() {
//        user = new User();
//        uDAO.getAllDataUnternehmen(user);
//        assertEquals((Unternehmen)un,(Unternehmen)un);
//    }
    @Test
    public void testUpdate() {


    }
    @Test
    public void testDelete() {

    }

}