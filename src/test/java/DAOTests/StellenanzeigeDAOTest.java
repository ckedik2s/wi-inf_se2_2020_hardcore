package DAOTests;

import org.HardCore.model.dao.StellenanzeigeDAO;
import org.HardCore.model.dao.StudentDAO;
import org.HardCore.model.objects.dto.UserDTO;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class StellenanzeigeDAOTest {


    @Test
    public void testCreate() {
        StellenanzeigeDAO stellenanzeigeDAO =  StellenanzeigeDAO.getInstance();
        assertNotNull(stellenanzeigeDAO);
    }
}