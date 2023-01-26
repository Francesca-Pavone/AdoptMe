package com.ispwproject.adoptme.utils.session;

import com.ispwproject.adoptme.utils.bean.ShelterBean;
import com.ispwproject.adoptme.utils.bean.UserBean;

public class Session {
    private static Session sessionInstance = null;
    private int type; // 0 -> user; 1 -> shelter
    private UserBean userBean;
    private ShelterBean shelterBean;

    private Session(Object ob) {
        if(ob instanceof UserBean)
            userBean = (UserBean) ob;
        else if(ob instanceof ShelterBean)
            shelterBean = (ShelterBean) ob;
    }

    public static Session getSessionInstance(Object ob) {
        if(sessionInstance == null)
            sessionInstance = new Session(ob);
        return sessionInstance;
    }

    public void closeSession() {
           sessionInstance = null;
    }

    public static Session getCurrentSession() {
        return sessionInstance;
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public ShelterBean getShelterBean() {
        return shelterBean;
    }

}
