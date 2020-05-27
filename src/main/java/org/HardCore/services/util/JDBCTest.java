package org.HardCore.services.util;

import org.HardCore.process.control.exceptions.DatabaseException;
import org.HardCore.services.db.JDBCConnection;

import static org.junit.jupiter.api.Assertions.*;

public class JDBCTest {

    @org.junit.jupiter.api.Test
    void testStatement() throws DatabaseException { //Throwt Exception, da diese in jdbc klasse ebenso geworfen wird
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
