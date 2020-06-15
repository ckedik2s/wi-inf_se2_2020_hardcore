package org.HardCore.services.util.tests;

import org.HardCore.model.dao.RegisterDAO;
import org.HardCore.model.dao.RoleDAO;
import org.HardCore.model.objects.dto.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegisterDAOTest {

    private static RegisterDAO regiDAO;
    private User user;

    @Test
    static void testCreate() {
        regiDAO =  RegisterDAO.getInstance();
        assertNotNull(regiDAO);
    }
    @Test
    void testRead() {

    }
    @Test
    void testUpdate() {

    }
    @Test
    void testDelete() {
        user = new User();
        regiDAO.addUser(user);
    }
}