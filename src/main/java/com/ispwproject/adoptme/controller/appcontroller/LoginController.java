package com.ispwproject.adoptme.controller.appcontroller;

import com.ispwproject.adoptme.model.ShelterModel;
import com.ispwproject.adoptme.model.UserModel;
import com.ispwproject.adoptme.utils.bean.LoginBean;
import com.ispwproject.adoptme.utils.bean.ShelterBean;
import com.ispwproject.adoptme.utils.bean.UserBean;
import com.ispwproject.adoptme.utils.dao.LoginDAO;
import com.ispwproject.adoptme.utils.dao.ShelterDAO;
import com.ispwproject.adoptme.utils.dao.UserDAO;


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
