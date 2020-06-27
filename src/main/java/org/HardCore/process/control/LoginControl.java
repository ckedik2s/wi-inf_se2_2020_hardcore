package org.HardCore.process.control;

import com.vaadin.ui.UI;
import org.HardCore.gui.ui.MyUI;
import org.HardCore.model.objects.dto.StudentDTO;
import org.HardCore.model.objects.dto.UnternehmenDTO;
import org.HardCore.model.objects.dto.UserDTO;
import org.HardCore.process.Interfaces.LoginControlInterface;
import org.HardCore.process.exceptions.DatabaseException;
import org.HardCore.process.exceptions.NoSuchUserOrPassword;
import org.HardCore.services.db.JDBCConnection;
import org.HardCore.services.util.Roles;
import org.HardCore.services.util.Views;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginControl implements LoginControlInterface {
    private static LoginControl loginControl = null;

    private LoginControl(){
    }
    public static LoginControl getInstance(){
        if(loginControl == null){
            loginControl = new LoginControl();
        }
        return loginControl;
    }

    public void checkAuthentification( String email, String password) throws NoSuchUserOrPassword, DatabaseException {

        //DB User abfrage
        ResultSet rs = null;
        Statement statement = JDBCConnection.getInstance().getStatement();
        try {
            rs = statement.executeQuery("SELECT id " +
                    "FROM collhbrs.user " +
                    "WHERE email = \'" + email + "\' " +
                    "AND password = \'" + password + "\'");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new DatabaseException("Fehler im SQL-Befehl: Bitte den Programmierer informieren!");
        }

        UserDTO userDTO = null;

        try {
            if( rs.next() ) {
                userDTO = new UserDTO();
                userDTO.setId(rs.getInt(1));
                userDTO.setEmail(email);
                if ( userDTO.hasRole(Roles.STUDENT) ) {
                    userDTO = ProfileControl.getInstance().getStudent(new StudentDTO(userDTO));
                }
                else {
                    userDTO = ProfileControl.getInstance().getUnternehmen(new UnternehmenDTO(userDTO));
                }
            }
            else {
                throw new NoSuchUserOrPassword();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            JDBCConnection.getInstance().closeConnection();
        }
        //UI.getCurrent().getSession().setAttribute(user);
        ((MyUI) UI.getCurrent() ).setUserDTO(userDTO); //Mockito zum Testen
        UI.getCurrent().getNavigator().navigateTo(Views.MAIN);
    }

    public void logoutUser() {
        UI.getCurrent().close();
        UI.getCurrent().getPage().setLocation("/HardCore");
    }
}
