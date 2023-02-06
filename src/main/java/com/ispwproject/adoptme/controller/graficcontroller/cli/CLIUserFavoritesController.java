package com.ispwproject.adoptme.controller.graficcontroller.cli;

import com.ispwproject.adoptme.controller.appcontroller.ShowUserFavoritesController;
import com.ispwproject.adoptme.engineering.bean.PetBean;
import com.ispwproject.adoptme.engineering.exception.FavoriteListEmptyException;
import com.ispwproject.adoptme.engineering.observer.Observer;
import com.ispwproject.adoptme.engineering.utils.PrintSupport;
import com.ispwproject.adoptme.engineering.utils.ScannerSupport;
import com.ispwproject.adoptme.engineering.utils.ShowExceptionSupport;
import com.ispwproject.adoptme.view.cli.CLIUserFavoritesView;
import com.ispwproject.adoptme.view.cli.CLIUserHomepageView;

import java.util.List;


public class CLIUserFavoritesController implements CLIGraficController, Observer {
    private final CLIUserFavoritesView view;
    private List<PetBean> petBeanList;
    private CLIUserHomepageView previousPage;

    public CLIUserFavoritesController() {
        this.view = new CLIUserFavoritesView(this);
    }
    public void setPreviousPage(CLIUserHomepageView previousPage) {
        this.previousPage = previousPage;
    }
    @Override
    public void start() {

        ShowUserFavoritesController showUserFavoritesController = new ShowUserFavoritesController();
        try {
            this.petBeanList = showUserFavoritesController.getPetList(this);
            this.view.run();

        } catch (FavoriteListEmptyException e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
            ScannerSupport.waitEnter();
            this.previousPage.run();
        }

    }

    @Override
    public void update(Object object){
       // ignore
    }

    @Override
    public void update2(Object object1, Object object2){
        this.petBeanList.remove((int)object2 - 1);
        PrintSupport.printMessage("\n");
        this.view.run();
    }

    public void getPet(){
        int i = 1;
        String gender;
        for (PetBean petBean: petBeanList) {
            gender = (switch (petBean.getGender()) {
                case 1 -> "Female";
                default -> "Male";
            });
            this.view.printPet(petBean.getName(), gender, petBean.getAge(), i);
            i++;
        }
        this.view.printCommands();
    }

    public void executeCommand(int i) {
        if (i == 0) {
            CLIShelterInfoController cliShelterInfoController = new CLIShelterInfoController();
            cliShelterInfoController.goBack();
        }
        else {
            PetBean petBean = this.petBeanList.get(i-1);
            CLIPetInformationController cliPetInformationController = new CLIPetInformationController(petBean);
            cliPetInformationController.setIndex(i);
            cliPetInformationController.setFavObserver(this);
            cliPetInformationController.setPreviousPage(this);
            cliPetInformationController.start();
        }
    }


}
