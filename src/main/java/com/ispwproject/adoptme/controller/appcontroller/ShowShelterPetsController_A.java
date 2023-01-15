package com.ispwproject.adoptme.controller.appcontroller;

import com.ispwproject.adoptme.model.PetModel;
import com.ispwproject.adoptme.utils.bean.PetBean;
import com.ispwproject.adoptme.utils.dao.PetDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShowShelterPetsController_A {

    int shelterId;
    private static List<PetModel> petList = new ArrayList<>();
    private static PetDAO petDAO = new PetDAO();

    public ShowShelterPetsController_A(int shelterId) {
        this.shelterId = shelterId;
    }

    public List<PetBean> getPetList() {

        try {
            int searchKey = 1;
            petList = petDAO.retrivePetByShelterId(searchKey);


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

        return petBeanList;
    }
}
