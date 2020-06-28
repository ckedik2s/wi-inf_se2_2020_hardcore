import org.HardCore.process.exceptions.DatabaseException;
import org.HardCore.services.db.JDBCConnection;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;


public class JDBCTest {

    @Test
    public void testStatement() throws DatabaseException { //Throwt Exception, da diese in jdbc klasse ebenso geworfen wird
        JDBCConnection con = JDBCConnection.getInstance();
        try {
            con.initConnection();
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        assertNotNull(con.getStatement()); // Checkt, ob Statement nicht null
        con.closeConnection();
        con = null;
        assertNull(con);
    }
}
