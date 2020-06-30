package DTOTest;

import org.hardcore.model.objects.dto.BewerbungDTO;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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