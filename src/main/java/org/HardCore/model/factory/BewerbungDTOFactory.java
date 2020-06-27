package org.HardCore.model.factory;

import org.HardCore.model.objects.dto.BewerbungDTO;
import org.HardCore.model.objects.dto.StellenanzeigeDetail;
import org.HardCore.model.objects.dto.UserDTO;
import org.HardCore.model.objects.entities.Stellenanzeige;

import java.sql.Date;

public class BewerbungDTOFactory {
    public static BewerbungDTO createBewerbungDTO(int id, String text) {
        BewerbungDTO bewerbungDTO = new BewerbungDTO();

        bewerbungDTO.setId(id);
        bewerbungDTO.setFreitext(text);
        return bewerbungDTO;
    }
}
