package junit;

import com.ispwproject.adoptme.controller.appcontroller.AddPetController;
import com.ispwproject.adoptme.controller.appcontroller.LoginController;
import com.ispwproject.adoptme.engineering.bean.LoginBean;
import com.ispwproject.adoptme.engineering.bean.PetBean;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAddPet {
    /**
     * FRANCESCA PAVONE SALAFIA
     * Il seguente test va a testare il corretto funzionamento del metodo addNewPet
     * questo restituisce -1 se l'inserimento in database non è andato a buon fine,
     * altrimenti restituisce l'id dell'animale appena inserito.
     * L'id viene calcolato prelevando l'id dell'ultimo animale registrato dallo Shelter e sommando 1
     */
    @Test
    public void testAddPet(){
        try {
            LoginBean loginBean = new LoginBean("pensieri_bestiali@gmail.com", "123");
            LoginController loginController = new LoginController();
            loginController.checkLogin(loginBean);
            loginController.completeShelterLogin(loginBean);

            PetBean petBean = new PetBean();
            petBean.setName("Romi");
            petBean.setYearOfBirth(2019);
            petBean.setMonthOfBirth(1);
            petBean.setDayOfBirth(16);
            petBean.setGender(0);
            petBean.setType(0);

            AddPetController addPetController = new AddPetController(petBean);
            int petId = addPetController.addNewPet(null);

            // il test fallisce perché l'inserimento va a buon fine
            assertEquals(-1, petId);
        }
        catch (Exception ignored){
        }
    }
}
