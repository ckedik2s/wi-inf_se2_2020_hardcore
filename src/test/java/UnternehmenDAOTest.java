import org.HardCore.model.dao.UnternehmenDAO;
import org.HardCore.model.dao.UserDAO;
import org.HardCore.model.objects.dto.Unternehmen;
import org.HardCore.model.objects.dto.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnternehmenDAOTest {

    private static UnternehmenDAO uDAO;
    private User user;
    private Object un;

    @Test
    static void testCreate() {
        uDAO =  UnternehmenDAO.getInstance();
        assertNotNull(uDAO);
    }
    @Test
    void testRead() {
        user = new User();
        uDAO.getAllDataUnternehmen(user);
        assertEquals((Unternehmen)un,(Unternehmen)un);
    }
    @Test
    void testUpdate() {


    }
    @Test
    void testDelete() {

    }

}