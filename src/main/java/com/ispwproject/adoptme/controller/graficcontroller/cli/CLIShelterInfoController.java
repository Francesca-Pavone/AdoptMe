package com.ispwproject.adoptme.controller.graficcontroller.cli;

import com.ispwproject.adoptme.view.cli.CLIShelterInfoView;
import com.ispwproject.adoptme.controller.appcontroller.ShelterPageController;
import com.ispwproject.adoptme.engineering.bean.PetBean;
import com.ispwproject.adoptme.engineering.bean.ShelterBean;
import com.ispwproject.adoptme.engineering.observer.Observer;

import java.util.List;

public class CLIShelterInfoController implements Observer {
    public void setData(ShelterBean shelterBean) {
        ShelterPageController shelterPageController = new ShelterPageController(shelterBean);
        List<PetBean> petBeanList = shelterPageController.getPetListCLI(this);
        CLIShelterInfoView.showShelter(shelterBean, petBeanList);
    }

    public void showPet(String petName) {

    }

    @Override
    public void update(Object object) {
    }

    @Override
    public void update2(Object object1, Object object2) {

    }
}
