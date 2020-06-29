package org.HardCore.process.Interfaces;

import com.vaadin.ui.Button;
import org.HardCore.model.objects.dto.BewerbungDTO;
import org.HardCore.model.objects.dto.StellenanzeigeDetail;
import org.HardCore.model.objects.dto.StudentDTO;
import org.HardCore.model.objects.dto.UserDTO;
import org.HardCore.process.exceptions.BewerbungException;
import org.HardCore.process.exceptions.DatabaseException;

import java.sql.SQLException;
import java.util.List;

public interface BewerbungControlInterface {

    int getLatestApply(UserDTO userDTO) throws DatabaseException, SQLException;

    void applyForStellenanzeige(StellenanzeigeDetail stellenanzeige, int id_bewerbung) throws DatabaseException;

    void applyingIsAllowed() throws DatabaseException, BewerbungException, SQLException;

    void checkAlreadyApplied(StellenanzeigeDetail stellenanzeigeDetail, UserDTO userDTO) throws BewerbungException, DatabaseException, SQLException;

    void checkAllowed(StellenanzeigeDetail stellenanzeige, UserDTO userDTO, Button bewerbenButton);

    void createBewerbung(String bewerbungstext, UserDTO userDTO) throws BewerbungException;

    BewerbungDTO getBewerbungForStellenanzeige(StellenanzeigeDetail selektiert, StudentDTO studentDTO) throws SQLException, DatabaseException;

    List<BewerbungDTO> getBewerbungenForStudent(StudentDTO studentDTO) throws SQLException;

    void deleteBewerbung(BewerbungDTO bewerbungDTO) throws BewerbungException;
}
