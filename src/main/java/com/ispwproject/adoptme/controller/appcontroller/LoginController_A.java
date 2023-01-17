package com.ispwproject.adoptme.controller.appcontroller;

import com.ispwproject.adoptme.model.AccountInfo;
import com.ispwproject.adoptme.utils.bean.LoginBean;
import com.ispwproject.adoptme.utils.dao.LoginDAO;
import com.ispwproject.adoptme.utils.session.Session;

import java.sql.SQLException;

public class LoginController_A {
    public void checkLogin(LoginBean loginBean) throws SQLException, ClassNotFoundException {
        System.out.println("Email: "+loginBean.getEmail());
        System.out.println("Password: "+loginBean.getPassword());
        int type = LoginDAO.checkLogin(loginBean.getEmail(), loginBean.getPassword());
        System.out.println("Type: "+type);
        loginBean.setAccountType(type);
        System.out.println("Account type: "+loginBean.getAccountType());
    }

    public void noLogin() {
        Session sessionNoLog = new Session();
    }

    public void getLoginInfo(LoginBean loginBean) throws Exception {
        Session session = new Session(loginBean.getAccountType(), loginBean.getEmail());
    }
}
