import org.HardCore.model.dao.UserDAO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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