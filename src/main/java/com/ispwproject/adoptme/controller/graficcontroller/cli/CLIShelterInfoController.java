package com.ispwproject.adoptme.controller.graficcontroller.cli;

import com.ispwproject.adoptme.controller.appcontroller.ShowShelterPetsController;
import com.ispwproject.adoptme.engineering.exception.NoPetsFoundException;
import com.ispwproject.adoptme.engineering.utils.ScannerSupport;
import com.ispwproject.adoptme.engineering.utils.ShowExceptionSupport;
import com.ispwproject.adoptme.view.cli.CLIShelterInfoView;
import com.ispwproject.adoptme.engineering.bean.PetBean;
import com.ispwproject.adoptme.engineering.bean.ShelterBean;
import com.ispwproject.adoptme.engineering.observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class CLIShelterInfoController implements Observer {

    private CLIShelterInfoView cliShelterInfoView;
    private ShelterBean shelterBean;
    private List<PetBean> petBeanList;
    private CLIUserHomepageController previousPage;

    public void setShelter(String shelterName) {
        this.petBeanList = new ArrayList<>();
        try {
            ShowShelterPetsController showShelterPetsController = new ShowShelterPetsController();
        this.shelterBean = showShelterPetsController.getShelter(shelterName);
        this.start();
        } catch (Exception e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
            ScannerSupport.waitEnter();
            previousPage.start();
        }
    }
    
    public void start() throws NoPetsFoundException {
        this.cliShelterInfoView = new CLIShelterInfoView(this);
        this.cliShelterInfoView.run(this.shelterBean.getName(), this.shelterBean.getEmail(), this.shelterBean.getPhoneNumber(), this.shelterBean.getWebSite(), this.shelterBean.getAddress(), this.shelterBean.getCity());
    }

    public void getPet() throws NoPetsFoundException {
        ShowShelterPetsController showShelterPetsController = new ShowShelterPetsController(this.shelterBean);
        showShelterPetsController.getPetList(this);
        this.printPet();

    }

    public void goBack(){
        previousPage.start();
    }

    @Override
    public void update(Object object) {
        this.petBeanList.add((PetBean) object);
    }

    private void printPet() {
        int i = 1;
        for(PetBean petBean: this.petBeanList) {
            String gender;
            try {
                gender = (switch ((petBean).getGender()) {
                    case 1 -> "Female";
                    default -> "Male";
                });
                this.cliShelterInfoView.printPet((petBean).getName(), gender, (petBean).getAge(), i);
                i++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.cliShelterInfoView.printCommands();
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
            cliPetInformationController.setPetInfo();
        }
    }

    public void setPreviousPage(CLIUserHomepageController cliUserHomepageController) {
        this.previousPage = cliUserHomepageController;
    }
}
