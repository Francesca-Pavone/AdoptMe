package com.ispwproject.adoptme.controller.appcontroller;

import com.ispwproject.adoptme.controller.graficcontroller.cli.CLIShelterInfoController;
import com.ispwproject.adoptme.controller.graficcontroller.gui.GUIShelterInformationController;
import com.ispwproject.adoptme.engineering.observer.Observer;
import com.ispwproject.adoptme.model.PetModel;
import com.ispwproject.adoptme.model.ShelterModel;
import com.ispwproject.adoptme.engineering.bean.PetBean;
import com.ispwproject.adoptme.engineering.bean.ShelterBean;
import com.ispwproject.adoptme.engineering.dao.PetDAO;
import com.ispwproject.adoptme.engineering.observer.concreteSubjects.ShelterPetsList;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShelterPageController {
    ShelterModel shelterModel;
    ShelterPetsList shelterPetsList;

    public ShelterPageController(ShelterBean shelterBean) {
        this.shelterModel = new ShelterModel(shelterBean);
    }

    public List<PetBean> getPetList(Observer observer) {
        //List<PetModel> petList = new ArrayList<>();
        try {
            shelterPetsList = PetDAO.retrievePetByShelterId(shelterModel,observer);

        } catch (SQLException se) {
            // Errore durante l'apertura della connessione
            se.printStackTrace();
        } catch (ClassNotFoundException driverEx) {
            // Errore nel loading del driver
            driverEx.printStackTrace();
        } catch (Exception e) {
            // Errore nel loading del driver o possibilmente nell'accesso al filesystem
            e.printStackTrace();
        }

        List<PetBean> petBeanList = new ArrayList<>();

        for (PetModel petModel : shelterPetsList.getPetList()) {
            PetBean petBean = new PetBean(petModel);
            petBeanList.add(petBean);
        }
        return petBeanList;
    }

}
