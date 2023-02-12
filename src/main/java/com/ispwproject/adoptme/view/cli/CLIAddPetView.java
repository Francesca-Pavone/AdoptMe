package com.ispwproject.adoptme.view.cli;

import com.ispwproject.adoptme.controller.graficcontroller.cli.CLIAddPetController;
import com.ispwproject.adoptme.controller.graficcontroller.cli.CLIShelterHomepageController;
import com.ispwproject.adoptme.engineering.utils.PrintSupport;
import com.ispwproject.adoptme.engineering.utils.ScannerSupport;

import java.util.Scanner;

public class CLIAddPetView {
    private final CLIAddPetController controller;

    public CLIAddPetView(CLIAddPetController controller) {
        this.controller = controller;
    }

    public void showForm() {
        Scanner scanner = new Scanner(System.in);
        PrintSupport.printMessage("---------------------------------------- ADD NEW PET ----------------------------------------");
        PrintSupport.printMessage("Insert the requested information:");
        PrintSupport.printMessage("\nName:");
        String name = scanner.nextLine();
        PrintSupport.printMessage("\nDate of birth: [dd-MM-yyyy]\t(if you don't know the exact date press ENTER)");
        String date = scanner.nextLine();
        String year = "";
        String month = "";
        if (date.equals("")) {
            PrintSupport.printMessage("\nYear of birth:");
            year = scanner.nextLine();
            PrintSupport.printMessage("\nMonth of birth (if you don't know the exact month press ENTER)");
            month = scanner.nextLine();
        }
        PrintSupport.printMessage("\nType: (0 -> Dog  |  1 -> Cat)");
        int type = scanner.nextInt();
        PrintSupport.printMessage("\nGender: (0 -> Male  |  1 -> Female)");
        int gender = scanner.nextInt();
        PrintSupport.printMessage("\nCoat length: (0 -> Short  |  1 -> Medium  |  2 -> Long");
        this.controller.setMainInfo(name, date, year, month, type, gender);

        int coatLength = scanner.nextInt();
        scanner.nextLine();
        int size = -1;
        if (type == 0) {
            PrintSupport.printMessage("\nSize: (0 -> Small  |  1 -> Medium  |  2 -> Large  |  3 -> Extra Large)");
            size = scanner.nextInt();
            scanner.nextLine();
        }
        PrintSupport.printMessage("\nVaccination complete: (0 -> No  |  1 -> Yes)");
        String vaccinated = scanner.nextLine();
        PrintSupport.printMessage("\nMicrochipped: (0 -> No  |  1 -> Yes)");
        String microchipped = scanner.nextLine();
        PrintSupport.printMessage("\nDewormed: (0 -> No  |  1 -> Yes)");
        String dewormed = scanner.nextLine();
        PrintSupport.printMessage("\nSterilized: (0 -> No  |  1 -> Yes)");
        String sterilized = scanner.nextLine();
        PrintSupport.printMessage("\nDisability: (0 -> No  |  1 -> Yes)");
        String disability = scanner.nextLine();
        String disabilityType = "";
        if (disability.equals("1")) {
            PrintSupport.printMessage("\nDisability type:");
            disabilityType = scanner.nextLine();
        }
        this.controller.setGeneralInfo(coatLength, vaccinated, microchipped, dewormed, sterilized, disability, disabilityType);

        String education = "";
        String testFiv = "";
        String testFelv = "";
        if (type == 0) {
            PrintSupport.printMessage("\nNeed of a program of dog education: (0 -> No  |  1 -> Yes)");
            education = scanner.nextLine();

        }else {
            PrintSupport.printMessage("\nTest Fiv: (0 -> Negative  |  1 -> Positive)");
            testFiv = scanner.nextLine();
            PrintSupport.printMessage("\nTest Felv: (0 -> Negative  |  1 -> Positive)");
            testFelv = scanner.nextLine();
        }

        PrintSupport.printMessage("\n\nNow insert the requested compatibility: (0 -> Not compatible  |  1 -> Compatible)");
        PrintSupport.printMessage("\nMale dog:");
        String maleDog = scanner.nextLine();
        PrintSupport.printMessage("\nFemale dog:");
        String femaleDog = scanner.nextLine();
        PrintSupport.printMessage("\nMale cat:");
        String maleCat = scanner.nextLine();
        PrintSupport.printMessage("\nFemale cat:");
        String femaleCat = scanner.nextLine();
        this.controller.setCompatibilityWithPets(maleDog, femaleDog, maleCat, femaleCat);

        PrintSupport.printMessage("\nChildren:");
        String children = scanner.nextLine();
        PrintSupport.printMessage("\nElders:");
        String elders = scanner.nextLine();
        PrintSupport.printMessage("\nSleeping outside:");
        String sleepOutside = scanner.nextLine();
        PrintSupport.printMessage("\nFirst experience:");
        String firstExperience = scanner.nextLine();
        PrintSupport.printMessage("\nHours alone: ( 0 -> from 1 to 3 hours  |  1 -> from 4 to 6 hours  |  2 -> more than 6 hours)");
        int hoursAlone = scanner.nextInt();
        scanner.nextLine();
        this.controller.setOtherCompatibility(children, elders, sleepOutside, firstExperience, hoursAlone);

        if (type == 0)
            this.controller.setDogInfo(size, education);
        else
            this.controller.setCatInfo(testFiv, testFelv);

        this.controller.complete();
        PrintSupport.printMessage("********  DONE  ********\n\tPress ENTER to continue");
        ScannerSupport.waitEnter();
        CLIShelterHomepageController cliShelterHomepageController= new CLIShelterHomepageController();
        cliShelterHomepageController.start();
    }
}
