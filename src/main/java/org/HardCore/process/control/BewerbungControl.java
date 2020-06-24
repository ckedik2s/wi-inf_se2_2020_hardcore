package org.HardCore.process.control;

import org.HardCore.model.dao.BewerbungDAO;
import org.HardCore.model.objects.dto.Bewerbung;
import org.HardCore.model.objects.dto.StellenanzeigeDetail;
import org.HardCore.model.objects.dto.Student;
import org.HardCore.model.objects.dto.User;

public class BewerbungControl {
    private static BewerbungControl bewerbungControl= null;

    private BewerbungControl(){

    }

    public static BewerbungControl getInstance(){
        if(bewerbungControl == null){
            bewerbungControl = new BewerbungControl();
        }
        return bewerbungControl;
    }

    public boolean applyForStellenanzeige(StellenanzeigeDetail stellenanzeige, User user) {
        Student student = new Student(user);
        Bewerbung bewerbung = BewerbungDAO.getInstance().getBewerbung(student);
        if (bewerbung == null) {
            return false;
        }
        return BewerbungDAO.getInstance().applyForStellenanzeige(stellenanzeige, student);
    }
}
