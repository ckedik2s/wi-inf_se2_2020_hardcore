package org.HardCore.process.Interfaces;

import org.HardCore.process.exceptions.DatabaseException;
import org.HardCore.process.exceptions.NoSuchUserOrPassword;

import java.sql.SQLException;

public interface LoginControlInterface {

    void checkAuthentification( String email, String password) throws NoSuchUserOrPassword, DatabaseException, SQLException;
    void logoutUser();
}
