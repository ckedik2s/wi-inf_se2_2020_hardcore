package org.HardCore.process.control;

import com.vaadin.ui.UI;
import org.HardCore.gui.ui.MyUI;
import org.HardCore.model.objects.dto.StellenanzeigeDetail;
import org.HardCore.model.objects.dto.Student;
import org.HardCore.model.objects.dto.Unternehmen;
import org.HardCore.model.objects.dto.User;
import org.HardCore.services.util.Roles;

import java.util.List;

public class SearchControl {
    private static SearchControl search = null;

    public static SearchControl getInstance() {
        if (search == null) {
            search = new SearchControl();
        }
        return search;
    }

    private SearchControl() {

    }

    public List<StellenanzeigeDetail> getAnzeigenForUser() {
        User user = ( (MyUI)UI.getCurrent() ).getUser();
        if (user.hasRole(Roles.STUDENT)) {
            Student student = new Student(user);
            return StellenanzeigeControl.getInstance().getAnzeigenForStudent(student);
        }
        Unternehmen unternehmen = new Unternehmen(user);
        return StellenanzeigeControl.getInstance().getAnzeigenForUnternehmen(unternehmen);
    }

    public List<StellenanzeigeDetail> getAnzeigenForSearch(String suchtext, String filter) {
        if (filter == null) {
            filter = "name";
        }
        return StellenanzeigeControl.getInstance().getAnzeigenForSearch(suchtext, filter);
    }
}
