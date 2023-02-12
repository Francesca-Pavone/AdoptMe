package com.ispwproject.adoptme.controller.appcontroller;

import com.ispwproject.adoptme.engineering.bean.RequestBean;
import com.ispwproject.adoptme.engineering.bean.ShelterBean;
import com.ispwproject.adoptme.engineering.bean.UserBean;
import com.ispwproject.adoptme.engineering.exception.DateFormatException;
import com.ispwproject.adoptme.engineering.exception.NotFoundException;
import com.ispwproject.adoptme.engineering.exception.TimeFormatException;
import com.ispwproject.adoptme.model.GenericUserModel;
import com.ispwproject.adoptme.model.RequestModel;
import com.ispwproject.adoptme.model.ShelterModel;
import com.ispwproject.adoptme.model.UserModel;
import com.ispwproject.adoptme.engineering.dao.RequestDAO;
import com.ispwproject.adoptme.engineering.observer.Observer;
import com.ispwproject.adoptme.engineering.session.Session;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class ShowRequestsController {
    private GenericUserModel genericUserModel;

    public ShowRequestsController() {
        if (Session.getCurrentSession().getShelterBean() != null){
            ShelterBean shelterBean = Session.getCurrentSession().getShelterBean();
            this.genericUserModel = new ShelterModel(shelterBean.getShelterBeanImg(), shelterBean.getShelterBeanName(), shelterBean.getBeanEmail(), shelterBean.getBeanPhoneNumber(), shelterBean.getBeanAddress(), shelterBean.getBeanCity(), shelterBean.getBeanWebSite());
            this.genericUserModel.setId(shelterBean.getShelterBeanId());
        }
        else if (Session.getCurrentSession().getUserBean() != null) {
            UserBean userBean = Session.getCurrentSession().getUserBean();
            this.genericUserModel = new UserModel(userBean.getUserId(), userBean.getProfileImg(), userBean.getName(), userBean.getSurname(), userBean.getEmail());
        }
    }

    public void getRequestList(Observer observer) throws NotFoundException, DateFormatException, TimeFormatException {

        List<RequestModel> requestModelList = RequestDAO.retrieveReqByOwnerId(this.genericUserModel.getId(), this.genericUserModel.getType());

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
