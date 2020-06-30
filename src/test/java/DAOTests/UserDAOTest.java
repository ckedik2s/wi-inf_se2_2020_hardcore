import org.hardcore.model.dao.UserDAO;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class UserDAOTest {

    private UserDAO userDAO;

    @Test
    public void testCreate() {
        userDAO =  UserDAO.getInstance();
        assertNotNull(userDAO);
    }
}