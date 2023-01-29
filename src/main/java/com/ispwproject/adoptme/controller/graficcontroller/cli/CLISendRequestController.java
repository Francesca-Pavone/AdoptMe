package com.ispwproject.adoptme.controller.graficcontroller.cli;

import com.ispwproject.adoptme.engineering.bean.PetBean;
import com.ispwproject.adoptme.engineering.bean.RequestBean;
import com.ispwproject.adoptme.engineering.bean.ShelterBean;
import com.ispwproject.adoptme.engineering.bean.UserBean;
import com.ispwproject.adoptme.engineering.session.Session;
import com.ispwproject.adoptme.view.cli.CLISendRequestView;

public class CLISendRequestController {

    public void sendRequest(PetBean petBean) {

        UserBean user = Session.getCurrentSession().getUserBean();
        RequestBean requestBean = new RequestBean(petBean.getName(), petBean.getPetId(), petBean.getShelterId(), user.getName(), user.getUserId());

        CLISendRequestView cliSendRequestView = new CLISendRequestView();
        cliSendRequestView.showSendRequestForm(requestBean);


    }
}
