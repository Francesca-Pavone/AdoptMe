package com.ispwproject.adoptme.controller.graficcontroller.cli;

import com.ispwproject.adoptme.utils.dao.ShelterDAO;
import com.ispwproject.adoptme.view.CLIView.CLIShelterInfoView;
import com.ispwproject.adoptme.controller.appcontroller.ShelterPageController;
import com.ispwproject.adoptme.utils.bean.PetBean;
import com.ispwproject.adoptme.utils.bean.ShelterBean;
import com.ispwproject.adoptme.utils.observer.Observer;

import java.util.List;

public class CLIShelterInfoController implements Observer {
    public void setData(ShelterBean shelterBean) {
        ShelterPageController shelterPageController = new ShelterPageController(shelterBean);
        List<PetBean> petBeanList = shelterPageController.getPetListCLI(this);
        CLIShelterInfoView.showShelter(shelterBean, petBeanList);
    }

    public void setShelterData(String shelterName) throws Exception {
        ShelterBean shelterBean = new ShelterBean(ShelterDAO.retrieveShelterById(ShelterDAO.retrieveIdByShelterName(shelterName)));
        setData(shelterBean);
    }

    @Override
    public void update(Object object) {
    }

}