import org.HardCore.model.dao.RegisterDAO;
import org.HardCore.model.objects.dto.User;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class RegisterDAOTest {

    private static RegisterDAO regiDAO;
    private User user;

    @Test
    public void testCreate() {
        regiDAO =  RegisterDAO.getInstance();
        assertNotNull(regiDAO);
    }
    @Test
    public void testRead() {

    }
    @Test
    public void testUpdate() {

    }
//    @Test
//    void testDelete() {
//        user = new User();
//        regiDAO.addUser(user);
//    }
}