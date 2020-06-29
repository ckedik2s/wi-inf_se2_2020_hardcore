import org.HardCore.process.proxy.RegistrationControlProxy;
import org.junit.Test;

import static org.junit.Assert.*;

public class RegistrationControlProxyTest {

    @Test
    public void createTest() {
        RegistrationControlProxy regicontprox = RegistrationControlProxy.getInstance();
        assertNotNull(regicontprox);
    }



}