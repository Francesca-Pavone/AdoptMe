package com.ispwproject.adoptme.controller.appcontroller;

import com.ispwproject.adoptme.model.PetModel;
import com.ispwproject.adoptme.utils.bean.GI.GIPreviewPetBean;
import com.ispwproject.adoptme.utils.bean.PetBean;
import com.ispwproject.adoptme.utils.bean.ShelterBean;
import com.ispwproject.adoptme.utils.dao.PetDAO;
import com.ispwproject.adoptme.utils.dao.ShelterDAOJDBC;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShelterPageController_A {

    public ShelterBean setData(String shelterName) throws Exception {
        ShelterBean shelterBean = new ShelterBean(ShelterDAOJDBC.retrieveShelterByName(shelterName));
        System.out.println(shelterBean.getName());
        return shelterBean;
    }

    public List<PetBean> getPetList(String shelterName) {
        PetDAO petDAO= new PetDAO();
        System.out.println(shelterName + "2");
        List<PetModel> petList = new ArrayList<>();
        try {
            petList = petDAO.retrievePetByShelterName(shelterName);


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
