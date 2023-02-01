package com.ispwproject.adoptme.controller.graficcontroller.cli;

import com.ispwproject.adoptme.Main;
import com.ispwproject.adoptme.controller.appcontroller.ShowUserFavoritesController;
import com.ispwproject.adoptme.controller.graficcontroller.gui.GUIPetItemController;
import com.ispwproject.adoptme.engineering.bean.PetBean;
import com.ispwproject.adoptme.engineering.observer.Observer;
import com.ispwproject.adoptme.view.cli.CLIUserFavoritesView;


public class CLIUserFavoritesController implements Observer {
    private CLIUserFavoritesView cliUserFavoritesView;

    public void start() {
        this.cliUserFavoritesView = new CLIUserFavoritesView(this);
        this.cliUserFavoritesView.run();

        ShowUserFavoritesController showUserFavoritesController = new ShowUserFavoritesController();
        showUserFavoritesController.getPetList(this);
    }

    @Override
    public void update(Object object) {
        if(((PetBean) object).isFav()) {
            CLIPetInformationController cliPetInformationController = new CLIPetInformationController();
            cliPetInformationController.showPet((PetBean)object);
        } else {
            //todo rimuovere dalla lista (decrementare indici)
        }
    }

    @Override
    public void update2(Object object1, Object object2) {

    }
}
