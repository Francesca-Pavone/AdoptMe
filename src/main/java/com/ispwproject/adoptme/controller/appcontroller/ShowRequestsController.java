package com.ispwproject.adoptme.controller.appcontroller;

import com.ispwproject.adoptme.engineering.bean.ShelterBean;
import com.ispwproject.adoptme.engineering.bean.UserBean;
import com.ispwproject.adoptme.model.AccountInfo;
import com.ispwproject.adoptme.model.ShelterModel;
import com.ispwproject.adoptme.model.UserModel;
import com.ispwproject.adoptme.engineering.dao.RequestDAO;
import com.ispwproject.adoptme.engineering.observer.Observer;
import com.ispwproject.adoptme.engineering.session.Session;

import java.sql.SQLException;

public class ShowRequestsController {

    private ShelterModel shelterModel;
    private UserModel userModel;

    public ShowRequestsController() {
        if (Session.getCurrentSession().getShelterBean() != null){
            ShelterBean shelterBean = Session.getCurrentSession().getShelterBean();
            AccountInfo accountInfo = new AccountInfo(shelterBean.getEmail(), 1);
            this.shelterModel = new ShelterModel(shelterBean.getShelterImg(), accountInfo, shelterBean.getName(), shelterBean.getPhoneNumber(), shelterBean.getAddress(), shelterBean.getCity(), shelterBean.getWebSite());
            this.shelterModel.setId(shelterBean.getShelterId());
            //this.shelterModel = new ShelterModel(Session.getCurrentSession().getShelterBean());
        }
        else if (Session.getCurrentSession().getUserBean() != null) {
            UserBean userBean = Session.getCurrentSession().getUserBean();
            this.userModel = new UserModel(userBean.getUserId(), userBean.getProfileImg(), userBean.getEmail(), 0, userBean.getName(), userBean.getSurname());
            //this.userModel = new UserModel(Session.getCurrentSession().getUserBean());
        }
    }

    public void getRequestList(Observer observer){

        try {
            if (this.shelterModel != null)
                RequestDAO.retrieveReqByShelter(this.shelterModel, observer);
            else if (this.userModel != null)
                RequestDAO.retrieveReqByUser(this.userModel, observer);

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

    }




}
