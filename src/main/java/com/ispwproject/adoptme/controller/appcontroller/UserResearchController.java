package com.ispwproject.adoptme.controller.appcontroller;


import com.ispwproject.adoptme.model.ShelterModel;
import com.ispwproject.adoptme.engineering.bean.PetBean;
import com.ispwproject.adoptme.engineering.bean.UserResearchBean;
import com.ispwproject.adoptme.engineering.bean.ShelterBean;
import com.ispwproject.adoptme.engineering.dao.ShelterDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserResearchController {

    public List<PetBean> searchShelter(String shelterName) throws Exception {

        List<PetBean> petList = new ArrayList<>();
        /*
        ShelterModel shelter = ShelterDAO.retrieveShelterByName(shelterName);
        try {
            for(PetModel petModel : PetDAO.retrievePetByShelterId(shelter)) {
                PetBean petBean = new PetBean(petModel);
                petList.add(petBean);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

         */
        return petList;
    }

    public List<ShelterBean> searchCity(UserResearchBean userResearchBean) throws Exception {
        List<ShelterBean> listShelterBean = new ArrayList<>();
        try {
            for (ShelterModel shelterModel : ShelterDAO.retrieveShelterByCity(userResearchBean.getCityShelter())) {
                ShelterBean shelterBean = new ShelterBean(shelterModel);
                listShelterBean.add(shelterBean);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listShelterBean;
    }
    }
