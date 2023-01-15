package com.ispwproject.adoptme.controller.appcontroller;


import com.ispwproject.adoptme.model.PetModel;
import com.ispwproject.adoptme.model.ShelterModel;
import com.ispwproject.adoptme.utils.bean.PreviewPetBean;
import com.ispwproject.adoptme.utils.bean.UserResearchBean;
import com.ispwproject.adoptme.utils.bean.ShelterBean;
import com.ispwproject.adoptme.utils.dao.PetDAO;
import com.ispwproject.adoptme.utils.dao.ShelterDAOJDBC;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserResearchController_A {

    public List<PreviewPetBean> searchShelter(String shelterName) throws Exception {
        List<PreviewPetBean> petList = new ArrayList<>();
        try {
            for(PetModel petModel : PetDAO.retrievePetByShelterName(shelterName)) {
                PreviewPetBean previewPetBean = new PreviewPetBean(petModel);
                petList.add(previewPetBean);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return petList;
    }

    public List<ShelterBean> searchCity(UserResearchBean userResearchBean) throws Exception {
        List<ShelterBean> listShelterBean = new ArrayList<>();
        try {
            for (ShelterModel shelterModel : ShelterDAOJDBC.retrieveShelterByCity(userResearchBean.getCityShelter())) {
                ShelterBean shelterBean = new ShelterBean(shelterModel.getShelterName());
                listShelterBean.add(shelterBean);
            }
        } catch (IOException e) { // todo: non penso sia IO exception
            e.printStackTrace();
        }
        return listShelterBean;
    }
    }
