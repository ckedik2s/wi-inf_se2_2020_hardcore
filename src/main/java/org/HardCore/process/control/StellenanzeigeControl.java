package org.HardCore.process.control;

import com.vaadin.ui.UI;
import org.HardCore.gui.ui.MyUI;
import org.HardCore.model.dao.StellenanzeigeDAO;
import org.HardCore.model.factory.StellenanzeigeFactory;
import org.HardCore.model.objects.dto.StellenanzeigeDetail;
import org.HardCore.model.objects.dto.Student;
import org.HardCore.model.objects.dto.User;
import org.HardCore.model.objects.entities.Stellenanzeige;

import java.util.List;

public class StellenanzeigeControl {
    private static StellenanzeigeControl search = null;

    public static StellenanzeigeControl getInstance() {
        if (search == null) {
            search = new StellenanzeigeControl();
        }
        return search;
    }

    private StellenanzeigeControl() {

    }

    public List<StellenanzeigeDetail> getAnzeigenForUnternehmen() {
        User user = ( (MyUI) UI.getCurrent() ).getUser();
        return StellenanzeigeDAO.getInstance().getStellenanzeigenForUnternehmen(user);
    }

    public List<StellenanzeigeDetail> getAnzeigenForStudent() {
        User user = ( (MyUI) UI.getCurrent() ).getUser();
        Student student = ProfileControl.getInstance().getStudent(user);
        return StellenanzeigeDAO.getInstance().getStellenanzeigeforStudent(student);

    }
    public boolean createStellenanzeige(StellenanzeigeDetail stellenanzeigeDetail){
        User user = ( (MyUI) UI.getCurrent() ).getUser();
        Stellenanzeige stellenanzeige = StellenanzeigeFactory.createStellenanzeige(stellenanzeigeDetail, user);
        return StellenanzeigeDAO.getInstance().createStellenanzeige(stellenanzeige, user);
    }
    public boolean updateStellenanzeige(StellenanzeigeDetail stellenanzeigeDetail) {
        User user = ( (MyUI) UI.getCurrent() ).getUser();
        Stellenanzeige stellenanzeige = StellenanzeigeFactory.createStellenanzeige(stellenanzeigeDetail, user);
        return StellenanzeigeDAO.getInstance().updateStellenanzeige(stellenanzeige);
    }

    public boolean deleteStellenanzeige(StellenanzeigeDetail stellenanzeigeDetail) {
        User user = ( (MyUI) UI.getCurrent() ).getUser();
        Stellenanzeige stellenanzeige = StellenanzeigeFactory.createStellenanzeige(stellenanzeigeDetail, user);
        return StellenanzeigeDAO.getInstance().deleteStellenanzeige(stellenanzeige);
    }

    public List<StellenanzeigeDetail> getAnzeigenForSearch(String suchtext) {
        return StellenanzeigeDAO.getInstance().getStellenanzeigenForSearch(suchtext);
    }
}
