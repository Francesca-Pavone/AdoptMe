package com.ispwproject.adoptme.utils.session;

import com.ispwproject.adoptme.utils.bean.ShelterBean;
import com.ispwproject.adoptme.utils.bean.UserBean;

public class Session {

    //private boolean logged; TODO: aggiungere al database ed controllare se l'utente è già loggato
    private UserBean userBean;
    private ShelterBean shelterBean;

    public Session() {
    }

    public Session(UserBean userBean) {
        setUserBean(userBean);
    }

    public Session(ShelterBean shelterBean) {
        setShelterBean(shelterBean);
    }

    public UserBean getUserBean() {
        return userBean;
    }

    private void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public ShelterBean getShelterBean() {
        return shelterBean;
    }

    private void setShelterBean(ShelterBean shelterBean) {
        this.shelterBean = shelterBean;
    }
}
