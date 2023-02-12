package com.ispwproject.adoptme.controller.graficcontroller.cli;

import com.ispwproject.adoptme.controller.graficcontroller.cli.requests.CLIAppointmentsPageController;
import com.ispwproject.adoptme.engineering.exception.CommandNotFoundException;
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
        CLIGraficController controller = null;
        switch (input){
            case ADD_PET -> {
                controller = new CLIAddPetController();
            }
            case VIEW_PETS -> {
                controller = new CLIShowPetsController();
            }
            case APPOINTMENTS -> {
                controller = new CLIAppointmentsPageController();
            }
            case SETTINGS -> {
                controller = new CLIShelterSettingsController();
            }
            default -> throw new CommandNotFoundException("1 | 2 | 3 | 4");
        }
        controller.start();
    }

}
