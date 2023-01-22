package com.ispwproject.adoptme.controller.appcontroller;

import com.ispwproject.adoptme.controller.graficcontroller.gui.GUIShelterHomepageController;
import com.ispwproject.adoptme.model.PetModel;
import com.ispwproject.adoptme.model.ShelterModel;
import com.ispwproject.adoptme.utils.bean.PetBean;
import com.ispwproject.adoptme.utils.bean.ShelterBean;
import com.ispwproject.adoptme.utils.dao.PetDAO;
import com.ispwproject.adoptme.utils.observer.concreteSubjects.ShelterPetsList;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShowShelterPetsController {

    private final ShelterModel shelterModel;
    //private List<PetModel> petList = new ArrayList<>();
    private ShelterPetsList shelterPetsList;
    public ShowShelterPetsController(ShelterBean shelterBean) {
        this.shelterModel = new ShelterModel(shelterBean);
    }

    public List<PetBean> getPetList(GUIShelterHomepageController observer) {
        try {
            shelterPetsList = PetDAO.retrievePetByShelterId(this.shelterModel, observer);
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
