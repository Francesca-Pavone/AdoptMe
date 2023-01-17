package com.ispwproject.adoptme.utils.session;

import com.ispwproject.adoptme.model.ShelterModel;
import com.ispwproject.adoptme.model.UserModel;
import com.ispwproject.adoptme.utils.dao.ShelterDAO;
import com.ispwproject.adoptme.utils.dao.UserDAO;

public class Session {

    //private boolean logged; TODO: aggiungere al database ed controllare se l'utente è già loggato
    private UserModel userModel;
    private ShelterModel shelterModel;

    public Session() {
    }

    public Session(int type, String email) throws Exception {
        if (type == 1)
            userModel = UserDAO.retrieveUserByEmail(email);
        else if (type == 2)
            shelterModel = ShelterDAO.retrieveShelterByEmail(email);
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public ShelterModel getShelterModel() {
        return shelterModel;
    }
}
