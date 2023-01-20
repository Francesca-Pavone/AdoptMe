package com.ispwproject.adoptme.controller.appcontroller;

import com.ispwproject.adoptme.model.ShelterModel;
import com.ispwproject.adoptme.model.UserModel;
import com.ispwproject.adoptme.utils.bean.LoginBean;
import com.ispwproject.adoptme.utils.bean.ShelterBean;
import com.ispwproject.adoptme.utils.bean.UserBean;
import com.ispwproject.adoptme.utils.dao.LoginDAO;
import com.ispwproject.adoptme.utils.dao.ShelterDAO;
import com.ispwproject.adoptme.utils.dao.UserDAO;
import com.ispwproject.adoptme.utils.session.Session;

import java.sql.SQLException;

public class LoginController {

    public void checkLogin(LoginBean loginBean) throws SQLException, ClassNotFoundException {
        int type = LoginDAO.checkLogin(loginBean.getEmail(), loginBean.getPassword());
        loginBean.setAccountType(type);
    }

    public Session getLoginInfo(LoginBean loginBean) throws Exception {
        Session session;
        if (loginBean.getAccountType() == 1) {
            UserModel userModel = UserDAO.retrieveUserByEmail(loginBean.getEmail());
            UserBean userBean = new UserBean(userModel);
            session = new Session(userBean);

        } else {
            ShelterModel shelterModel = ShelterDAO.retrieveShelterByEmail(loginBean.getEmail());
            ShelterBean shelterBean = new ShelterBean(shelterModel);

            session = new Session(shelterBean);

        }
        return session;
    }
}
