package DAOTests;

import org.HardCore.model.dao.BewerbungDAO;
import org.HardCore.model.dao.StudentDAO;
import org.HardCore.model.objects.dto.StudentDTO;
import org.HardCore.model.objects.dto.UserDTO;
import org.junit.Test;

import static org.junit.Assert.*;

public class BewerbungDAOTest {


    @Test
    public void testCreate() {
        BewerbungDAO bewDao = BewerbungDAO.getInstance();
        assertNotNull(bewDao);
    }
    @Test
    public void createBewerbung() {
        UserDTO userDTO = new UserDTO();
        StudentDTO studentDAO = new StudentDTO(userDTO);
        assertFalse(BewerbungDAO.getInstance().createBewerbung("Lachs",studentDAO));
    }

}