package junit;

import com.ispwproject.adoptme.controller.appcontroller.LoginController;
import com.ispwproject.adoptme.engineering.bean.LoginBean;
import com.ispwproject.adoptme.engineering.exception.EmailFormatException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestLoginController {
    /**
     * FEDERICA CANTELMI
     * Il seguente test va a verificare se viene settato il tipo di account corretto.
     */
    @Test
    void testCheckLogin() throws EmailFormatException {
        LoginBean loginBean = new LoginBean("federica@gmail.com", "789");
        LoginController loginController = new LoginController();
        loginController.checkLogin(loginBean);

        int accountType2 = 0;
        int accountType1 = loginBean.getAccountType();

        assertEquals(accountType1, accountType2, 0); //test ha successo
    }
}
