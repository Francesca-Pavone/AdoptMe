package com.ispwproject.adoptme.controller.appcontroller;

import com.ispwproject.adoptme.engineering.bean.PetBean;
import com.ispwproject.adoptme.engineering.bean.UserBean;
import com.ispwproject.adoptme.engineering.dao.PetDAO;
import com.ispwproject.adoptme.engineering.exception.FavoriteListEmptyException;
import com.ispwproject.adoptme.engineering.observer.Observer;
import com.ispwproject.adoptme.engineering.observer.concretesubjects.UserFavoritesPetsList;
import com.ispwproject.adoptme.engineering.session.Session;
import com.ispwproject.adoptme.model.PetModel;
import com.ispwproject.adoptme.model.UserModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ShowUserFavoritesController {
    private UserFavoritesPetsList userFavoritesPetsList;

    public List<PetBean> getPetList(Observer observer) throws FavoriteListEmptyException {
        try {
            UserBean userBean = Session.getCurrentSession().getUserBean();
            UserModel userModel = new UserModel(userBean.getUserId(), userBean.getProfileImg(), userBean.getEmail(), 0, userBean.getName(), userBean.getSurname());
            userFavoritesPetsList = PetDAO.retrieveUserFavoritesPets(userModel, observer);
        } catch (SQLException se) {
            // Errore durante l'apertura della connessione
            se.printStackTrace();
        } catch (ClassNotFoundException driverEx) {
            // Errore nel loading del driver
            driverEx.printStackTrace();
        } catch (Exception e) {
            // Errore nel loading del driver o possibilmente nell'accesso al filesystem
            throw new FavoriteListEmptyException();
        }

        List<PetBean> petBeanList = new ArrayList<>();

        for (PetModel petModel : userFavoritesPetsList.getPetList()) {
            PetBean petBean = new PetBean(petModel.getPetId(), petModel.getShelter().getId(), petModel.getPetImage(), petModel.getName(), petModel.getType(), petModel.getAge(), petModel.getGender());
            petBean.setFav(petModel.isFav());
            petBeanList.add(petBean);
        }
        return petBeanList;
    }
}
