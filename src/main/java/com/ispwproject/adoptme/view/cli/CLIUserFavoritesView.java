package com.ispwproject.adoptme.view.cli;

import com.ispwproject.adoptme.controller.graficcontroller.cli.CLIPetInformationController;
import com.ispwproject.adoptme.controller.graficcontroller.cli.CLIUserFavoritesController;
import com.ispwproject.adoptme.engineering.bean.PetBean;

import java.util.List;
import java.util.Scanner;

public class CLIUserFavoritesView {
    private CLIUserFavoritesController cliUserFavoritesControllerCurrent;

    public CLIUserFavoritesView(CLIUserFavoritesController cliUserFavoritesController) {
        this.cliUserFavoritesControllerCurrent = cliUserFavoritesController;
    }

    public void run() {
       System.out.println("------------------------------------------ FAVORITES ------------------------------------------");
    }
}
