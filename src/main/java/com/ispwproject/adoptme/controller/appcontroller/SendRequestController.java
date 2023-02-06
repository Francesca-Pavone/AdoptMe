package com.ispwproject.adoptme.controller.appcontroller;

import com.ispwproject.adoptme.engineering.bean.UserBean;
import com.ispwproject.adoptme.engineering.exception.PastDateException;
import com.ispwproject.adoptme.model.*;
import com.ispwproject.adoptme.engineering.bean.PetBean;
import com.ispwproject.adoptme.engineering.bean.RequestBean;
import com.ispwproject.adoptme.engineering.dao.RequestDAO;
import com.ispwproject.adoptme.engineering.dao.ShelterDAO;
import com.ispwproject.adoptme.engineering.observer.Observer;
import com.ispwproject.adoptme.engineering.session.Session;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class SendRequestController {

    public void sendUserRequest(PetBean petBean, RequestBean requestBean, Observer observer) throws Exception {
        PetModel petModel;
        ShelterModel shelterModel = ShelterDAO.retrieveShelterById(petBean.getShelterId());

        UserBean userBean = Session.getCurrentSession().getUserBean();
        UserModel userModel = new UserModel(userBean.getUserId(), userBean.getProfileImg(), userBean.getName(), userBean.getSurname());

        RequestModel requestModel = new RequestModel();
        requestModel.register(observer);

        if (petBean.getType() == 0){
            //DOG
            petModel = new DogModel();
        }else {
            //CAT
            petModel = new CatModel();
        }
        petModel.setPetId(petBean.getPetId());
        petModel.setName(petBean.getName());

        requestModel.setPet(petModel);
        requestModel.setShelter(shelterModel);
        requestModel.setUser(userModel);

        //non permetto di prendere appuntamenti nei giorni già passati
        if (requestBean.getDate().isBefore(LocalDate.now())){
            throw new PastDateException(requestBean.getDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        }
        requestModel.setDate(requestBean.getDate());

        requestModel.setTime(LocalTime.of(Integer.parseInt(requestBean.getHour()), Integer.parseInt(requestBean.getMinutes())));
        requestModel.setStatus(0);

        RequestDAO.saveRequest(requestModel, shelterModel);
        requestModel.notifyObservers(null);
    }
}
