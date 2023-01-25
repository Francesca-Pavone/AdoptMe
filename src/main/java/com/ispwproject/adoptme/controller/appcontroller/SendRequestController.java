package com.ispwproject.adoptme.controller.appcontroller;

import com.ispwproject.adoptme.controller.graficcontroller.gui.GUISendRequestController;
import com.ispwproject.adoptme.model.*;
import com.ispwproject.adoptme.utils.bean.PetBean;
import com.ispwproject.adoptme.utils.bean.RequestBean;
import com.ispwproject.adoptme.utils.dao.RequestDAO;
import com.ispwproject.adoptme.utils.dao.ShelterDAO;
import com.ispwproject.adoptme.utils.observer.concreteSubjects.RequestList;
import com.ispwproject.adoptme.utils.session.Session;

import java.time.LocalTime;

public class SendRequestController {

    public void createUserRequest(PetBean petBean, RequestBean requestBean, GUISendRequestController observer) throws Exception {
        PetModel petModel;
        ShelterModel shelterModel = ShelterDAO.retrieveShelterById(petBean.getShelterId());
        RequestList requestList = new RequestList(observer, shelterModel);

        RequestModel requestModel = new RequestModel();
        requestModel.setStatus(0);
        requestModel.setDate(requestBean.getDate());

        LocalTime time = LocalTime.of(Integer.parseInt(requestBean.getHour()), Integer.parseInt(requestBean.getMinutes()));
        requestModel.setTime(time);

        if (petBean.getType() == 0){ //DOG
            petModel = new DogModel();
        }else { //CAT
            petModel = new CatModel();
        }
        petModel.setPetId(petBean.getPetId());
        petModel.setName(petBean.getName());
        petModel.setShelter(shelterModel);
        requestModel.setPet(petModel);
        requestModel.setUser(new UserModel(Session.getSession().getUserBean()));

        RequestDAO.saveRequest(requestModel, observer);
        requestList.addRequest(requestModel);
    }
}
