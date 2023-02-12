package com.ispwproject.adoptme.controller.graficcontroller.cli;

import com.ispwproject.adoptme.controller.appcontroller.ShowShelterPetsController;
import com.ispwproject.adoptme.engineering.bean.PetBean;
import com.ispwproject.adoptme.engineering.exception.NotFoundException;
import com.ispwproject.adoptme.engineering.observer.Observer;
import com.ispwproject.adoptme.engineering.utils.ShowExceptionSupport;
import com.ispwproject.adoptme.view.cli.CLIShowPetsView;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class CLIShowPetsController implements CLIGraficController, Observer {

    private final CLIShowPetsView view;
    private final List<PetBean> petBeanList = new ArrayList<>();

    public CLIShowPetsController() {
        this.view = new CLIShowPetsView(this);
    }

    @Override
    public void start() {
        ShowShelterPetsController showShelterPetsController = new ShowShelterPetsController();
        try {
            showShelterPetsController.getPetList(this);
        }catch (NotFoundException e){
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
            String gender;
            String age;
            int yearDiff = Year.now().getValue() - petBean.getYearOfBirth();

            if (yearDiff <= 1)
                age = "Puppy";
            else if (yearDiff <= 3)
                age = "Young";
            else if (yearDiff <= 10)
                age = "Adult";
            else
                age = "Senior";

            try {
                gender = (switch ((petBean).getGender()) {
                    case 1 -> "Female";
                    default -> "Male";
                });
                this.view.showPetInfo((petBean).getName(), gender, age, i);
                i++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.view.showCommands();
    }



    public void executeCommand(int i) {
        if (i == 0) {
            goToHomepage();
        }
        else {
            PetBean petBean = this.petBeanList.get(i-1);
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
