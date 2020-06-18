import org.HardCore.model.dao.RoleDAO;
import org.HardCore.model.objects.dto.User;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class RoleDAOTest {

    private static RoleDAO roleDAO;
    private User user;

    @Test
    public void testCreate() {
        roleDAO =  RoleDAO.getInstance();
        assertNotNull(roleDAO);
    }
    @Test
    public void testRead() {

    }
    @Test
    public void testUpdate() {

    }
    @Test
    public void testDelete() {

    }
}