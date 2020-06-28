package org.HardCore.process.control;

import com.vaadin.ui.UI;
import org.HardCore.gui.ui.MyUI;
import org.HardCore.gui.windows.ConfirmationWindow;
import org.HardCore.model.dao.RegisterDAO;
import org.HardCore.model.dao.RoleDAO;
import org.HardCore.model.dao.UserDAO;
import org.HardCore.model.objects.dto.UserDTO;
import org.HardCore.process.Interfaces.RegistrationControlInterface;
import org.HardCore.process.exceptions.DatabaseException;
import org.HardCore.process.exceptions.EmailInUseException;
import org.HardCore.process.exceptions.EmptyFieldException;
import org.HardCore.process.exceptions.NoEqualPasswordException;
import org.HardCore.services.db.JDBCConnection;
import org.HardCore.services.util.Roles;
import org.HardCore.services.util.Views;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegistrationControl implements RegistrationControlInterface {

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
        String sql = "SELECT email " +
                     "FROM collhbrs.user " +
                     "WHERE email = ? ;";
        ResultSet rs = null;
        PreparedStatement statement = JDBCConnection.getInstance().getPreparedStatement(sql);

        try {
            statement.setString(1,email);
            rs = statement.executeQuery();
        } catch (SQLException throwables) {

        }

        try {
            if (rs.next()) {
                throw new EmailInUseException("Die Email wird bereits benutzt!");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new DatabaseException("Fehler bei set: Bitte den Programmierer informieren!");
        }
    }

    //User registrieren
    public void registerUser( String email, String password, String regAs ) throws DatabaseException {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(email);
        userDTO.setPassword(password);
        boolean registerUser = RegisterDAO.getInstance().addUser(userDTO);
        userDTO.setId(UserDAO.getInstance().getMaxID());

        if (regAs.equals(Roles.STUDENT)) {
            registerUser = RegisterDAO.getInstance().addStudent(userDTO);
            registerUser = RoleDAO.getInstance().setRolesForStudent(userDTO);
        } else {
            registerUser = RegisterDAO.getInstance().addUnternehmen(userDTO);
            registerUser = RoleDAO.getInstance().setRolesForUnternehmen(userDTO);
        }

        if (registerUser == true) {
            UI.getCurrent().addWindow( new ConfirmationWindow("Registration erfolgreich!") );
            ( (MyUI)UI.getCurrent() ).setUserDTO(userDTO);
            UI.getCurrent().getNavigator().navigateTo(Views.MAIN);
        } else {
            throw new DatabaseException("Fehler bei Abschluß der Registration");
        }

    }

    //User Löschen
    public void deleteUser(UserDTO userDTO){
        RegisterDAO.getInstance().deleteUser(userDTO);
    }
}
