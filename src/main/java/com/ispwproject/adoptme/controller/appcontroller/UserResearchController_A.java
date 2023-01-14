package com.ispwproject.adoptme.controller.appcontroller;


import com.ispwproject.adoptme.model.ShelterModel;
import com.ispwproject.adoptme.utils.bean.UserResearchBean;
import com.ispwproject.adoptme.utils.bean.ShelterBean;
import com.ispwproject.adoptme.utils.dao.ShelterDAOJDBC;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserResearchController_A {

    public void searchShelter(UserResearchBean userResearchBean) throws Exception {
        //searchUserHomepageBean.setPetList(PetDAO.retrievePetByShelterName(searchUserHomepageBean.getCityShelter()));
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
