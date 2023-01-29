package com.ispwproject.adoptme.view.cli;

import com.ispwproject.adoptme.engineering.bean.RequestBean;

public class CLISendRequestView {

    public void showSendRequestForm(RequestBean requestBean) {
        System.out.println("New request from '" + requestBean.getUserName() + "' to meet '" + requestBean.getPetName());
    }
}
