package org.HardCore.process.control;

import com.vaadin.ui.UI;
import org.HardCore.gui.ui.MyUI;
import org.HardCore.model.dao.BewerbungDAO;
import org.HardCore.model.dao.StellenanzeigeDAO;
import org.HardCore.model.factory.StellenanzeigeFactory;
import org.HardCore.model.objects.dto.StellenanzeigeDetail;
import org.HardCore.model.objects.dto.Student;
import org.HardCore.model.objects.dto.Unternehmen;
import org.HardCore.model.objects.dto.User;
import org.HardCore.model.objects.entities.Stellenanzeige;
import org.HardCore.process.control.exceptions.DatabaseException;
import org.HardCore.process.control.exceptions.NoSuchUserOrPassword;
import org.HardCore.services.db.JDBCConnection;
import org.HardCore.services.util.Roles;
import org.HardCore.services.util.Views;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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

    public List<StellenanzeigeDetail> getAnzeigenForUnternehmen(Unternehmen unternehmen) {
        return StellenanzeigeDAO.getInstance().getStellenanzeigenForUnternehmen(unternehmen);
    }

    public List<StellenanzeigeDetail> getAnzeigenForStudent(Student student) {
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

    public boolean deleteBewerbung(int id_anzeige) {
        return BewerbungDAO.getInstance().deleteBewerbung(id_anzeige);
    }
}
