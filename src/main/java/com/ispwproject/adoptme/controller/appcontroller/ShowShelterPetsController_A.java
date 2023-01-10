package com.ispwproject.adoptme.controller.appcontroller;

import com.ispwproject.adoptme.model.PetModel;
import com.ispwproject.adoptme.utils.bean.PetBean;
import com.ispwproject.adoptme.utils.dao.PetDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShowShelterPetsController_A {

    private static List<PetModel> petList = new ArrayList<>();
    private static PetDAO petDAO = new PetDAO();


    public static List<PetModel> getPetList() {

        try {
            int searchKey = 1;
            petList = petDAO.retreivePetByShelterId(searchKey);


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

        List<PetBean> petBeanList = new ArrayList<PetBean>();
/*
        for (PetModel pet : petList) {
            PetBean petBean = new PetBean(pet.getImgSrc(), pet.getName(), pet.getType(), pet.getGender());
        }

 */
        return petList;
    }
}
