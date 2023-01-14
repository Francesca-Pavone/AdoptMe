package com.ispwproject.adoptme.controller.appcontroller;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.model.ShelterModel;
import com.ispwproject.adoptme.utils.bean.ShelterBean;
import com.ispwproject.adoptme.utils.dao.ShelterDAOJDBC;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.InputStream;

public class ShelterPageController_A {

    ShelterBean shelterBean = new ShelterBean();
    public void setData(String shelterName) throws Exception {
        ShelterModel shelterModel = ShelterDAOJDBC.retrieveShelterByName(shelterName);
        ShelterBean shelterBean = new ShelterBean();
        shelterBean.setShelterId(shelterModel.getId());
        shelterBean.setName(shelterModel.getShelterName());
        shelterBean.setAddress(shelterModel.getAddress());
        shelterBean.setEmail(shelterModel.getAccountInfo().getEmail());
        shelterBean.setCity(shelterModel.getCity());
        shelterBean.setShelterImg(shelterModel.getProfileImg());
    }

    public ShelterBean getData() {
        return shelterBean;
    }
}
