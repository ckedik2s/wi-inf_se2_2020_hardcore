package DAOTests;

import org.hardcore.model.dao.AbstractDAO;
import org.junit.Test;

import java.sql.Statement;

import static org.junit.Assert.assertNotNull;

public class AbstractDAOTest extends AbstractDAO {

    private Statement statement;

    @Test
    public void testCreate() {
       statement = getStatement();
        assertNotNull(statement);
    }
}