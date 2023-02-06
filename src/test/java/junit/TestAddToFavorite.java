package junit;

import com.ispwproject.adoptme.controller.appcontroller.AddToFavoritesController;
import com.ispwproject.adoptme.controller.appcontroller.LoginController;
import com.ispwproject.adoptme.engineering.bean.LoginBean;
import com.ispwproject.adoptme.engineering.bean.PetBean;
import com.ispwproject.adoptme.engineering.bean.UserBean;
import com.ispwproject.adoptme.engineering.dao.FavoritesDAO;
import com.ispwproject.adoptme.engineering.session.Session;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAddToFavorite {
    @Test
    public void testAddToFavorite() throws Exception {
        LoginBean loginBean = new LoginBean("francesca@gmail.com", "123");
        LoginController loginController = new LoginController();
        loginController.checkLogin(loginBean);
        loginController.completeUserLogin(loginBean);

        UserBean userBean = Session.getCurrentSession().getUserBean();

        PetBean petBean = new PetBean();
        petBean.setPetId(1);
        petBean.setShelterId(1);

        //metti try e vedi se ti si solleva eccezione perchè è già favorite
        if(!FavoritesDAO.checkFav(petBean.getPetId(), userBean.getUserId(), petBean.getShelterId())) {
            AddToFavoritesController addToFavoritesController = new AddToFavoritesController(petBean);
            addToFavoritesController.addPet(userBean, null, null);
        }

        int ret = 0;
        if(FavoritesDAO.checkFav(petBean.getPetId(), userBean.getUserId(), petBean.getShelterId()))
            ret = 1;

        assertEquals(1, ret, 0); //supera il test

    }
}
