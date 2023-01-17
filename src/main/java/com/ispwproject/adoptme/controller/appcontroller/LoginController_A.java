package com.ispwproject.adoptme.controller.appcontroller;

import com.ispwproject.adoptme.model.AccountInfo;
import com.ispwproject.adoptme.model.ShelterModel;
import com.ispwproject.adoptme.model.ShelterUserModel;
import com.ispwproject.adoptme.model.UserModel;
import com.ispwproject.adoptme.utils.bean.LoginBean;
import com.ispwproject.adoptme.utils.bean.ShelterBean;
import com.ispwproject.adoptme.utils.bean.UserBean;
import com.ispwproject.adoptme.utils.dao.LoginDAO;
import com.ispwproject.adoptme.utils.dao.ShelterDAO;
import com.ispwproject.adoptme.utils.dao.UserDAO;
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

    public Session getLoginInfo(LoginBean loginBean) throws Exception {
        Session session;
        if (loginBean.getAccountType() == 1) {
            UserModel userModel;
            userModel = UserDAO.retrieveUserByEmail(loginBean.getEmail());
            UserBean userBean = new UserBean(userModel);

            session = new Session(userBean);

        } else if (loginBean.getAccountType() == 2) {
            ShelterModel shelterModel;
            shelterModel = ShelterDAO.retrieveShelterByEmail(loginBean.getEmail());
            ShelterBean shelterBean = new ShelterBean(shelterModel);

            session = new Session(shelterBean);

        }
        else
            session = new Session();

        return session;
    }
}
