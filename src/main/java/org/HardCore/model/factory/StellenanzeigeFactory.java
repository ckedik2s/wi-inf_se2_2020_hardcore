package org.HardCore.model.factory;

import org.HardCore.model.objects.dto.StellenanzeigeDetail;
import org.HardCore.model.objects.dto.User;
import org.HardCore.model.objects.entities.Stellenanzeige;

import java.sql.Date;

public class StellenanzeigeFactory {

    public static Stellenanzeige createStellenanzeige(StellenanzeigeDetail stellenanzeigeDetail, User user) {
        Stellenanzeige stellenanzeige = new Stellenanzeige();

        stellenanzeige.setId(stellenanzeigeDetail.getId());
        stellenanzeige.setId_anzeige(stellenanzeigeDetail.getId_anzeige());
        stellenanzeige.setName(stellenanzeigeDetail.getName());
        stellenanzeige.setArt(stellenanzeigeDetail.getArt());
        stellenanzeige.setBranche(stellenanzeigeDetail.getBranche());
        stellenanzeige.setStudiengang(stellenanzeigeDetail.getStudiengang());
        stellenanzeige.setZeitraum(Date.valueOf(stellenanzeigeDetail.getZeitraum()));

        return stellenanzeige;
    }
}
