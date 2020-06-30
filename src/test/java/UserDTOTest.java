import org.hardcore.model.objects.dto.UserDTO;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserDTOTest {

    UserDTO userDTO = new UserDTO();

    @Test
    public void testVorname() {
        userDTO.setVorname("Jülle");
        assertEquals("Jülle",userDTO.getVorname());
    }
    @Test
    public void testName() {
        userDTO.setName("Hoppa");
        assertEquals("Hoppa",userDTO.getName());
    }
    @Test
    public void testId() {
        userDTO.setId(1);
        assertEquals(1,userDTO.getId());
    }
    @Test
    public void testEmail() {
        userDTO.setEmail("Jülle@free.de");
        assertEquals("Jülle@free.de",userDTO.getEmail());
    }
    @Test
    public void testPassword() {
        userDTO.setPassword("123");
        assertEquals("123",userDTO.getPassword());
    }
}