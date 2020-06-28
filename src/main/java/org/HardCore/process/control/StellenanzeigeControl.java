package org.HardCore.process.control;

import com.vaadin.ui.UI;
import org.HardCore.gui.ui.MyUI;
import org.HardCore.model.dao.StellenanzeigeDAO;
import org.HardCore.model.factory.StellenanzeigeFactory;
import org.HardCore.model.objects.dto.StellenanzeigeDetail;
import org.HardCore.model.objects.dto.StudentDTO;
import org.HardCore.model.objects.dto.UnternehmenDTO;
import org.HardCore.model.objects.dto.UserDTO;
import org.HardCore.model.objects.entities.Stellenanzeige;
import org.HardCore.process.Interfaces.StellenanzeigeControlInterface;
import org.HardCore.process.exceptions.DatabaseException;
import org.HardCore.process.exceptions.StellenanzeigeException;
import org.HardCore.services.db.JDBCConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class StellenanzeigeControl implements StellenanzeigeControlInterface {
    private static StellenanzeigeControl search = null;

    public static StellenanzeigeControl getInstance() {
        if (search == null) {
            search = new StellenanzeigeControl();
        }
        return search;
    }

    private StellenanzeigeControl() {

    }

    public List<StellenanzeigeDetail> getAnzeigenForUnternehmen(UnternehmenDTO unternehmenDTO) {
        return StellenanzeigeDAO.getInstance().getStellenanzeigenForUnternehmen(unternehmenDTO);
    }

    public List<StellenanzeigeDetail> getAnzeigenForStudent(StudentDTO studentDTO) {
        return StellenanzeigeDAO.getInstance().getStellenanzeigeforStudent(studentDTO);

    }
    public void createStellenanzeige(StellenanzeigeDetail stellenanzeigeDetail) throws StellenanzeigeException {
        UserDTO userDTO = ( (MyUI) UI.getCurrent() ).getUserDTO();
        Stellenanzeige stellenanzeige = StellenanzeigeFactory.createStellenanzeige(stellenanzeigeDetail, userDTO);
        boolean result = StellenanzeigeDAO.getInstance().createStellenanzeige(stellenanzeige, userDTO);
        if (result) {
            return;
        }
        throw new StellenanzeigeException();
    }
    public void updateStellenanzeige(StellenanzeigeDetail stellenanzeigeDetail) throws StellenanzeigeException {
        UserDTO userDTO = ( (MyUI) UI.getCurrent() ).getUserDTO();
        Stellenanzeige stellenanzeige = StellenanzeigeFactory.createStellenanzeige(stellenanzeigeDetail, userDTO);
        boolean result = StellenanzeigeDAO.getInstance().updateStellenanzeige(stellenanzeige);
        if (result) {
            return;
        }
        throw new StellenanzeigeException();
    }

    public void deleteStellenanzeige(StellenanzeigeDetail stellenanzeigeDetail) throws StellenanzeigeException {
        UserDTO userDTO = ( (MyUI) UI.getCurrent() ).getUserDTO();
        Stellenanzeige stellenanzeige = StellenanzeigeFactory.createStellenanzeige(stellenanzeigeDetail, userDTO);
        boolean result =  StellenanzeigeDAO.getInstance().deleteStellenanzeige(stellenanzeige);
        if (result) {
            return;
        }
        throw new StellenanzeigeException();
    }

    public List<StellenanzeigeDetail> getAnzeigenForSearch(String suchtext, String filter) {
        return StellenanzeigeDAO.getInstance().getStellenanzeigenForSearch(suchtext, filter);
    }

    public int getAnzahlBewerber(StellenanzeigeDetail stellenanzeigeDetail) throws DatabaseException {

        int anzahl_bewerber = 0;
        ResultSet rs = null;
        Statement statement = JDBCConnection.getInstance().getStatement();
        try {
            rs = statement.executeQuery("SELECT count(id_bewerbung) " +
                    "FROM collhbrs.bewerbung_to_stellenanzeige " +
                    "WHERE id_anzeige = \'" + stellenanzeigeDetail.getId_anzeige() + "\'");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new DatabaseException("Fehler im SQL-Befehl: Bitte den Programmierer informieren!");
        }

        if(rs==null){
            return anzahl_bewerber;
        }

        try {
            if( rs.next() ) {
                anzahl_bewerber = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            JDBCConnection.getInstance().closeConnection();
        }

        return anzahl_bewerber;
    }
}
