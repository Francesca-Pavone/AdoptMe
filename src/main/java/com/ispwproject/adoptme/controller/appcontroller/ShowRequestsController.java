package com.ispwproject.adoptme.controller.appcontroller;

import com.ispwproject.adoptme.engineering.bean.RequestBean;
import com.ispwproject.adoptme.engineering.bean.ShelterBean;
import com.ispwproject.adoptme.engineering.bean.UserBean;
import com.ispwproject.adoptme.engineering.exception.Fra.DateFormatException;
import com.ispwproject.adoptme.engineering.exception.Fra.NotFoundException;
import com.ispwproject.adoptme.engineering.exception.Fra.TimeFormatException;
import com.ispwproject.adoptme.model.RequestModel;
import com.ispwproject.adoptme.model.ShelterModel;
import com.ispwproject.adoptme.model.UserModel;
import com.ispwproject.adoptme.engineering.dao.RequestDAO;
import com.ispwproject.adoptme.engineering.observer.Observer;
import com.ispwproject.adoptme.engineering.session.Session;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ShowRequestsController {
    private ShelterModel shelterModel;
    private UserModel userModel;

    public ShowRequestsController() {
        if (Session.getCurrentSession().getShelterBean() != null){
            ShelterBean shelterBean = Session.getCurrentSession().getShelterBean();
            this.shelterModel = new ShelterModel(shelterBean.getShelterImg(), shelterBean.getName(), shelterBean.getEmail(), shelterBean.getPhoneNumber(), shelterBean.getAddress(), shelterBean.getCity(), shelterBean.getWebSite());
            this.shelterModel.setId(shelterBean.getShelterId());
        }
        else if (Session.getCurrentSession().getUserBean() != null) {
            UserBean userBean = Session.getCurrentSession().getUserBean();
            this.userModel = new UserModel(userBean.getUserId(), userBean.getProfileImg(), userBean.getName(), userBean.getSurname(), userBean.getEmail());
        }
    }

    public void getRequestList(Observer observer) throws NotFoundException, DateFormatException, TimeFormatException {

        List<RequestModel> requestModelList = new ArrayList<>();
        if (this.shelterModel != null) {
            requestModelList = RequestDAO.retrieveReqByShelter(this.shelterModel);
        }
        else if (this.userModel != null) {
            requestModelList = RequestDAO.retrieveReqByUser(this.userModel);
        }
        for (RequestModel request : requestModelList) {
            RequestBean requestBean = new RequestBean(request.getPet().getPetImage(), request.getUser().getImage(), request.getPet().getName(), request.getPet().getPetId(), request.getShelter().getId(), request.getUser().getName(), request.getUser().getId());
            requestBean.setId(request.getId());
            requestBean.setDate(request.getDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            requestBean.setTime(request.getTime().toString());
            requestBean.setStatus(request.getStatus());

            requestBean.register(observer);
            requestBean.notifyObservers(requestBean);
        }

    }




}
