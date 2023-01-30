package com.ispwproject.adoptme.controller.appcontroller;

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
        if (Session.getCurrentSession().getShelterBean() != null)
            this.shelterModel = new ShelterModel(Session.getCurrentSession().getShelterBean());
        else if (Session.getCurrentSession().getUserBean() != null) {
            this.userModel = new UserModel(Session.getCurrentSession().getUserBean());
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