import org.HardCore.model.dao.RegisterDAO;
import org.HardCore.process.control.RegistrationControl;
import org.HardCore.process.exceptions.DatabaseException;
import org.HardCore.process.exceptions.EmptyFieldException;
import org.HardCore.process.exceptions.NoEqualPasswordException;
import org.HardCore.process.proxy.RegistrationControlProxy;
import org.HardCore.services.util.SafeString;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

public class RegiControlTest {

    private static String email;
    private static String password1;
    private static String password2;
    private static boolean emailBool;
    private static boolean password1Bool;
    private static boolean password2Bool;
    private static boolean checkBox;

    @Test
    public void EqualPasswordTest() {
        email = "Pfill@test.de";
        password1 = SafeString.PW1;
        password2 = SafeString.PW2;
        emailBool = true;
        password1Bool = true;
        password2Bool = true;
        checkBox = true;
        assertThrows(NoEqualPasswordException.class,
                () -> RegistrationControlProxy.getInstance().checkValid(email, emailBool, password1, password2, password1Bool, password2Bool, checkBox));
    }

    @Test
    public void emptyFieldTest() {
        email = "Pfill";
        password1 = SafeString.PW1;
        password2 = SafeString.PW2;
        emailBool = false;
        password1Bool = true;
        password2Bool = true;
        checkBox = true;
        assertThrows(EmptyFieldException.class,
                () -> RegistrationControlProxy.getInstance().checkValid(email, emailBool, password1, password2, password1Bool, password2Bool, checkBox));
    }
    @Test
    public void createTest() {
        RegistrationControl registrationControl = RegistrationControl.getInstance();
        assertNotNull(registrationControl);
    }

}
