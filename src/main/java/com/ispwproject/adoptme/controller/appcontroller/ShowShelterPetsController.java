package com.ispwproject.adoptme.controller.appcontroller;

import com.ispwproject.adoptme.engineering.bean.ShelterBean;
import com.ispwproject.adoptme.engineering.dao.ShelterDAO;
import com.ispwproject.adoptme.engineering.exception.NoPetsFoundException;
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
        this.shelterModel = new ShelterModel(shelterBean.getShelterId());
    }
    public ShowShelterPetsController(){}

    public ShelterBean getShelter(String shelterName) throws NotFoundException, NoSheltersWithThatNameException {
        int shelterId = ShelterDAO.retrieveIdByShelterName(shelterName);
        ShelterModel shelterModel = ShelterDAO.retrieveShelterById(shelterId);
        return new ShelterBean(shelterModel.getId(), shelterModel.getShelterName(), shelterModel.getPhoneNumber(), shelterModel.getAddress(), shelterModel.getCity(), shelterModel.getWebSite(), shelterModel.getEmail());
    }

    public void getPetList(Observer observer) throws NoPetsFoundException {
        if(Session.getCurrentSession().getShelterBean() != null)
            this.shelterModel = new ShelterModel(Session.getCurrentSession().getShelterBean().getShelterId());
        try {
            new ShelterPetsList(observer, PetDAO.retrievePetByShelterId(shelterModel), shelterModel);
        } catch (SQLException se) {
            // Errore durante l'apertura della connessione
            se.printStackTrace();
        }
    }
}
