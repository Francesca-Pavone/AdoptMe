package com.ispwproject.adoptme.controller.appcontroller;

import com.ispwproject.adoptme.model.PetModel;
import com.ispwproject.adoptme.utils.bean.PetBean;
import com.ispwproject.adoptme.utils.dao.PetDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShelterPageController_A {



    public List<PetBean> getPetList(int shelterId) {
        List<PetModel> petList = new ArrayList<>();
        try {
            petList = PetDAO.retrievePetByShelterId(shelterId);


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

        for (PetModel petModel : petList) {
            PetBean petBean = new PetBean(petModel);
            petBeanList.add(petBean);
        }
        System.out.println(petBeanList.size());
        return petBeanList;
    }
}
