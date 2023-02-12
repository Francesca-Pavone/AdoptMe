package com.ispwproject.adoptme.controller.graficcontroller.cli;

import com.ispwproject.adoptme.controller.appcontroller.ShowShelterPetsController;
import com.ispwproject.adoptme.engineering.exception.NoSheltersWithThatNameException;
import com.ispwproject.adoptme.engineering.exception.NotFoundException;
import com.ispwproject.adoptme.engineering.utils.ScannerSupport;
import com.ispwproject.adoptme.engineering.utils.ShowExceptionSupport;
import com.ispwproject.adoptme.view.cli.CLIShelterInfoView;
import com.ispwproject.adoptme.engineering.bean.PetBean;
import com.ispwproject.adoptme.engineering.bean.ShelterBean;
import com.ispwproject.adoptme.engineering.observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class CLIShelterInfoController implements CLIGraficController, Observer {

    private final CLIShelterInfoView view;
    private ShelterBean shelterBean;
    private List<PetBean> petBeanList;
    private CLIUserHomepageController previousPage;

    public CLIShelterInfoController() {
        this.view = new CLIShelterInfoView(this);
    }

    public void setShelter(String shelterName) {
        this.petBeanList = new ArrayList<>();
        ShowShelterPetsController showShelterPetsController = new ShowShelterPetsController();
        try {
            this.shelterBean = showShelterPetsController.getShelter(shelterName);
        } catch (NotFoundException | NoSheltersWithThatNameException e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
            ScannerSupport.waitEnter();
            previousPage.start();
        }
        this.start();

    }
    @Override
    public void start() {

        try {
            this.view.run(this.shelterBean.getShelterBeanName(), this.shelterBean.getBeanEmail(), this.shelterBean.getBeanPhoneNumber(), this.shelterBean.getBeanWebSite(), this.shelterBean.getBeanAddress(), this.shelterBean.getBeanCity());
        }
        catch (NotFoundException e){
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
            ScannerSupport.waitEnter();
            previousPage.start();
        }
    }

    public void getPet() throws NotFoundException {
        ShowShelterPetsController showShelterPetsController = new ShowShelterPetsController(this.shelterBean);
        showShelterPetsController.getPetList(this);
        CLIShowPetsController cliShowPetsController = new CLIShowPetsController();
        cliShowPetsController.setPetList(this.petBeanList);
        cliShowPetsController.setPreviousPage(this);
        cliShowPetsController.showPets();
    }

    public void goBack(){
        previousPage.start();
    }

    @Override
    public void update(Object object) {
        this.petBeanList.add((PetBean) object);
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
            this.petBeanList.clear();
            CLIPetInformationController cliPetInformationController = new CLIPetInformationController(petBean);
            cliPetInformationController.setIndex(i);
            cliPetInformationController.setFavObserver(this);
            cliPetInformationController.setPreviousPage(this);
            cliPetInformationController.start();
        }
    }

    public void setPreviousPage(CLIUserHomepageController cliUserHomepageController) {
        this.previousPage = cliUserHomepageController;
    }
}
