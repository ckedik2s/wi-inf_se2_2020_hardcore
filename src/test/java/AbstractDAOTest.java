import org.HardCore.model.dao.AbstractDAO;
import org.HardCore.model.dao.UserDAO;
import org.junit.Test;
import java.sql.Statement;
import static org.junit.Assert.*;

public class AbstractDAOTest extends AbstractDAO {

    private Statement statement;

    @Test
    public void testCreate() {
       statement = getStatement();
        assertNotNull(statement);
    }
}