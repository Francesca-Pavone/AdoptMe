package junit;

import com.ispwproject.adoptme.controller.appcontroller.LoginController;
import com.ispwproject.adoptme.engineering.bean.LoginBean;
import com.ispwproject.adoptme.engineering.exception.EmailFormatException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestLoginController {
    /**
     * FEDERICA CANTELMI
     * Il seguente test va a verificare se viene settato il tipo di account corretto.
     */
    @Test
    void testCheckLogin() throws EmailFormatException {
        LoginBean loginBean = new LoginBean("federica@gmail.com", "789");
        LoginController loginController = new LoginController();
        loginController.checkLogin(loginBean);

        int accountExpected = 1;
        int accountActual = loginBean.getAccountType();

        assertEquals(accountExpected, accountActual); //test ha successo
    }
}
