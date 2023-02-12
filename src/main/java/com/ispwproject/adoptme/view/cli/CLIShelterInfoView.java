package com.ispwproject.adoptme.view.cli;

import com.ispwproject.adoptme.controller.graficcontroller.cli.CLIShelterInfoController;
import com.ispwproject.adoptme.engineering.exception.NotFoundException;
import com.ispwproject.adoptme.engineering.utils.PrintSupport;

public class CLIShelterInfoView {
    
    private final CLIShelterInfoController cliShelterInfoControllerCurrent;

    public CLIShelterInfoView(CLIShelterInfoController cliShelterInfoController) {
        this.cliShelterInfoControllerCurrent = cliShelterInfoController;
    }

    public void run(String shelterName, String shelterEmail, String shelterPhoneNumber, String shelterWebSite, String shelterAddress, String shelterCity) throws NotFoundException {
        PrintSupport.printMessage("\n\n-------------------------------------- " + shelterName.toUpperCase() + " --------------------------------------");
        PrintSupport.printMessage("------------------------------------- Shelter Information -------------------------------------\n  Email: " + shelterEmail + "\n  Phone number: " + shelterPhoneNumber + "\n  Web site: " + shelterWebSite + "\n  Address: " + shelterAddress + ", " + shelterCity);
        this.cliShelterInfoControllerCurrent.getPet();
    }

}
