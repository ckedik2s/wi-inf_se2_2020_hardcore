package DAOTests;

import org.HardCore.model.objects.dto.BewerbungDTO;
import org.junit.Test;

import static org.junit.Assert.*;

public class BewerbungDTOTest {

    @Test
    public void testFreitext(){
        BewerbungDTO bewerbungDTO = new BewerbungDTO();
        bewerbungDTO.setFreitext("Lachs");
        assertEquals("Lachs",bewerbungDTO.getFreitext());
    }
    @Test
    public void testID(){
        BewerbungDTO bewerbungDTO = new BewerbungDTO();
        bewerbungDTO.setId(1);
        assertEquals(1,bewerbungDTO.getId());
    }


}