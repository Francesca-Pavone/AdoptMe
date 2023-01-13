package com.ispwproject.adoptme.controller.appcontroller;

import com.ispwproject.adoptme.model.PetModel;
import com.ispwproject.adoptme.model.Shelter;
import com.ispwproject.adoptme.utils.bean.ShelterPageBean;
import com.ispwproject.adoptme.utils.dao.PetDAO;
import com.ispwproject.adoptme.utils.dao.ShelterDAOJDBC;

import java.util.List;

public class ShelterPageController_A {
    public void searchShelter(ShelterPageBean shelterPageBean, String shelterName) throws Exception {
        Shelter shelter = ShelterDAOJDBC.retrieveShelterByName(shelterName);
        shelterPageBean.setShelterName(shelter.getShelterName());
        shelterPageBean.setShelterCity(shelter.getCity());
        shelterPageBean.setShelterImg(shelter.getProfileImg());
        shelterPageBean.setShelterNumber(shelter.getPhoneNumber());
        shelterPageBean.setShelterAddress(shelter.getAddress());

        shelterPageBean.setSheltersPet(PetDAO.retrievePetByShelterName(shelterName));
    }
}
