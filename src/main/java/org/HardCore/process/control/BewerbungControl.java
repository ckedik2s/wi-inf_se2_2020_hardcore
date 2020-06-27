package org.HardCore.process.control;

import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import org.HardCore.model.dao.BewerbungDAO;
import org.HardCore.model.objects.dto.Bewerbung;
import org.HardCore.model.objects.dto.StellenanzeigeDetail;
import org.HardCore.model.objects.dto.Student;
import org.HardCore.model.objects.dto.User;
import org.HardCore.process.control.exceptions.BewerbungException;
import org.HardCore.process.control.exceptions.DatabaseException;
import org.HardCore.services.util.Roles;

public class BewerbungControl {
    private static BewerbungControl bewerbungControl = null;

    private BewerbungControl() {

    }

    public static BewerbungControl getInstance() {
        if (bewerbungControl == null) {
            bewerbungControl = new BewerbungControl();
        }
        return bewerbungControl;
    }

    public void setBewerbung(Bewerbung bewerbung) {

    }

    public boolean applyForStellenanzeige(StellenanzeigeDetail stellenanzeige, User user) throws DatabaseException {
        // TODO Moritz: bewerben auf Stellenanzeige mit Bewerbung des usersreturn
        return BewerbungDAO.getInstance().sendBewerbung(stellenanzeige, user);

    }

    public boolean applyingIsAllowed() {
        //TODO Moritz: check nach Bewerbung Toggle auf Datenbank
        return BewerbungDAO.getInstance().applyingIsAllowed();
    }

    public void checkAllowed(User user, Button bewerbenButton) {
        if (user == null || !user.hasRole(Roles.STUDENT)) {
            bewerbenButton.setVisible(false);
        }
    }

    public void createBewerbung(String bewerbungstext, User user) throws BewerbungException {
        Student student = new Student(user);
        boolean result = BewerbungDAO.getInstance().createBewerbung(bewerbungstext, student);
        if (result == false) {
            throw new BewerbungException();
        }
    }
}
