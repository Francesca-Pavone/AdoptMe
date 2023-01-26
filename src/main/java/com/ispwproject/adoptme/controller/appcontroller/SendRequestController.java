package com.ispwproject.adoptme.controller.appcontroller;

import com.ispwproject.adoptme.engineering.exception.PastDateException;
import com.ispwproject.adoptme.engineering.exception.Trigger;
import com.ispwproject.adoptme.model.*;
import com.ispwproject.adoptme.engineering.bean.PetBean;
import com.ispwproject.adoptme.engineering.bean.RequestBean;
import com.ispwproject.adoptme.engineering.dao.PetDAO;
import com.ispwproject.adoptme.engineering.dao.RequestDAO;
import com.ispwproject.adoptme.engineering.dao.ShelterDAO;
import com.ispwproject.adoptme.engineering.dao.UserDAO;
import com.ispwproject.adoptme.engineering.observer.Observer;
import com.ispwproject.adoptme.engineering.observer.concreteSubjects.RequestList;
import com.ispwproject.adoptme.engineering.session.Session;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class SendRequestController {

    public void sendUserRequest(PetBean petBean, RequestBean requestBean, Observer observer) throws Exception {
        PetModel petModel;
        ShelterModel shelterModel = ShelterDAO.retrieveShelterById(petBean.getShelterId());
        RequestList requestList = new RequestList(observer, shelterModel);
        RequestModel requestModel = new RequestModel();

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

        requestModel.setPet(petModel);
        requestModel.setUser(new UserModel(Session.getCurrentSession().getUserBean()));

        //non permetto di prendere appuntamenti nei giorni già passati
        if (requestBean.getDate().isBefore(LocalDate.now())){
            Trigger trigger = new Trigger();
            trigger.pastDate(requestBean.getDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        }
        requestModel.setDate(requestBean.getDate());

        requestModel.setTime(LocalTime.of(Integer.parseInt(requestBean.getHour()), Integer.parseInt(requestBean.getMinutes())));
        requestModel.setStatus(0);

        RequestDAO.saveRequest(requestModel);
        requestList.addRequest(requestModel);
    }
}
