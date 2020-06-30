import org.hardcore.model.dao.StellenanzeigeDAO;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class StellenanzeigeDAOTest {


    @Test
    public void testCreate() {
        StellenanzeigeDAO stellenanzeigeDAO =  StellenanzeigeDAO.getInstance();
        assertNotNull(stellenanzeigeDAO);
    }
}