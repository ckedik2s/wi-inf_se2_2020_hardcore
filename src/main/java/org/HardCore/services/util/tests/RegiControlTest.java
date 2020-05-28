package org.HardCore.services.util.tests;

import org.HardCore.process.control.RegistrationControl;
import org.HardCore.process.control.exceptions.EmptyFieldException;

import org.HardCore.process.control.exceptions.NoEqualPasswordException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegiControlTest {

    private static String email;
    private static String password1;
    private static String password2;
    private static boolean emailBool;
    private static boolean password1Bool;
    private static boolean password2Bool;
    private static boolean checkBox;

    @Test
    void EqualPasswordTest() {
        email = "Pfill@test.de";
        password1 = "!ABC123";
        password2 = "!ABC1234";
        emailBool = true;
        password1Bool = true;
        password2Bool = true;
        checkBox = true;
        assertThrows(NoEqualPasswordException.class,
                () -> RegistrationControl.checkValid(email, emailBool, password1, password2, password1Bool, password2Bool, checkBox));
    }

    @Test
    void emptyFieldTest() {
        email = "Pfill";
        password1 = "!ABC123";
        password2 = "!ABC1234";
        emailBool = false;
        password1Bool = true;
        password2Bool = true;
        checkBox = true;
        assertThrows(EmptyFieldException.class,
                () -> RegistrationControl.checkValid(email, emailBool, password1, password2, password1Bool, password2Bool, checkBox));
    }
}
