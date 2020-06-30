import org.hardcore.model.dao.RoleDAO;
import org.hardcore.model.objects.dto.UserDTO;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class RoleDAOTest {

    private RoleDAO roleDAO;

    @Test
    public void testCreate() {
        roleDAO =  RoleDAO.getInstance();
        assertNotNull(roleDAO);
    }
    @Test
    public void setStudent() {
        UserDTO userDTO = new UserDTO();
        assertFalse(RoleDAO.getInstance().setRolesForStudent(userDTO));

    }
    @Test
    public void setUnternehmen() {
        UserDTO userDTO = new UserDTO();
        assertFalse(RoleDAO.getInstance().setRolesForUnternehmen(userDTO));
    }
    @Test
    public void testDelete() {

    }
}