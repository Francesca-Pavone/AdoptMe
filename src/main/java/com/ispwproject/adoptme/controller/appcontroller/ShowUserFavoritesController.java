package com.ispwproject.adoptme.controller.appcontroller;

import com.ispwproject.adoptme.controller.graficcontroller.gui.GUIUserFavoritesController;
import com.ispwproject.adoptme.engineering.bean.PetBean;
import com.ispwproject.adoptme.engineering.dao.PetDAO;
import com.ispwproject.adoptme.engineering.observer.concreteSubjects.UserFavoritesPetsList;
import com.ispwproject.adoptme.engineering.session.Session;
import com.ispwproject.adoptme.model.PetModel;
import com.ispwproject.adoptme.model.UserModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ShowUserFavoritesController {
    private UserFavoritesPetsList userFavoritesPetsList;

    public List<PetBean> getPetList(GUIUserFavoritesController observer) {
        try {
            userFavoritesPetsList = PetDAO.retrieveUserFavoritesPets(new UserModel(Session.getCurrentSession().getUserBean()), observer);
        } catch (SQLException se) {
            // Errore durante l'apertura della connessione
            se.printStackTrace();
        } catch (ClassNotFoundException driverEx) {
            // Errore nel loading del driver
            driverEx.printStackTrace();
        } catch (Exception e) {
            // Errore nel loading del driver o possibilmente nell'accesso al filesystem
            e.printStackTrace();
        }

        List<PetBean> petBeanList = new ArrayList<>();

        for (PetModel petModel : userFavoritesPetsList.getPetList()) {
            PetBean petBean = new PetBean(petModel);
            petBeanList.add(petBean);
        }
        return petBeanList;
    }
}
