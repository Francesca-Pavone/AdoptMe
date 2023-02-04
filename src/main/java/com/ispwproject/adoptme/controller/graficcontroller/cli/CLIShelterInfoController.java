package com.ispwproject.adoptme.controller.graficcontroller.cli;

import com.ispwproject.adoptme.engineering.utils.ScannerSupport;
import com.ispwproject.adoptme.engineering.utils.ShowExceptionSupport;
import com.ispwproject.adoptme.view.cli.CLIShelterInfoView;
import com.ispwproject.adoptme.controller.appcontroller.ShelterPageController;
import com.ispwproject.adoptme.engineering.bean.PetBean;
import com.ispwproject.adoptme.engineering.bean.ShelterBean;
import com.ispwproject.adoptme.engineering.observer.Observer;

import java.util.List;

public class CLIShelterInfoController implements Observer {

    private CLIShelterInfoView cliShelterInfoView;
    private ShelterBean shelterBean;
    private List<PetBean> petBeanList;
    private CLIUserHomepageController previousPage;

    public void setShelter(String shelterName) {
        try {
            ShelterPageController shelterPageController = new ShelterPageController();
        this.shelterBean = shelterPageController.getShelter(shelterName);
        this.start();
        } catch (Exception e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
            ScannerSupport.waitEnter();
            previousPage.start();
        }
    }
    
    public void start(){
        this.cliShelterInfoView = new CLIShelterInfoView(this);
        this.cliShelterInfoView.run(this.shelterBean.getName(), this.shelterBean.getEmail(), this.shelterBean.getPhoneNumber(), this.shelterBean.getWebSite(), this.shelterBean.getAddress(), this.shelterBean.getCity());
    }

    public void getPet() {
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



    public void goBack(){
        previousPage.start();
    }
    @Override
    public void update(Object object) {
        //ignore
    }

    @Override
    public void update2(Object object1, Object object2) {
        //ignore
    }

    public void executeCommand(int i) {
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

    public void setPreviousPage(CLIUserHomepageController cliUserHomepageController) {
        this.previousPage = cliUserHomepageController;
    }
}
