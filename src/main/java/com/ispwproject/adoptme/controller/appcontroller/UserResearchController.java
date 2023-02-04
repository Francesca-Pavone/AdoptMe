package com.ispwproject.adoptme.controller.appcontroller;


import com.ispwproject.adoptme.engineering.exception.NoCityFoundException;
import com.ispwproject.adoptme.engineering.utils.ShowExceptionSupport;
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
        for (ShelterModel shelterModel : ShelterDAO.retrieveShelterByCity(userResearchBean.getCityShelter())) {
            ShelterBean shelterBean = new ShelterBean(shelterModel.getId(), shelterModel.getShelterName(), shelterModel.getPhoneNumber(), shelterModel.getAddress(), shelterModel.getCity(), shelterModel.getWebSite(), shelterModel.getAccountInfo().getEmail());
            shelterBean.setShelterImg(shelterModel.getProfileImg());
            listShelterBean.add(shelterBean);
        }
        return listShelterBean;
    }
}
