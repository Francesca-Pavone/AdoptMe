package com.ispwproject.adoptme.engineering.session;

import com.ispwproject.adoptme.engineering.bean.ShelterBean;
import com.ispwproject.adoptme.engineering.bean.UserBean;

public class Session {
    private static Session sessionInstance = null;
    private UserBean userBean;
    private ShelterBean shelterBean;

    private Session(Object ob) {
        if(ob instanceof UserBean) {
            this.userBean = (UserBean) ob;
        }
        else if(ob instanceof ShelterBean) {
            shelterBean = (ShelterBean) ob;
        }
    }


    public static void setSessionInstance(Object ob) {
        if(sessionInstance == null)
            sessionInstance = new Session(ob);
    }



    public static void closeSession() {
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
