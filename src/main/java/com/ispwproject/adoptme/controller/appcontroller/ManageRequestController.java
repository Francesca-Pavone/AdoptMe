package com.ispwproject.adoptme.controller.appcontroller;

import com.ispwproject.adoptme.engineering.bean.PetBean;
import com.ispwproject.adoptme.engineering.bean.UserBean;
import com.ispwproject.adoptme.engineering.dao.*;
import com.ispwproject.adoptme.engineering.exception.DuplicateRequestException;
import com.ispwproject.adoptme.engineering.exception.NotFoundException;
import com.ispwproject.adoptme.engineering.exception.PastDateException;
import com.ispwproject.adoptme.engineering.utils.DateTimeSupport;
import com.ispwproject.adoptme.model.*;
import com.ispwproject.adoptme.engineering.bean.RequestBean;
import com.ispwproject.adoptme.engineering.session.Session;

import java.time.LocalDate;
import java.time.LocalTime;

public class ManageRequestController {

    public void sendRequest(PetBean petBean, RequestBean requestBean) throws NotFoundException, PastDateException, DuplicateRequestException {
        PetModel petModel;
        ShelterModel shelterModel;
        try {
            shelterModel = ShelterDAO.retrieveShelterById(petBean.getPetBeanShelter());
        }catch (NotFoundException e) {
            throw new NotFoundException("during sending request process");
        }

        UserBean userBean = Session.getCurrentSession().getUserBean();
        UserModel userModel = new UserModel(userBean.getUserId(), userBean.getProfileImg(), userBean.getName(), userBean.getSurname());

        RequestModel requestModel = new RequestModel();

        if (petBean.getPetBeanType() == 0){
            //DOG
            petModel = new DogModel();
        }else {
            //CAT
            petModel = new CatModel();
        }
        petModel.setPetId(petBean.getPetBeanId());
        petModel.setName(petBean.getPetBeanName());

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

        RequestDAO.saveRequest(requestModel);

        requestBean.notifyObservers(null);
    }

    public void deleteRequest(RequestBean request, Object object) throws NotFoundException {
        UserDAO userDAO = getUserDAO();
        UserModel userModel = userDAO.retrieveUserById(request.getUserId());

        PetModel petModel = PetDAO.retrievePetById(request.getPetId(), request.getShelterId());
        ShelterModel shelterModel = ShelterDAO.retrieveShelterById(request.getShelterId());

        request.setStatus(3);
        request.notifyObservers(request, object);

        RequestModel requestModel = new RequestModel(request.getId(), petModel, shelterModel, userModel, DateTimeSupport.fromStringToLocalDate(request.getDate()), DateTimeSupport.fromStringToLocalTime(request.getTime()), request.getStatus());

        if (Session.getCurrentSession().getUserBean() != null)
            RequestDAO.deleteRequest(requestModel.getId());

        else
            RequestDAO.updateRequestStatus(requestModel);
    }

    public void acceptRequest(RequestBean request, Object object) throws NotFoundException {
        UserDAO userDAO = getUserDAO();
        UserModel userModel = userDAO.retrieveUserById(request.getUserId());

        PetModel petModel = PetDAO.retrievePetById(request.getPetId(), request.getShelterId());
        ShelterModel shelterModel = ShelterDAO.retrieveShelterById(request.getShelterId());

        request.setStatus(2);
        request.notifyObservers(request, object);

        RequestModel requestModel = new RequestModel(request.getId(), petModel, shelterModel, userModel, DateTimeSupport.fromStringToLocalDate(request.getDate()), DateTimeSupport.fromStringToLocalTime(request.getTime()), request.getStatus());

        RequestDAO.updateRequestStatus(requestModel);
    }

    public void updateRequest(RequestBean request, Object object) throws NotFoundException, PastDateException {

        LocalDate date = DateTimeSupport.fromStringToLocalDate(request.getDate());
        if (date.isBefore(LocalDate.now())){
            throw new PastDateException(request.getDate());
        }
        UserDAO userDAO = getUserDAO();
        UserModel userModel = userDAO.retrieveUserById(request.getUserId());

        PetModel petModel = PetDAO.retrievePetById(request.getPetId(), request.getShelterId());
        ShelterModel shelterModel = ShelterDAO.retrieveShelterById(request.getShelterId());

        if (Session.getCurrentSession().getShelterBean() != null) {
            request.setStatus(1);
        }
        else if (Session.getCurrentSession().getUserBean() != null){
            request.setStatus(0);
        }
        request.notifyObservers(request, object);

        RequestModel requestModel = new RequestModel(request.getId(), petModel, shelterModel, userModel, DateTimeSupport.fromStringToLocalDate(request.getDate()), DateTimeSupport.fromStringToLocalTime(request.getTime()), request.getStatus());

        RequestDAO.modifyRequest(requestModel);
    }

    private static UserDAO getUserDAO() {
        UserDAO userDAO;
        if (LocalTime.now().getMinute()%2 == 0) {
            userDAO = new UserDAOJDBC();
        } else {
            userDAO = new UserDAOCSV();
        }
        return userDAO;
    }
}
