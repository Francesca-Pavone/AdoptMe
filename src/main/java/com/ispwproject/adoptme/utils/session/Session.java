package com.ispwproject.adoptme.utils.session;

import com.ispwproject.adoptme.model.ShelterModel;
import com.ispwproject.adoptme.model.UserModel;
import com.ispwproject.adoptme.utils.bean.ShelterBean;
import com.ispwproject.adoptme.utils.bean.UserBean;
import com.ispwproject.adoptme.utils.dao.ShelterDAO;
import com.ispwproject.adoptme.utils.dao.UserDAO;

public class Session {

    //private boolean logged; TODO: aggiungere al database ed controllare se l'utente è già loggato
    private static UserBean userBean;
    private static ShelterBean shelterBean;

    public Session() {
    }

    public Session(UserBean userBean) {
        setUserBean(userBean);
    }

    public Session(ShelterBean shelterBean) {
        setShelterBean(shelterBean);
    }

    public static UserBean getUserBean() {
        return userBean;
    }

    private void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public static ShelterBean getShelterBean() {
        return shelterBean;
    }

    private void setShelterBean(ShelterBean shelterBean) {
        this.shelterBean = shelterBean;
    }
}
