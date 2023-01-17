package com.ispwproject.adoptme.controller.appcontroller;

import com.ispwproject.adoptme.model.AccountInfo;
import com.ispwproject.adoptme.utils.bean.LoginBean;
import com.ispwproject.adoptme.utils.bean.ShelterBean;
import com.ispwproject.adoptme.utils.bean.UserBean;
import com.ispwproject.adoptme.utils.dao.LoginDAO;
import com.ispwproject.adoptme.utils.session.Session;

import java.sql.SQLException;

public class LoginController_A {

    public void checkLogin(LoginBean loginBean) throws SQLException, ClassNotFoundException {
        int type = LoginDAO.checkLogin(loginBean.getEmail(), loginBean.getPassword());
        loginBean.setAccountType(type);
    }

    public void noLogin() {
        Session sessionNoLog = new Session();
    }

    public UserBean getUserSession(LoginBean loginBean) throws Exception {
        Session session = new Session(loginBean.getAccountType(), loginBean.getEmail());
        return new UserBean(session.getUserModel());
    }

    public ShelterBean getShelterSession(LoginBean loginBean) throws Exception {
        Session session = new Session(loginBean.getAccountType(), loginBean.getEmail());
        return new ShelterBean(session.getShelterModel());
    }

}
