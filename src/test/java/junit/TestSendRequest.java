package junit;

import com.ispwproject.adoptme.controller.appcontroller.LoginController;
import com.ispwproject.adoptme.controller.appcontroller.ManageRequestController;
import com.ispwproject.adoptme.engineering.bean.LoginBean;
import com.ispwproject.adoptme.engineering.bean.PetBean;
import com.ispwproject.adoptme.engineering.bean.RequestBean;
import com.ispwproject.adoptme.engineering.dao.RequestDAO;
import com.ispwproject.adoptme.engineering.exception.francesca.DateFormatException;
import com.ispwproject.adoptme.engineering.exception.francesca.DuplicateRequestException;
import com.ispwproject.adoptme.engineering.exception.francesca.PastDateException;
import com.ispwproject.adoptme.model.ShelterModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestSendRequest {
    /**
     * FRANCESCA PAVONE SALAFIA
     * Il seguente test va a testare che andando a prelevare il numero di richieste di uno shelter
     * prima e dopo l'invio di una nuova richiesta da parte di un utente si trovi il valore aggiornato di 1
     */

    @Test
    void testSendRequest() throws PastDateException, DuplicateRequestException, DateFormatException {
        try {
            int requestBefore = RequestDAO.retrieveReqByShelter(new ShelterModel(1)).size();

            LoginBean loginBean = new LoginBean("francesca@gmail.com", "123");
            LoginController loginController = new LoginController();
            loginController.checkLogin(loginBean);
            loginController.completeUserLogin(loginBean);

            PetBean petBean = new PetBean();
            petBean.setPetId(1);
            petBean.setShelterId(1);

            RequestBean requestBean = new RequestBean("16-03-2023", "16:00");

            ManageRequestController manageRequestController = new ManageRequestController();
            manageRequestController.sendRequest(petBean, requestBean);

            int requestAfter = RequestDAO.retrieveReqByShelter(new ShelterModel(1)).size();

            assertEquals(requestBefore + 1, requestAfter, 0);  // supera il test
        }
        catch (PastDateException | DateFormatException | DuplicateRequestException e) {
            throw e;
        }
        catch (Exception ignored) {
        }
    }
}
