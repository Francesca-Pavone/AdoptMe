package com.ispwproject.adoptme.controller.appcontroller;

import com.ispwproject.adoptme.engineering.bean.ShelterBean;
import com.ispwproject.adoptme.engineering.dao.ShelterDAO;
import com.ispwproject.adoptme.engineering.exception.NoSheltersWithThatNameException;
import com.ispwproject.adoptme.engineering.exception.NotFoundException;
import com.ispwproject.adoptme.engineering.observer.Observer;
import com.ispwproject.adoptme.model.ShelterModel;
import com.ispwproject.adoptme.engineering.dao.PetDAO;
import com.ispwproject.adoptme.engineering.observer.concretesubjects.ShelterPetsList;
import com.ispwproject.adoptme.engineering.session.Session;

import java.sql.SQLException;

public class ShowShelterPetsController {

    private ShelterModel shelterModel;

    public ShowShelterPetsController(ShelterBean shelterBean) {
        int a;
        this.shelterModel = new ShelterModel(shelterBean.getShelterBeanId());
    }
    public ShowShelterPetsController(){}

    public ShelterBean getShelter(String shelterName) throws NotFoundException, NoSheltersWithThatNameException {
        int shelterId = ShelterDAO.retrieveIdByShelterName(shelterName);
        ShelterModel shelterModelCurrent = ShelterDAO.retrieveShelterById(shelterId);
        ShelterBean shelterBean = new ShelterBean(shelterModelCurrent.getId(), shelterModelCurrent.getShelterName(), shelterModelCurrent.getPhoneNumber(), shelterModelCurrent.getAddress(), shelterModelCurrent.getCity(), shelterModelCurrent.getWebSite(), shelterModelCurrent.getEmail());
        shelterBean.setShelterBeanImg(shelterModelCurrent.getImage());
        return shelterBean;
    }

    public void getPetList(Observer observer) throws NotFoundException {
        if(Session.getCurrentSession().getShelterBean() != null)
            this.shelterModel = new ShelterModel(Session.getCurrentSession().getShelterBean().getShelterBeanId());
        try {
            new ShelterPetsList(observer, PetDAO.retrievePetByShelterId(shelterModel), shelterModel);
        } catch (SQLException se) {
            se.printStackTrace();
        }

    }
}
