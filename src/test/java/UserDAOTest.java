import org.HardCore.model.dao.UserDAO;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertNotNull;

public class UserDAOTest {

    private UserDAO userDAO;

    @Test
    public void testCreate() {
        userDAO =  UserDAO.getInstance();
        assertNotNull(userDAO);
    }
    @Test
    public void testReadID() {
        try {
            assertNotNull(UserDAO.getInstance().getMaxID());
        } catch (SQLException e) {
            
        }
    }
}