package junit;

import com.ispwproject.adoptme.controller.appcontroller.LoginController;
import com.ispwproject.adoptme.controller.appcontroller.ManageRequestController;
import com.ispwproject.adoptme.engineering.bean.LoginBean;
import com.ispwproject.adoptme.engineering.bean.PetBean;
import com.ispwproject.adoptme.engineering.bean.RequestBean;
import com.ispwproject.adoptme.engineering.dao.RequestDAO;
import com.ispwproject.adoptme.engineering.exception.DateFormatException;
import com.ispwproject.adoptme.engineering.exception.DuplicateRequestException;
import com.ispwproject.adoptme.engineering.exception.PastDateException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestManageRequestController {
    /**
     * FRANCESCA PAVONE SALAFIA
     * Il seguente test va a testare che andando a prelevare il numero di richieste di uno shelter
     * prima e dopo l'invio di una nuova richiesta da parte di un utente si trovi il valore aggiornato di 1
     */

    @Test
    void testSendRequest() throws PastDateException, DuplicateRequestException, DateFormatException {
        try {
            int requestBefore = RequestDAO.retrieveReqByOwnerId(1, 1).size();

            LoginBean loginBean = new LoginBean("francesca@gmail.com", "123");
            LoginController loginController = new LoginController();
            loginController.checkLogin(loginBean);
            loginController.completeUserLogin(loginBean);

            PetBean petBean = new PetBean();
            petBean.setPetBeanId(1);
            petBean.setPetBeanShelter(1);

            RequestBean requestBean = new RequestBean("17-03-2023", "16:00");

            ManageRequestController manageRequestController = new ManageRequestController();
            manageRequestController.sendRequest(petBean, requestBean);

            int requestAfter = RequestDAO.retrieveReqByOwnerId(1, 1).size();

            assertEquals(requestBefore + 1, requestAfter, 0);  // supera il test
        }
        catch (PastDateException | DateFormatException | DuplicateRequestException e) {
            throw e;
        }
        catch (Exception ignored) {
        }
    }
}
