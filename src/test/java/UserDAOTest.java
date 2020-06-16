import org.HardCore.model.dao.UserDAO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {

    private UserDAO userDAO;

    @Test
    void testCreate() {
        userDAO =  UserDAO.getInstance();
        assertNotNull(userDAO);
    }
//    @Test
//    void testRead() {
//        assertNotNull(userDAO.getMaxID());
//    }
    @Test
    void testUpdate() {

    }
    @Test
    void testDelete() {

    }

}