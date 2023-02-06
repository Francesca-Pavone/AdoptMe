package com.ispwproject.adoptme.controller.appcontroller;

import com.ispwproject.adoptme.engineering.bean.UserBean;
import com.ispwproject.adoptme.engineering.exception.DuplicateRequestException;
import com.ispwproject.adoptme.engineering.exception.NotFoundException;
import com.ispwproject.adoptme.engineering.exception.PastDateException;
import com.ispwproject.adoptme.engineering.utils.DateTimeSupport;
import com.ispwproject.adoptme.model.*;
import com.ispwproject.adoptme.engineering.bean.PetBean;
import com.ispwproject.adoptme.engineering.bean.RequestBean;
import com.ispwproject.adoptme.engineering.dao.RequestDAO;
import com.ispwproject.adoptme.engineering.dao.ShelterDAO;
import com.ispwproject.adoptme.engineering.observer.Observer;
import com.ispwproject.adoptme.engineering.session.Session;

import java.time.LocalDate;

public class SendRequestController {

    public void sendUserRequest(PetBean petBean, RequestBean requestBean, Observer observer) throws NotFoundException, PastDateException, DuplicateRequestException {
        PetModel petModel;
        ShelterModel shelterModel;
        try {
            shelterModel = ShelterDAO.retrieveShelterById(petBean.getShelterId());
        }catch (NotFoundException e) {
            throw new NotFoundException("Error during sending request process");
        }

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

        LocalDate date = DateTimeSupport.fromStringToLocalDate(requestBean.getDate());
        //non permetto di prendere appuntamenti nei giorni già passati
        if (date.isBefore(LocalDate.now())){
            throw new PastDateException(requestBean.getDate());
        }
        requestModel.setDate(date);

        requestModel.setTime(DateTimeSupport.fromStringToLocalTime(requestBean.getTime()));
        requestModel.setStatus(0);

        RequestDAO.saveRequest(requestModel, shelterModel);
        requestModel.notifyObservers(null);
    }
}
