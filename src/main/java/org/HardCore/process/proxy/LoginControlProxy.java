package org.HardCore.process.proxy;

import com.vaadin.ui.UI;
import org.HardCore.gui.ui.MyUI;
import org.HardCore.model.objects.dto.StudentDTO;
import org.HardCore.model.objects.dto.UnternehmenDTO;
import org.HardCore.model.objects.dto.UserDTO;
import org.HardCore.process.Interfaces.LoginControlInterface;
import org.HardCore.process.control.LoginControl;
import org.HardCore.process.exceptions.DatabaseException;
import org.HardCore.process.exceptions.NoSuchUserOrPassword;
import org.HardCore.services.db.JDBCConnection;
import org.HardCore.services.util.Roles;
import org.HardCore.services.util.Views;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

    public void checkAuthentification( String email, String password) throws NoSuchUserOrPassword, DatabaseException {
        LoginControl.getInstance().checkAuthentification(email, password);
    }

    public void logoutUser() {
        LoginControl.getInstance().logoutUser();
    }
}
