package Builder;

import org.hardcore.model.objects.dto.UnternehmenDTO;
import org.hardcore.model.objects.dto.UserDTO;

public class UnternehmenDTOBuilder {

    private static UnternehmenDTOBuilder builder = null;
    private UnternehmenDTO unternehmenDTO;
    private UserDTO userDTO;

    public static UnternehmenDTOBuilder getInstance() {
        if(builder==null) {
            builder = new UnternehmenDTOBuilder();
        }
        return builder;
    }

    public UnternehmenDTOBuilder withName(String name) { //name
        unternehmenDTO.setName(name);
        return this;	//return um chain zu bilden
    }
    public UnternehmenDTOBuilder withNoName() { //kein name
        unternehmenDTO.setName("");
        return this;
    }
    public UnternehmenDTOBuilder withVorname(String name) { //name
        unternehmenDTO.setName(name);
        return this;	//return um chain zu bilden
    }
    public UnternehmenDTOBuilder withNoVorname(String name) { //name
        unternehmenDTO.setName(name);
        return this;	//return um chain zu bilden
    }
    public UnternehmenDTOBuilder withEmail(String email) { //email
        unternehmenDTO.setEmail(email);
        return this; //return um chain zu bilden
    }
    public UnternehmenDTOBuilder withNoEmail() { //keine email
        unternehmenDTO.setEmail("");
        return this;
    }
    public UnternehmenDTO thatsAll() { //Gibt dto zurÃ¼ck (gleich wie done)
            return new UnternehmenDTO(userDTO);
    }
    public UnternehmenDTO done() { //Gibt dto zurÃ¼ck (gleich wie thatsAll)
        return new UnternehmenDTO(userDTO);
    }
    public UnternehmenDTOBuilder createDefaultUser() {
        return this;
    }


}
