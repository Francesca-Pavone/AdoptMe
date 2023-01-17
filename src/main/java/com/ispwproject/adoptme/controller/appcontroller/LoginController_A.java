package com.ispwproject.adoptme.controller.appcontroller;

import com.ispwproject.adoptme.utils.bean.AccountInfoBean;
import com.ispwproject.adoptme.utils.dao.UserShelterDAO;

import java.sql.SQLException;

public class LoginController_A {
    public int checkLogin(AccountInfoBean accountInfoBean) throws SQLException, ClassNotFoundException {
        return UserShelterDAO.checkLogin(accountInfoBean.getEmail(), accountInfoBean.getPassword());
    }

    public void getUser(AccountInfoBean accountInfoBean) {

    }

    public void getShelter(AccountInfoBean accountInfoBean) {

    }
}
