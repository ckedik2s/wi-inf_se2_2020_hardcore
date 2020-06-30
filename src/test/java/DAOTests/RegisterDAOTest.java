package DAOTests;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.RegularExpression;
import org.HardCore.model.dao.RegisterDAO;
import org.HardCore.model.dao.UserDAO;
import org.HardCore.model.objects.dto.UserDTO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.sql.SQLException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class RegisterDAOTest {



    private RegisterDAO regiDAO = RegisterDAO.getInstance();
    private UserDTO userDTO = new UserDTO();


    @Test
    public void testCreate() {
        userDTO.setEmail("lachs12@gmx.de");
        userDTO.setId(2);
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

    @After
    public void testDelete() throws SQLException {
        int maxID=UserDAO.getInstance().getMaxID();
        userDTO.setId(maxID);
        regiDAO.deleteUser(userDTO);
    }
}