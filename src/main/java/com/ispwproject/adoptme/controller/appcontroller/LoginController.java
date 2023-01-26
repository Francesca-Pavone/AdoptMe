package com.ispwproject.adoptme.controller.appcontroller;

import com.ispwproject.adoptme.model.ShelterModel;
import com.ispwproject.adoptme.model.UserModel;
import com.ispwproject.adoptme.engineering.bean.LoginBean;
import com.ispwproject.adoptme.engineering.bean.ShelterBean;
import com.ispwproject.adoptme.engineering.bean.UserBean;
import com.ispwproject.adoptme.engineering.dao.LoginDAO;
import com.ispwproject.adoptme.engineering.dao.ShelterDAO;
import com.ispwproject.adoptme.engineering.dao.UserDAO;


public class LoginController {

    public void checkLogin(LoginBean loginBean) {
        int type = LoginDAO.checkLogin(loginBean.getEmail(), loginBean.getPassword());
        loginBean.setAccountType(type);
    }

    public UserBean getLoginInfoUser(LoginBean loginBean) throws Exception {
        UserModel userModel = UserDAO.retrieveUserByEmail(loginBean.getEmail());
        return new UserBean(userModel);
    }
    public ShelterBean getLoginInfoShelter(LoginBean loginBean) throws Exception {
            ShelterModel shelterModel = ShelterDAO.retrieveShelterByEmail(loginBean.getEmail());
            return new ShelterBean(shelterModel);
    }
}
