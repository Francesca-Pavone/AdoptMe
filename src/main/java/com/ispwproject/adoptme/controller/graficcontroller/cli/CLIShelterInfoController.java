package com.ispwproject.adoptme.controller.graficcontroller.cli;

import com.ispwproject.adoptme.view.cli.CLIShelterInfoView;
import com.ispwproject.adoptme.controller.appcontroller.ShelterPageController;
import com.ispwproject.adoptme.engineering.bean.PetBean;
import com.ispwproject.adoptme.engineering.bean.ShelterBean;
import com.ispwproject.adoptme.engineering.observer.Observer;
import com.ispwproject.adoptme.view.cli.CLIUserHomepageView;

import java.util.List;

public class CLIShelterInfoController implements Observer {

    private CLIShelterInfoView cliShelterInfoView;
    private ShelterBean shelterBean;
    private List<PetBean> petBeanList;

    public void setShelter(String shelterName) throws Exception {
        ShelterPageController shelterPageController = new ShelterPageController();
        this.shelterBean = shelterPageController.getShelter(shelterName);
        this.start();
    }
    
    public void start() throws Exception {
        this.cliShelterInfoView = new CLIShelterInfoView(this);
        this.cliShelterInfoView.run(this.shelterBean.getName(), this.shelterBean.getEmail(), this.shelterBean.getPhoneNumber(), this.shelterBean.getWebSite(), this.shelterBean.getAddress(), this.shelterBean.getCity());
    }

    public void getPet() throws Exception {
        ShelterPageController shelterPageController = new ShelterPageController(this.shelterBean);
        this.petBeanList = shelterPageController.getPetList(this);
        int i = 1;
        String gender;
        for(PetBean petBean: this.petBeanList) {
            gender = (switch (petBean.getGender()) {
                case 1 -> "Female";
                default -> "Male";
            });
            this.cliShelterInfoView.printPet(petBean.getName(), gender, petBean.getAge(), i);
            i++;
        }
        this.cliShelterInfoView.printCommands();
    }



    public void goBack() throws Exception {
        CLIUserHomepageView cliUserHomepageView = new CLIUserHomepageView();
        cliUserHomepageView.run();
    }
    @Override
    public void update(Object object) {
    }

    @Override
    public void update2(Object object1, Object object2) {

    }

    public void executeCommand(int i) throws Exception {
        if (i == 0) {
            this.goBack();
        }
        else {
            PetBean petBean = this.petBeanList.get(i-1);
            CLIPetInformationController cliPetInformationController = new CLIPetInformationController(petBean);
            cliPetInformationController.setIndex(i);
            cliPetInformationController.setFavObserver(this);
            cliPetInformationController.setPreviousPage(this);
            cliPetInformationController.setPetInfo();
        }
    }
}
