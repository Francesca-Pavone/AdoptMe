package com.ispwproject.adoptme.controller.appcontroller;

import com.ispwproject.adoptme.engineering.dao.*;
import com.ispwproject.adoptme.engineering.exception.NotFoundException;
import com.ispwproject.adoptme.engineering.session.Session;
import com.ispwproject.adoptme.model.ShelterModel;
import com.ispwproject.adoptme.model.UserModel;
import com.ispwproject.adoptme.engineering.bean.LoginBean;
import com.ispwproject.adoptme.engineering.bean.ShelterBean;
import com.ispwproject.adoptme.engineering.bean.UserBean;

import java.time.LocalTime;


public class LoginController {

    public void checkLogin(LoginBean loginBean) {
        int type = LoginDAO.checkLogin(loginBean.getEmail(), loginBean.getPassword());
        loginBean.setAccountType(type);
    }

    public void completeUserLogin(LoginBean loginBean) throws NotFoundException {

        UserDAO userDAO;
        if (LocalTime.now().getMinute()%2 == 0) {
            userDAO = new UserDAOJDBC();
        } else {
            userDAO = new UserDAOCSV();
        }
        UserModel userModel = userDAO.retrieveUserByEmail(loginBean.getEmail());
        UserBean userBean = new UserBean(userModel.getId(), userModel.getName(), userModel.getSurname(), userModel.getEmail(), userModel.getImage());
        Session.setSessionInstance(userBean);

    }
    public void completeShelterLogin(LoginBean loginBean) throws NotFoundException {
        ShelterModel shelterModel = ShelterDAO.retrieveShelterByEmail(loginBean.getEmail());

        ShelterBean shelterBean = new ShelterBean(shelterModel.getId(), shelterModel.getShelterName(), shelterModel.getPhoneNumber(), shelterModel.getAddress(), shelterModel.getCity(), shelterModel.getWebSite(), shelterModel.getEmail());
        shelterBean.setShelterBeanImg(shelterModel.getImage());
        Session.setSessionInstance(shelterBean);

    }
}
