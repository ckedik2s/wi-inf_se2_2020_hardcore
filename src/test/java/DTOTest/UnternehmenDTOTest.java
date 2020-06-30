package DTOTest;

import org.HardCore.model.objects.dto.UnternehmenDTO;
import org.HardCore.model.objects.dto.UserDTO;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnternehmenDTOTest {

    UserDTO userDTO = new UserDTO();
    UnternehmenDTO unternehmenDTO = new UnternehmenDTO(userDTO);

    @Test
    public void testVorname() {
        unternehmenDTO.setVorname("Killy");
        assertEquals("Killy",unternehmenDTO.getVorname());
    }
    @Test
    public void testName() {
        unternehmenDTO.setName("Lian");
        assertEquals("Lian",unternehmenDTO.getName());
    }
    @Test
    public void testId() {
        unternehmenDTO.setId(1);
        assertEquals(1,unternehmenDTO.getId());
    }
    @Test
    public void testEmail() {
        unternehmenDTO.setEmail("lillyqfree@hoas.de");
        assertEquals("lillyqfree@hoas.de",unternehmenDTO.getEmail());
    }
    @Test
    public void testPassword() {
        unternehmenDTO.setPassword("asd");
        assertEquals("asd",unternehmenDTO.getPassword());
    }
    @Test
    public void testAnsprechpartner() {
        unternehmenDTO.setAnsprechpartner("pablo");
        assertEquals("pablo",unternehmenDTO.getAnsprechpartner());
    }
    @Test
    public void testStrasse() {
        unternehmenDTO.setStrasse("Schetraße");
        assertEquals("Schetraße",unternehmenDTO.getStrasse());
    }
    @Test
    public void testPlz() {
        unternehmenDTO.setPlz(52111);
        assertNotEquals(java.util.Optional.of(52111),unternehmenDTO.getPlz());
    }
    @Test
    public void testHaus_nr() {
        unternehmenDTO.setHaus_nr(42);
        assertNotEquals(java.util.Optional.of(42),unternehmenDTO.getHaus_nr());
    }
    @Test
    public void testZusatz() {
        unternehmenDTO.setZusatz("leer");
        assertEquals("leer",unternehmenDTO.getZusatz());
    }
    @Test
    public void testBranche() {
        unternehmenDTO.setBranche("Info");
        assertEquals("Info",unternehmenDTO.getBranche());
    }
    @Test
    public void testOrt() {
        unternehmenDTO.setOrt("Münich");
        assertEquals("Münich",unternehmenDTO.getOrt());
    }
}