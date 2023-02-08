package com.ispwproject.adoptme.controller.graficcontroller.cli;

import com.ispwproject.adoptme.controller.graficcontroller.cli.requests.CLIAppointmentsPageController;
import com.ispwproject.adoptme.engineering.exception.Fra.CommandNotFoundException;
import com.ispwproject.adoptme.engineering.utils.PrintSupport;
import com.ispwproject.adoptme.view.cli.CLIShelterHomepageView;

public class CLIShelterHomepageController implements CLIGraficController{
    private static final String ADD_PET = "1";
    private static final String VIEW_PETS = "2";
    private static final String APPOINTMENTS = "3";
    private static final String SETTINGS = "4";
    private final CLIShelterHomepageView view;

    public CLIShelterHomepageController() {
        this.view = new CLIShelterHomepageView(this);
    }

    @Override
    public void start() {
        this.view.run();
    }

    public void executeCommand(String input) throws CommandNotFoundException {
        switch (input){
            case ADD_PET, VIEW_PETS, SETTINGS -> {
                PrintSupport.printMessage("Functionality realized just with the first interface\n\n");
                view.run();
            }
            case APPOINTMENTS -> {
                CLIAppointmentsPageController cliAppointmentsPageController = new CLIAppointmentsPageController();
                cliAppointmentsPageController.start();
            }
            default -> throw new CommandNotFoundException();
        }
    }

}
