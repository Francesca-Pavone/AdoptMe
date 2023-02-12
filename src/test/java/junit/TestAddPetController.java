package junit;

import com.ispwproject.adoptme.controller.appcontroller.AddPetController;
import com.ispwproject.adoptme.controller.appcontroller.LoginController;
import com.ispwproject.adoptme.engineering.bean.LoginBean;
import com.ispwproject.adoptme.engineering.bean.PetBean;
import com.ispwproject.adoptme.engineering.bean.PetInformationBean;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestAddPetController {
    /**
     * FRANCESCA PAVONE SALAFIA
     * Il seguente test va a testare il corretto funzionamento del metodo addNewPet
     * questo restituisce -1 se l'inserimento in database non è andato a buon fine,
     * altrimenti restituisce l'id dell'animale appena inserito.
     * L'id viene calcolato prelevando l'id dell'ultimo animale registrato dallo Shelter e sommando 1
     */
    @Test
    void testAddNewPet(){
        try {
            LoginBean loginBean = new LoginBean("pensieri_bestiali@gmail.com", "123");
            LoginController loginController = new LoginController();
            loginController.checkLogin(loginBean);
            loginController.completeShelterLogin(loginBean);

            PetBean petBean = new PetBean();
            petBean.setPetBeanName("Axel");
            petBean.setPetBeanBirthYear(2019);
            petBean.setPetBeanBirthMonth(1);
            petBean.setPetBeanBirthDay(16);
            petBean.setPetBeanGender(0);
            petBean.setPetBeanType(0);
            PetInformationBean petInformationBean = new PetInformationBean();

            AddPetController addPetController = new AddPetController(petBean, petInformationBean);
            int petId = addPetController.addNewPet(null);

            // il test fallisce perché l'inserimento va a buon fine
            assertEquals(-1, petId);
        }
        catch (Exception ignored){
        }
    }
}
