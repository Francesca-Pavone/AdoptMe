package com.ispwproject.adoptme.controller.appcontroller;


import com.ispwproject.adoptme.utils.bean.SearchUserHomepageBean;
import com.ispwproject.adoptme.utils.dao.PetDAO;
import com.ispwproject.adoptme.utils.dao.ShelterDAOJDBC;

public class SearchUserHomepageApplicativeController {

    public void searchShelter(SearchUserHomepageBean searchUserHomepageBean) throws Exception {
        searchUserHomepageBean.setPetList(PetDAO.retrievePetByShelterName(searchUserHomepageBean.getCityShelter()));
    }

    public void searchCity(SearchUserHomepageBean searchUserHomepageBean) throws Exception {
        searchUserHomepageBean.setSheltersList(ShelterDAOJDBC.retrieveShelterByCity(searchUserHomepageBean.getCityShelter()));
    }

}
