import org.HardCore.model.objects.dto.RoleDTO;
import org.junit.Test;

import static org.junit.Assert.*;

public class RoleDTOTest {


    @Test
    public void testBezeichnung() {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setBezeichnung("Lachs");
        assertEquals("Lachs",roleDTO.getBezeichnung());


    }

}