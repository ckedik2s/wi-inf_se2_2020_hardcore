import org.HardCore.model.dao.UserDAO;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class UserDAOTest {

    private UserDAO userDAO;

    @Test
    public void testCreate() {
        userDAO =  UserDAO.getInstance();
        assertNotNull(userDAO);
    }
//    @Test
//    void testRead() {
//        assertNotNull(userDAO.getMaxID());
//    }
    @Test
    public void testUpdate() {

    }
    @Test
    public void testDelete() {

    }

}