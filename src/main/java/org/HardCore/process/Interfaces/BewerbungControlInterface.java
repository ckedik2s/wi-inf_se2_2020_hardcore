package org.HardCore.process.Interfaces;

import com.vaadin.ui.Button;
import org.HardCore.model.objects.dto.BewerbungDTO;
import org.HardCore.model.objects.dto.StellenanzeigeDetail;
import org.HardCore.model.objects.dto.StudentDTO;
import org.HardCore.model.objects.dto.UserDTO;
import org.HardCore.process.exceptions.BewerbungException;
import org.HardCore.process.exceptions.DatabaseException;

import java.util.List;

public interface BewerbungControlInterface {

    int getLatestApply(UserDTO userDTO) throws DatabaseException;

    void applyForStellenanzeige(StellenanzeigeDetail stellenanzeige, int id_bewerbung) throws DatabaseException;

    void applyingIsAllowed() throws DatabaseException, BewerbungException;

    void checkAlreadyApplied(StellenanzeigeDetail stellenanzeigeDetail, UserDTO userDTO) throws BewerbungException, DatabaseException;

    void checkAllowed(StellenanzeigeDetail stellenanzeige, UserDTO userDTO, Button bewerbenButton);

    void createBewerbung(String bewerbungstext, UserDTO userDTO) throws BewerbungException;

    BewerbungDTO getBewerbungForStellenanzeige(StellenanzeigeDetail selektiert, StudentDTO studentDTO);

    List<BewerbungDTO> getBewerbungenForStudent(StudentDTO studentDTO);

    void deleteBewerbung(BewerbungDTO bewerbungDTO) throws BewerbungException;
}
