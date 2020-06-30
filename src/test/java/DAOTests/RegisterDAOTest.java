package DAOTests;

import org.hardcore.model.dao.RegisterDAO;
import org.hardcore.model.dao.UserDAO;
import org.hardcore.model.objects.dto.UserDTO;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class RegisterDAOTest {


    private RegisterDAO regiDAO = RegisterDAO.getInstance();
    private UserDTO userDTO = new UserDTO();

    @Test
    public void testCreate() {
        userDTO.setEmail("lachs12@gmx.de");
        userDTO.setId(3);
        userDTO.setPassword("test");
        userDTO.setName("Pat");
        userDTO.setVorname("Rick");
        assertNotNull(regiDAO);
    }
    @Test
    public void AddUserTest() {
        assertTrue(RegisterDAO.getInstance().addUser(userDTO));
    }
    @Test
    public void update() {
        userDTO.setEmail("lachs12@gmx.de");
        userDTO.setId(3);
        userDTO.setPassword("test123"); //Pw geändert
        userDTO.setName("Pat");
        userDTO.setVorname("Rick");
        assertNotNull(regiDAO);
    }
    @Test
    public void testDelete() {
        try {
            userDTO.setId(UserDAO.getInstance().getMaxID());
        } catch (SQLException throwables) {
            assertNull(userDTO);
        }
        regiDAO.deleteUser(userDTO);
    }
}