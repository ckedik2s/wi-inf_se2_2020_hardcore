import org.HardCore.model.objects.dto.StellenanzeigeDetail;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.Assert.*;

public class StellenanzeigeDetailTest {


        StellenanzeigeDetail stellenanzeigeDetail = new StellenanzeigeDetail();

    @Test
    public void testId() {
        stellenanzeigeDetail.setId(1);
        assertEquals(1,stellenanzeigeDetail.getId());
    }

    @Test
    public void testGetId_anzeige() {
        stellenanzeigeDetail.setId_anzeige(1);
        assertEquals(1,stellenanzeigeDetail.getId_anzeige());
    }

    @Test
    public void testBeschreibung() {
        stellenanzeigeDetail.setBeschreibung("Lachs");
        assertEquals("Lachs",stellenanzeigeDetail.getBeschreibung());
    }

    @Test
    public void testArt() {
        stellenanzeigeDetail.setArt("Lachs");
        assertEquals("Lachs",stellenanzeigeDetail.getArt());
    }

    @Test
    public void testName() {
        stellenanzeigeDetail.setName("Lachs");
        assertEquals("Lachs",stellenanzeigeDetail.getName());
    }
    @Test
    public void testBranche() {
        stellenanzeigeDetail.setBranche("LachsBranche");
        assertEquals("LachsBranche",stellenanzeigeDetail.getBranche());
    }

    @Test
    public void testStudiengang() {
        stellenanzeigeDetail.setStudiengang("Inforamatik");
        assertEquals("Inforamatik",stellenanzeigeDetail.getStudiengang());
    }
    @Test
    public void testOrt() {
        stellenanzeigeDetail.setOrt("Bonn");
        assertEquals("Bonn",stellenanzeigeDetail.getOrt());
    }

    @Test
    public void testToString() {
        assertNotNull(stellenanzeigeDetail.toString());
    }

    @Test
    public void testAnzahl_bewerber() {
        stellenanzeigeDetail.setAnzahl_bewerber(5);
        assertEquals(5,stellenanzeigeDetail.getAnzahl_bewerber());

    }
}