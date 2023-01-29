package com.ispwproject.adoptme.controller.graficcontroller.cli;

import com.ispwproject.adoptme.engineering.dao.ShelterDAO;
import com.ispwproject.adoptme.view.cli.CLIShelterInfoView;
import com.ispwproject.adoptme.controller.appcontroller.ShelterPageController;
import com.ispwproject.adoptme.engineering.bean.PetBean;
import com.ispwproject.adoptme.engineering.bean.ShelterBean;
import com.ispwproject.adoptme.engineering.observer.Observer;
import com.ispwproject.adoptme.view.cli.CLIUserHomepageView;

import java.util.List;

public class CLIShelterInfoController implements Observer {

    public void setData(ShelterBean shelterBean) throws Exception {
        ShelterPageController shelterPageController = new ShelterPageController(shelterBean);
        List<PetBean> petBeanList = shelterPageController.getPetList(this);
        CLIShelterInfoView.showShelter(shelterBean, petBeanList);
    }

    public void setShelterData(String shelterName) throws Exception {
        ShelterBean shelterBean = new ShelterBean(ShelterDAO.retrieveShelterById(ShelterDAO.retrieveIdByShelterName(shelterName)));
        setData(shelterBean);
    }

    public void goBack() throws Exception {
        //todo: tornare indietro alla pagina giusta
        CLIUserHomepageView cliUserHomepageView = new CLIUserHomepageView();
        cliUserHomepageView.run();
    }
    @Override
    public void update(Object object) {
    }

    @Override
    public void update2(Object object1, Object object2) {

    }
}
