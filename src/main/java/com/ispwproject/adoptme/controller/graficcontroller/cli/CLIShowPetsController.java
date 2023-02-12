package com.ispwproject.adoptme.controller.graficcontroller.cli;

import com.ispwproject.adoptme.controller.appcontroller.ShowShelterPetsController;
import com.ispwproject.adoptme.engineering.bean.PetBean;
import com.ispwproject.adoptme.engineering.exception.NotFoundException;
import com.ispwproject.adoptme.engineering.observer.Observer;
import com.ispwproject.adoptme.engineering.utils.ComputeAgeSupport;
import com.ispwproject.adoptme.engineering.utils.ComputeGenderSupport;
import com.ispwproject.adoptme.engineering.utils.ShowExceptionSupport;
import com.ispwproject.adoptme.view.cli.CLIShowPetsView;

import java.util.ArrayList;
import java.util.List;

public class CLIShowPetsController implements CLIGraficController, Observer {

    private final CLIShowPetsView view;
    private List<PetBean> petBeanList = new ArrayList<>();
    private Object previousPage = null;

    public void setPreviousPage(Object object) {
        this.previousPage = object;
    }

    public void setPetList(List<PetBean> petList) {
            this.petBeanList = petList;
    }

    public CLIShowPetsController() {
        this.view = new CLIShowPetsView(this);
    }

    @Override
    public void start() {
        ShowShelterPetsController showShelterPetsController = new ShowShelterPetsController();
        try {
            showShelterPetsController.getPetList(this);
        } catch (NotFoundException e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
        }
        showPets();
    }

    public void goToHomepage(){
        CLIShelterHomepageController cliShelterHomepageController= new CLIShelterHomepageController();
        cliShelterHomepageController.start();
    }

    public void showPets() {
        int i = 1;
        for(PetBean petBean: this.petBeanList) {
            String gender = ComputeGenderSupport.computeGender(petBean);
            String age = ComputeAgeSupport.computeAge(petBean);
            this.view.showPetInfo((petBean).getPetBeanName(), gender, age, i);
            i++;
        }
        this.view.showCommands();
    }



    public void executeCommand(int i) {
        if(previousPage instanceof CLIShelterInfoController)
            ((CLIShelterInfoController)previousPage).executeCommand(i);
        else if (i == 0)
            goToHomepage();
        else {
            PetBean petBean = this.petBeanList.get(i - 1);
            this.petBeanList.clear();
            CLIPetInformationController cliPetInformationController = new CLIPetInformationController(petBean);
            cliPetInformationController.setIndex(i);
            cliPetInformationController.setFavObserver(this);
            cliPetInformationController.setPreviousPage(this);
            cliPetInformationController.start();
            start();
        }
    }

    @Override
    public void update(Object object) {
        this.petBeanList.add((PetBean) object);
    }
    @Override
    public void update2(Object object1, Object object2) {
        //ignore
    }
}
