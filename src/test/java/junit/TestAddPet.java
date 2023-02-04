package junit;

import com.ispwproject.adoptme.controller.appcontroller.AddPetController;
import com.ispwproject.adoptme.engineering.bean.PetBean;
import com.ispwproject.adoptme.engineering.bean.ShelterBean;
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
        ShelterBean shelterBean = new ShelterBean();
        shelterBean.setShelterId(1);

        PetBean petBean = new PetBean();
        petBean.setName("Nala");
        petBean.setYearOfBirth(2019);
        petBean.setMonthOfBirth(1);
        petBean.setDayOfBirth(16);
        petBean.setGender(0);
        petBean.setType(0);

        AddPetController addPetController = new AddPetController(petBean);
        int petId = addPetController.addNewPet(shelterBean, null);

        // il test fallisce perché l'inserimento va a buon fine
        assertEquals(-1, petId);
    }
}
