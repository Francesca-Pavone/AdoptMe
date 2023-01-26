package com.ispwproject.adoptme.controller.appcontroller;

import com.ispwproject.adoptme.model.*;
import com.ispwproject.adoptme.utils.bean.PetBean;
import com.ispwproject.adoptme.utils.bean.RequestBean;
import com.ispwproject.adoptme.utils.dao.PetDAO;
import com.ispwproject.adoptme.utils.dao.RequestDAO;
import com.ispwproject.adoptme.utils.dao.ShelterDAO;
import com.ispwproject.adoptme.utils.dao.UserDAO;
import com.ispwproject.adoptme.utils.observer.Observer;
import com.ispwproject.adoptme.utils.observer.concreteSubjects.RequestList;
import com.ispwproject.adoptme.utils.session.Session;

import java.time.LocalTime;

public class SendRequestController {

    public void sendUserRequest(PetBean petBean, RequestBean requestBean, Observer observer) throws Exception {
        PetModel petModel;
        ShelterModel shelterModel = ShelterDAO.retrieveShelterById(petBean.getShelterId());
        RequestList requestList = new RequestList(observer, shelterModel);

        if (petBean.getType() == 0){
            //DOG
            petModel = new DogModel();
        }else {
            //CAT
            petModel = new CatModel();
        }
        petModel.setPetId(petBean.getPetId());
        petModel.setName(petBean.getName());
        petModel.setShelter(shelterModel);

        RequestModel requestModel = new RequestModel(
                null,
                0,
                petModel,
                new UserModel(Session.getCurrentSession().getUserBean()),
                requestBean.getDate(),
                LocalTime.of(Integer.parseInt(requestBean.getHour()), Integer.parseInt(requestBean.getMinutes())),
                0);

        RequestDAO.saveRequest(requestModel);
        requestList.addRequest(requestModel);
    }

    public void sendShelterRequest(RequestBean requestBean, Object object, Observer observer) throws Exception {
        PetModel petModel = PetDAO.retrievePetById(requestBean.getPetId(), requestBean.getShelterId());
        UserModel userModel = UserDAO.retrieveUserById(requestBean.getUserId());
        LocalTime time = LocalTime.of(Integer.parseInt(requestBean.getHour()), Integer.parseInt(requestBean.getMinutes()));

        RequestModel requestModel = new RequestModel(observer, requestBean.getId(), petModel, userModel, requestBean.getDate(), time, requestBean.getStatus());
        requestModel.updateStatus(1, object);
    }
}
