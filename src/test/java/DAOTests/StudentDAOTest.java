import org.hardcore.model.dao.StudentDAO;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class StudentDAOTest {

    private StudentDAO studDAO;

    @Test
    public void testCreate() {
        studDAO =  StudentDAO.getInstance();
        assertNotNull(studDAO);
    }
    @Test
    public void testRead() {

    }
}