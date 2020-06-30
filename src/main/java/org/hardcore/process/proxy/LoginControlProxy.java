package org.hardcore.process.proxy;

import org.hardcore.process.Interfaces.LoginControlInterface;
import org.hardcore.process.control.LoginControl;
import org.hardcore.process.exceptions.DatabaseException;
import org.hardcore.process.exceptions.NoSuchUserOrPassword;

import java.sql.SQLException;

public class LoginControlProxy implements LoginControlInterface {
    private static LoginControlProxy loginControl = null;

    private LoginControlProxy(){
    }
    public static LoginControlProxy getInstance(){
        if(loginControl == null){
            loginControl = new LoginControlProxy();
        }
        return loginControl;
    }

    public void checkAuthentification( String email, String password) throws NoSuchUserOrPassword, DatabaseException, SQLException {
        LoginControl.getInstance().checkAuthentification(email, password);
    }

    public void logoutUser() {
        LoginControl.getInstance().logoutUser();
    }
}
