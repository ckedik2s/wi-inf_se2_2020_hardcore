package org.HardCore.services.util;

import org.HardCore.services.db.JDBCConnection;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Roles {
    public static final String CURRENT_USER = "currentUser";
    public static final String UNTERNEHMEN = "Unternehmen";
    public static final String STUDENT = "Student";

}
