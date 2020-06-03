package org.HardCore.process.control;

import com.vaadin.ui.UI;
import org.HardCore.gui.ui.MyUI;
import org.HardCore.gui.windows.ConfirmationWindow;
import org.HardCore.model.dao.RegisterDAO;
import org.HardCore.model.objects.dto.Student;
import org.HardCore.model.objects.dto.Unternehmen;
import org.HardCore.model.objects.dto.User;
import org.HardCore.process.control.exceptions.DatabaseException;
import org.HardCore.process.control.exceptions.EmailInUseException;
import org.HardCore.process.control.exceptions.EmptyFieldException;
import org.HardCore.process.control.exceptions.NoEqualPasswordException;
import org.HardCore.services.db.JDBCConnection;
import org.HardCore.services.util.Views;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProfileControl {


    public static void updateStudentData(Student student, String anrede, String name, String vorname) throws DatabaseException{
        student.setAnrede(anrede);
        student.setVorname(vorname);
        student.setName(name);
    }
    public static void updateUnternehmenData(Unternehmen unternehmen, String firmenname) throws DatabaseException{
        unternehmen.setName(firmenname);
    }

    public static void deleteUser(User user){
        /*boolean unregisterUser = RegisterDAO.getInstance().deleteUser(user);

        if (unregisterUser == true) {
            UI.getCurrent().addWindow( new ConfirmationWindow("Profil gelöscht!") );
            ( (MyUI)UI.getCurrent() ).setUser(null);
            UI.getCurrent().getNavigator().navigateTo(Views.MAIN);
        } else {
            throw new DatabaseException("Fehler bei Löschung des Profils");
        }*/
    }
}
