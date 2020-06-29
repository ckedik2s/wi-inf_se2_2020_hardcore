import org.HardCore.model.objects.dto.StellenanzeigeDTO;
import org.junit.Test;

import static org.junit.Assert.*;

public class StellenanzeigeDTOTest {


        StellenanzeigeDTO stellenanzeigeDTO = new StellenanzeigeDTO();

    @Test
    public void testId() {
        stellenanzeigeDTO.setId(1);
        assertEquals(1, stellenanzeigeDTO.getId());
    }

    @Test
    public void testGetId_anzeige() {
        stellenanzeigeDTO.setId_anzeige(1);
        assertEquals(1, stellenanzeigeDTO.getId_anzeige());
    }

    @Test
    public void testBeschreibung() {
        stellenanzeigeDTO.setBeschreibung("Lachs");
        assertEquals("Lachs", stellenanzeigeDTO.getBeschreibung());
    }

    @Test
    public void testArt() {
        stellenanzeigeDTO.setArt("Lachs");
        assertEquals("Lachs", stellenanzeigeDTO.getArt());
    }

    @Test
    public void testName() {
        stellenanzeigeDTO.setName("Lachs");
        assertEquals("Lachs", stellenanzeigeDTO.getName());
    }
    @Test
    public void testBranche() {
        stellenanzeigeDTO.setBranche("LachsBranche");
        assertEquals("LachsBranche", stellenanzeigeDTO.getBranche());
    }

    @Test
    public void testStudiengang() {
        stellenanzeigeDTO.setStudiengang("Inforamatik");
        assertEquals("Inforamatik", stellenanzeigeDTO.getStudiengang());
    }
    @Test
    public void testOrt() {
        stellenanzeigeDTO.setOrt("Bonn");
        assertEquals("Bonn", stellenanzeigeDTO.getOrt());
    }

    @Test
    public void testToString() {
        assertNotNull(stellenanzeigeDTO.toString());
    }

    @Test
    public void testAnzahl_bewerber() {
        stellenanzeigeDTO.setAnzahl_bewerber(5);
        assertEquals(5, stellenanzeigeDTO.getAnzahl_bewerber());

    }
}