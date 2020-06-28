package org.HardCore.model.factory;

import org.HardCore.model.objects.dto.StellenanzeigeDetail;
import org.HardCore.model.objects.dto.UserDTO;
import org.HardCore.model.objects.entities.Stellenanzeige;

import java.sql.Date;

public class StellenanzeigeFactory {

    public static Stellenanzeige createStellenanzeige(StellenanzeigeDetail stellenanzeigeDetail, UserDTO userDTO) {
        Stellenanzeige stellenanzeige = new Stellenanzeige();

        stellenanzeige.setId(stellenanzeigeDetail.getId());
        stellenanzeige.setId_anzeige(stellenanzeigeDetail.getId_anzeige());
        stellenanzeige.setBeschreibung(stellenanzeigeDetail.getBeschreibung());
        stellenanzeige.setName(stellenanzeigeDetail.getName());
        stellenanzeige.setArt(stellenanzeigeDetail.getArt());
        stellenanzeige.setBranche(stellenanzeigeDetail.getBranche());
        stellenanzeige.setStudiengang(stellenanzeigeDetail.getStudiengang());
        stellenanzeige.setOrt(stellenanzeigeDetail.getOrt());
        stellenanzeige.setZeitraum(Date.valueOf(stellenanzeigeDetail.getZeitraum()));
        stellenanzeige.setAnzahl_bewerber(stellenanzeige.getAnzahl_bewerber());

        return stellenanzeige;
    }
}
