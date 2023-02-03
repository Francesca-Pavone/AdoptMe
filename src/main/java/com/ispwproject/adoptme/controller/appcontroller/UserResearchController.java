package com.ispwproject.adoptme.controller.appcontroller;


import com.ispwproject.adoptme.model.ShelterModel;
import com.ispwproject.adoptme.engineering.bean.UserResearchBean;
import com.ispwproject.adoptme.engineering.bean.ShelterBean;
import com.ispwproject.adoptme.engineering.dao.ShelterDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserResearchController {

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
