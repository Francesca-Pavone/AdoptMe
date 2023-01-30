package com.ispwproject.adoptme.controller.graficcontroller.cli;

import com.ispwproject.adoptme.controller.appcontroller.PetInfoController;
import com.ispwproject.adoptme.controller.graficcontroller.cli.requests.CLISendRequestController;
import com.ispwproject.adoptme.engineering.bean.PetBean;
import com.ispwproject.adoptme.engineering.exception.NoAccoutException;
import com.ispwproject.adoptme.engineering.session.Session;
import com.ispwproject.adoptme.engineering.utils.PrintSupport;
import com.ispwproject.adoptme.engineering.utils.ScannerSupport;
import com.ispwproject.adoptme.view.cli.CLINeedAccountView;
import com.ispwproject.adoptme.view.cli.CLIPetInformationView;
import com.ispwproject.adoptme.view.cli.CLIUserHomepageView;

public class CLIPetInformationController {

    private PetBean petBean;
    private static final String REQUEST = "1";
    private static final String FAVORITE = "2";
    private static final String HOMEPAGE = "3";

    public CLIPetInformationController(PetBean petBean) {
        this.petBean = petBean;

    }

    public void executeCommand(String inputLine) throws Exception {
        switch (inputLine) {
            case REQUEST -> {
                try {
                    if (Session.getCurrentSession().getUserBean() == null)
                        throw new NoAccoutException();
                    else {
                        CLISendRequestController cliSendRequestController = new CLISendRequestController();
                        cliSendRequestController.sendRequest(petBean);
                    }
                }
                catch (NoAccoutException e) {
                    PrintSupport.printError(e.getMessage()+ "\n\tPress ENTER to continue");
                    ScannerSupport.waitEnter();
                    CLINeedAccountView cliNeedAccountView = new CLINeedAccountView();
                    cliNeedAccountView.showMessage();
                }
            }

            case FAVORITE -> System.out.println("Pet add to favorites -->> DA FARE");

            case HOMEPAGE -> {
                if (Session.getCurrentSession().getShelterBean() == null) {
                    CLIUserHomepageView cliUserHomepageView = new CLIUserHomepageView();
                    cliUserHomepageView.run();
                }
                //todo: fare shelter homepage
            }
        }
    }
    public void setPetInfo() throws Exception {

        PetInfoController petInfoControllerA = new PetInfoController();
        petInfoControllerA.getPetInfo(petBean);

        String dayOfBirth;
        String monthOfBirth;
        String yearOfBirth;
        if (petBean.getDayOfBirth() == 0)  // day of birth not known
            dayOfBirth = "";
        else
            dayOfBirth = String.valueOf(petBean.getDayOfBirth());

        if (petBean.getMonthOfBirth() == 0)  // month of birth not known
            monthOfBirth = "";
        else
            monthOfBirth = String.valueOf(petBean.getMonthOfBirth());

        // year of birth is mandatory information on pet registration
        yearOfBirth = String.valueOf(petBean.getYearOfBirth());

        String type = String.valueOf(
                switch (petBean.getType()) {
                    case 1 -> "Cat";
                    default -> "Dog";
                });
        String gender = String.valueOf(
                switch (petBean.getGender()) {
                    case 1 -> "Female";
                    default -> "Male";
                }
        );
        String coatLenght = String.valueOf(
                switch (petBean.getCoatLenght()) {
                    case 1 -> "Medium";
                    case 2 -> "Long";
                    default -> "Short";     // case 0
                }
        );
        String  dogSize, dogEducation;
        // check if it isn't a dog
        if (petBean.getType() != 0) {
            dogSize = "";
            dogEducation = "";
        }
        else {
            dogSize = String.valueOf(
                    switch (petBean.getSize()) {
                        case 1 -> "Medium";
                        case 2 -> "Large";
                        case 3 -> "ExtraLarge";
                        default -> "Small";   //case 0
                    }
            );

            if (petBean.isDogEducation())
                dogEducation = "Program of dog education: Needed";
            else
                dogEducation = "Program of dog education: Not needed";
        }

        String vaccinated, microchipped, dewormed, sterilized;
        //General info
        if (petBean.isVaccinated())
            vaccinated = "Vaccinations completed";
        else
            vaccinated = "Vaccinations not completed";

        if (petBean.isMicrochipped())
            microchipped = "Microchipped";
        else
            microchipped = "Not microchipped";

        if (petBean.isDewormed())
            dewormed = "Dewormed";
        else
            dewormed = "Not dewormed";

        if (petBean.isSterilized())
            sterilized = "Sterilized";
        else
            sterilized = "Not sterilized";

        String testFiv, testFelv;
        // check if it isn't a cat
        if (petBean.getType() != 1){
            testFiv = "";
            testFelv = "";
        }
        else {
            if (petBean.isTestFiv())
                testFiv = "Test Fiv: Positive";
            else
                testFiv = "Test Fiv: Negative";

            if (petBean.isTestFelv())
                testFelv = "Test Felv: Positive";
            else
                testFelv = "Test Felv: Negative";
        }
        String disability;
        String disabilityType = "";
        if (!petBean.isDisability())
            disability = "";
        else {
            disability = "Disability";
            if (petBean.getDisabilityType().equals(""))
                disabilityType = "Not specified";
            else
                disabilityType= petBean.getDisabilityType();
        }
        String compatibility = "";
        if (petBean.isMaleDog()) {
            compatibility = compatibility.concat( "\t\tMale dogs \n");
        }
        if (petBean.isFemaleDog()) {
            compatibility = compatibility.concat("\t\tFemale dogs\n");
        }
        if (petBean.isMaleCat()) {
            compatibility = compatibility.concat("\t\tMale cats\n");
        }
        if (petBean.isFemaleCat()) {
            compatibility = compatibility.concat("\t\tFemale cats\n");
        }
        if (petBean.isChildren()) {
            compatibility = compatibility.concat("\t\tChildren\n");
        }
        if (petBean.isElders()) {
            compatibility = compatibility.concat("\t\tElders\n");
        }
        if (petBean.isApartmentNoGarden()) {
            compatibility = compatibility.concat("\t\tApartments without garden\n");
        }
        if (petBean.isApartmentNoTerrace()) {
            compatibility = compatibility.concat("\t\tApartments without terrace\n");
        }
        if (petBean.isSleepOutside()) {
            compatibility = compatibility.concat("\t\tSleeping outside\n");
        }
        if (petBean.isFirstExperience()) {
            compatibility = compatibility.concat("\t\tFirst experience\n");
        }

        compatibility = compatibility.concat(switch (petBean.getHoursAlone()) {
                    case 0 -> "\t\tStay from 1 to 3 hours alone";
                    case 1 -> "\t\tStay from 4 to 6 hours alone";
                    default -> "\t\tStay more than 6 hours alone"; // case 2
                }
        );
        CLIPetInformationView cliPetInformationView = new CLIPetInformationView(this);
        cliPetInformationView.showData(petBean.getName(), dayOfBirth, monthOfBirth, yearOfBirth, type, gender, coatLenght, dogSize, dogEducation, vaccinated, microchipped, dewormed, sterilized, testFiv, testFelv, disability, disabilityType, compatibility);
    }


}
