import org.HardCore.model.dao.StudentDAO;
import org.HardCore.model.objects.dto.StudentDTO;
import org.HardCore.model.objects.dto.UserDTO;
import org.junit.Test;

import static org.junit.Assert.*;

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