package org.HardCore.process.Interfaces;

import org.HardCore.process.exceptions.DatabaseException;
import org.HardCore.process.exceptions.NoSuchUserOrPassword;

public interface LoginControlInterface {

    void checkAuthentification( String email, String password) throws NoSuchUserOrPassword, DatabaseException;
    void logoutUser();
}
