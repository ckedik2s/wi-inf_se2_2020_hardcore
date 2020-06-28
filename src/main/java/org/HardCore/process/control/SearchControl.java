package org.HardCore.process.control;

import com.vaadin.ui.UI;
import org.HardCore.gui.ui.MyUI;
import org.HardCore.model.objects.dto.StellenanzeigeDetail;
import org.HardCore.model.objects.dto.StudentDTO;
import org.HardCore.model.objects.dto.UnternehmenDTO;
import org.HardCore.model.objects.dto.UserDTO;
import org.HardCore.process.Interfaces.SearchControlInterface;
import org.HardCore.services.util.Roles;

import java.util.List;

public class SearchControl implements SearchControlInterface {
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
        UserDTO userDTO = ( (MyUI)UI.getCurrent() ).getUserDTO();
        if (userDTO.hasRole(Roles.STUDENT)) {
            StudentDTO studentDTO = new StudentDTO(userDTO);
            return StellenanzeigeControl.getInstance().getAnzeigenForStudent(studentDTO);
        }
        UnternehmenDTO unternehmenDTO = new UnternehmenDTO(userDTO);
        return StellenanzeigeControl.getInstance().getAnzeigenForUnternehmen(unternehmenDTO);
    }

    public List<StellenanzeigeDetail> getAnzeigenForSearch(String suchtext, String filter) {
        if (filter == null) {
            filter = "name";
        }
        return StellenanzeigeControl.getInstance().getAnzeigenForSearch(suchtext, filter);
    }
}
