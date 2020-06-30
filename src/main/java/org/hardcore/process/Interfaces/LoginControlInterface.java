package org.hardcore.process.Interfaces;

import org.hardcore.process.exceptions.DatabaseException;
import org.hardcore.process.exceptions.NoSuchUserOrPassword;

import java.sql.SQLException;

public interface LoginControlInterface {

    void checkAuthentification( String email, String password) throws NoSuchUserOrPassword, DatabaseException, SQLException;
    void logoutUser();
}
