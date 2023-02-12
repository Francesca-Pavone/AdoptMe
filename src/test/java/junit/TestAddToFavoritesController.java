package junit;

import com.ispwproject.adoptme.controller.appcontroller.AddToFavoritesController;
import com.ispwproject.adoptme.controller.appcontroller.LoginController;
import com.ispwproject.adoptme.engineering.bean.LoginBean;
import com.ispwproject.adoptme.engineering.bean.PetBean;
import com.ispwproject.adoptme.engineering.bean.PetInformationBean;
import com.ispwproject.adoptme.engineering.bean.UserBean;
import com.ispwproject.adoptme.engineering.dao.FavoritesDAO;
import com.ispwproject.adoptme.engineering.session.Session;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestAddToFavoritesController {
    /**
     * FEDERICA CANTELMI
     * Il seguente test va a verificare se il pet viene realmente aggiunto ai preferiti di un utente andando a
     * controllare che venga inserita correttamente la riga nel database.
     */
    @Test
    void testAddPet() throws Exception {
        LoginBean loginBean = new LoginBean("federica@gmail.com", "789");
        LoginController loginController = new LoginController();
        loginController.checkLogin(loginBean);
        loginController.completeUserLogin(loginBean);

        UserBean userBean = Session.getCurrentSession().getUserBean();

        int ret = 0;

        PetBean petBean = new PetBean();
        petBean.setPetBeanId(2);
        petBean.setPetBeanShelter(1);
        PetInformationBean petInformationBean = new PetInformationBean();

        AddToFavoritesController addToFavoritesController = new AddToFavoritesController(petBean, petInformationBean);
        addToFavoritesController.removePet(userBean, null, null);
        addToFavoritesController.addPet(userBean, null, null);

        if(FavoritesDAO.checkFav(petBean.getPetBeanId(), petBean.getPetBeanShelter(), userBean.getUserId()))
            ret = 1;

        assertEquals(1, ret, 0); //il test ha successo perchè, se il pet era già tra i preferiti
        // dell'utente, viene rimosso. Quindi non può venire sollevata alcuna eccezione all'inserimento.
    }
}
