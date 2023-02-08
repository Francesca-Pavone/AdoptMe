package com.ispwproject.adoptme.controller.graficcontroller.cli;

import com.ispwproject.adoptme.controller.appcontroller.ShowUserFavoritesController;
import com.ispwproject.adoptme.engineering.bean.PetBean;
import com.ispwproject.adoptme.engineering.exception.federica.FavoriteListEmptyException;
import com.ispwproject.adoptme.engineering.observer.Observer;
import com.ispwproject.adoptme.engineering.utils.PrintSupport;
import com.ispwproject.adoptme.view.cli.CLIUserFavoritesView;

import java.util.List;


public class CLIUserFavoritesController implements Observer {
    private CLIUserFavoritesView cliUserFavoritesView;
    private List<PetBean> petBeanList;
    private CLIUserHomepageController previousPage;

    public void start() throws FavoriteListEmptyException {
        this.cliUserFavoritesView = new CLIUserFavoritesView(this);

        ShowUserFavoritesController showUserFavoritesController = new ShowUserFavoritesController();
        this.petBeanList = showUserFavoritesController.getPetList(this);
        this.cliUserFavoritesView.run();

    }

    @Override
    public void update(Object object){
       // ignore
    }

    @Override
    public void update2(Object object1, Object object2){
        this.petBeanList.remove((int)object2 - 1);
        PrintSupport.printMessage("\n");
        this.cliUserFavoritesView.run();
    }

    public void getPet(){
        int i = 1;
        String gender;
        for (PetBean petBean: petBeanList) {
            gender = (switch (petBean.getGender()) {
                case 1 -> "Female";
                default -> "Male";
            });
            this.cliUserFavoritesView.printPet(petBean.getName(), gender, petBean.getAge(), i);
            i++;
        }
        this.cliUserFavoritesView.printCommands();
    }

    public void executeCommand(int i) {
        if (i == 0) {
            this.previousPage.start();
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

    public void setPreviousPage(CLIUserHomepageController cliUserHomepageController) {
        this.previousPage = cliUserHomepageController;
    }
}
