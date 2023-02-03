package junit;

import com.ispwproject.adoptme.controller.appcontroller.LoginController;
import com.ispwproject.adoptme.controller.appcontroller.SendRequestController;
import com.ispwproject.adoptme.engineering.bean.LoginBean;
import com.ispwproject.adoptme.engineering.bean.PetBean;
import com.ispwproject.adoptme.engineering.bean.RequestBean;
import com.ispwproject.adoptme.engineering.bean.UserBean;
import com.ispwproject.adoptme.engineering.dao.RequestDAO;
import com.ispwproject.adoptme.engineering.exception.PastDateException;
import com.ispwproject.adoptme.engineering.session.Session;
import com.ispwproject.adoptme.engineering.utils.ShowExceptionSupport;
import com.ispwproject.adoptme.model.ShelterModel;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSendRequest {
    /**
     * FRANCESCA PAVONE SALAFIA
     * Il seguente test va a testare che andando a prelevare il numero di richieste di uno shelter
     * prima e dopo l'invio di una nuova richiesta da parte di un utente si trovi il valore aggiornato di 1
     */

    @Test
    public void testSendRequest() throws Exception {
        int requestBefore = RequestDAO.retrieveReqByShelter(new ShelterModel(1), null);

        LoginBean loginBean = new LoginBean("francesca@gmail.com", "123");
        LoginController loginController = new LoginController();
        loginController.checkLogin(loginBean);
        UserBean userBean = loginController.getLoginInfoUser(loginBean);
        Session.setSessionInstance(userBean);

        PetBean petBean = new PetBean();
        petBean.setPetId(1);
        petBean.setShelterId(1);

        RequestBean requestBean = new RequestBean(LocalDate.of(2023, 11, 16), "16", "0");

        SendRequestController sendRequestController = new SendRequestController();
        try {
            sendRequestController.sendUserRequest(petBean, requestBean, null);
        }
        catch (PastDateException e) {
            e.printStackTrace();
        }

        int requestAfter = RequestDAO.retrieveReqByShelter(new ShelterModel(1), null);

        assertEquals(requestBefore+1, requestAfter, 0);  // supera il test

    }
}
