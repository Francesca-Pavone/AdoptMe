package com.ispwproject.adoptme.controller.appcontroller;

import com.ispwproject.adoptme.model.PetModel;
import com.ispwproject.adoptme.model.RequestModel;
import com.ispwproject.adoptme.model.UserModel;
import com.ispwproject.adoptme.utils.bean.RequestBean;
import com.ispwproject.adoptme.utils.dao.PetDAO;
import com.ispwproject.adoptme.utils.dao.RequestDAO;
import com.ispwproject.adoptme.utils.dao.UserDAO;
import com.ispwproject.adoptme.utils.observer.Observer;
import com.ispwproject.adoptme.utils.session.Session;

import java.time.LocalTime;

public class ManageRequestController {

    public void deleteRequest(RequestBean request, Object object, Observer observer) throws Exception {
         PetModel petModel = PetDAO.retrievePetById(request.getPetId(), request.getShelterId());
            UserModel userModel = UserDAO.retrieveUserById(request.getUserId());
            LocalTime time = LocalTime.of(Integer.parseInt(request.getHour()), Integer.parseInt(request.getMinutes()));

            RequestModel requestModel = new RequestModel(observer, request.getId(), petModel, userModel, request.getDate(), time, request.getStatus());
            requestModel.updateStatus(3, object);

            if (Session.getSession().getUserBean() != null)
                RequestDAO.deleteRequest(requestModel.getId());
            else
                RequestDAO.updateRequestState(requestModel);
    }

    public void acceptRequest(RequestBean request, Object object, Observer observer, Observer itemObserver) throws Exception {
        PetModel petModel = PetDAO.retrievePetById(request.getPetId(), request.getShelterId());
        UserModel userModel = UserDAO.retrieveUserById(request.getUserId());
        LocalTime time = LocalTime.of(Integer.parseInt(request.getHour()), Integer.parseInt(request.getMinutes()));

        RequestModel requestModel = new RequestModel(observer, request.getId(), petModel, userModel, request.getDate(), time, request.getStatus());
        requestModel.register(itemObserver);
        requestModel.updateStatus(2, object);

        RequestDAO.updateRequestState(requestModel);
    }

    public void modifyRequest(RequestBean request, Object object, Observer observer, Observer itemObserver) throws Exception {
        PetModel petModel = PetDAO.retrievePetById(request.getPetId(), request.getShelterId());
        UserModel userModel = UserDAO.retrieveUserById(request.getUserId());
        LocalTime time = LocalTime.of(Integer.parseInt(request.getHour()), Integer.parseInt(request.getMinutes()));

        RequestModel requestModel = new RequestModel(observer, request.getId(), petModel, userModel, request.getDate(), time, request.getStatus());
        requestModel.register(itemObserver);

        if (Session.getSession().getShelterBean() != null)
            requestModel.updateStatus(1, object);
        else if (Session.getSession().getUserBean() != null)
            requestModel.updateStatus(0, object);

        RequestDAO.modifyRequest(requestModel);
    }
}
