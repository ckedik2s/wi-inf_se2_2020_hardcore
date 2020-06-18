package org.HardCore.process.control;

import com.vaadin.ui.UI;
import org.HardCore.gui.ui.MyUI;
import org.HardCore.gui.windows.ConfirmationWindow;
import org.HardCore.model.dao.RegisterDAO;
import org.HardCore.model.dao.RoleDAO;
import org.HardCore.model.dao.UserDAO;
import org.HardCore.model.objects.dto.User;
import org.HardCore.process.control.exceptions.DatabaseException;
import org.HardCore.process.control.exceptions.EmailInUseException;
import org.HardCore.process.control.exceptions.EmptyFieldException;
import org.HardCore.process.control.exceptions.NoEqualPasswordException;
import org.HardCore.services.db.JDBCConnection;
import org.HardCore.services.util.Roles;
import org.HardCore.services.util.Views;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RegistrationControl {

    private static RegistrationControl registration = null;
    private RegistrationControl(){
    }

    public static RegistrationControl getInstance(){
        if(registration == null){
            registration = new RegistrationControl();
        }
        return registration;
    }

    public void checkValid(String email, boolean emailBool, String password1, String password2, boolean password1Bool, boolean password2Bool, boolean checkBox) throws NoEqualPasswordException, DatabaseException, EmailInUseException, EmptyFieldException {

        //Eingabecheck
        if (!emailBool || !password1Bool  || !password2Bool || !checkBox) {
            throw new EmptyFieldException("Bitte ergänzen Sie Ihre Eingaben in den makierten Bereichen!");
        }

        //Passwortcheck
        if ( !password1.equals(password2) ) {
            throw new NoEqualPasswordException("Passwörter stimmen nicht überein!");
        }

        //DB Zugriff Emailcheck
        ResultSet set = null;
        Statement statement = JDBCConnection.getInstance().getStatement();

        try {
            set = statement.executeQuery("SELECT email " +
                    "FROM collhbrs.user " +
                    "WHERE email = \'" + email + "\'");
        } catch (SQLException throwables) {

        }

        try {
            if (set.next()) {
                throw new EmailInUseException("Die Email wird bereits benutzt!");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new DatabaseException("Fehler bei set: Bitte den Programmierer informieren!");
        }
    }

    //User registrieren
    public void registerUser( String email, String password, String regAs ) throws DatabaseException {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        boolean registerUser = RegisterDAO.getInstance().addUser(user);
        user.setId(UserDAO.getInstance().getMaxID());

        if (regAs.equals(Roles.STUDENT)) {
            registerUser = RegisterDAO.getInstance().addStudent(user);
            registerUser = RoleDAO.getInstance().setRolesForStudent(user);
        } else {
            registerUser = RegisterDAO.getInstance().addUnternehmen(user);
            registerUser = RoleDAO.getInstance().setRolesForUnternehmen(user);
        }

        if (registerUser == true) {
            UI.getCurrent().addWindow( new ConfirmationWindow("Registration erfolgreich!") );
            ( (MyUI)UI.getCurrent() ).setUser(user);
            UI.getCurrent().getNavigator().navigateTo(Views.MAIN);
        } else {
            throw new DatabaseException("Fehler bei Abschluß der Registration");
        }

    }

    //User Löschen
    public void deleteUser(User user){
        RegisterDAO.getInstance().deleteUser(user);
    }
}
