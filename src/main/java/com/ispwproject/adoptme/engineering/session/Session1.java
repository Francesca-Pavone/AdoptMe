package com.ispwproject.adoptme.engineering.session;

import com.ispwproject.adoptme.engineering.bean.ShelterBean;
import com.ispwproject.adoptme.engineering.bean.UserBean;

public class Session1 {
    private static Session1 sessionInstance = null;
    private int type; // 0 -> user; 1 -> shelter
    private UserBean userBean;
    private ShelterBean shelterBean;

    private Session1(Object ob) {
        if(ob instanceof UserBean)
            userBean = (UserBean) ob;
        else if(ob instanceof ShelterBean)
            shelterBean = (ShelterBean) ob;
    }

    public static Session1 getSessionInstance(Object ob) {
        if(sessionInstance == null)
            sessionInstance = new Session1(ob);
        return sessionInstance;
    }

    public void closeSession() {
           sessionInstance = null;
    }

    public static Session1 getSession() {
        return sessionInstance;
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public ShelterBean getShelterBean() {
        return shelterBean;
    }

    /*
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
    }*/
}
