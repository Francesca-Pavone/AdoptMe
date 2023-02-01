package com.ispwproject.adoptme.controller.graficcontroller.cli;

import com.ispwproject.adoptme.controller.appcontroller.PetInfoController;
import com.ispwproject.adoptme.controller.graficcontroller.cli.requests.CLISendRequestController;
import com.ispwproject.adoptme.engineering.bean.PetBean;
import com.ispwproject.adoptme.engineering.exception.CommandNotFoundException;
import com.ispwproject.adoptme.engineering.exception.NoAccoutException;
import com.ispwproject.adoptme.engineering.session.Session;
import com.ispwproject.adoptme.engineering.utils.PrintSupport;
import com.ispwproject.adoptme.engineering.utils.ScannerSupport;
import com.ispwproject.adoptme.engineering.utils.ShowExceptionSupport;
import com.ispwproject.adoptme.view.cli.CLINeedAccountView;
import com.ispwproject.adoptme.view.cli.CLIPetInformationView;
import com.ispwproject.adoptme.view.cli.CLIUserHomepageView;

public class CLIPetInformationController {

    private final PetBean petBean;
    private CLIPetInformationView cliPetInformationViewCurrent;
    private static final String REQUEST = "1";
    private static final String FAVORITE = "2";
    private static final String HOMEPAGE = "3";

    public CLIPetInformationController(PetBean petBean) {
        this.petBean = petBean;
    }

    public void setCliPetInformationViewCurrent(CLIPetInformationView cliPetInformationViewCurrent) {
        this.cliPetInformationViewCurrent = cliPetInformationViewCurrent;
    }

    public void executeCommand(String inputLine) {
        try {
            switch (inputLine) {
                case REQUEST -> this.executeRequest();


                case FAVORITE -> PrintSupport.printMessage("Pet add to favorites -->> DA FARE");

                case HOMEPAGE -> {
                    if (Session.getCurrentSession().getShelterBean() == null) {
                        CLIUserHomepageView cliUserHomepageView = new CLIUserHomepageView();
                        cliUserHomepageView.run();
                    }
                    // todo: fare shelter homepage
                }
                default -> throw new CommandNotFoundException();
            }
        } catch (CommandNotFoundException e) {
            PrintSupport.printError(e.getMessage() + "1 | 2 | 3\nPress ENTER to continue");
            ScannerSupport.waitEnter();
            this.cliPetInformationViewCurrent.showCommand();
        }
    }

    private void executeRequest() {
        try {
            if (Session.getCurrentSession().getUserBean() == null)
                throw new NoAccoutException();
            else {
                CLISendRequestController cliSendRequestController = new CLISendRequestController();
                cliSendRequestController.sendRequest(petBean);
            }
        } catch (NoAccoutException e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
            CLINeedAccountView cliNeedAccountView = new CLINeedAccountView();
            cliNeedAccountView.showMessage();
        }
    }

    public void setPetInfo() throws Exception {

        PetInfoController petInfoControllerA = new PetInfoController();
        petInfoControllerA.getPetInfo(petBean);

        String dayOfBirth = "";
        String monthOfBirth = "";
        // year of birth is mandatory information on pet registration
        String yearOfBirth = String.valueOf(petBean.getYearOfBirth());

        if (petBean.getDayOfBirth() != 0)  // day of birth not known
            dayOfBirth = String.valueOf(petBean.getDayOfBirth());

        if (petBean.getMonthOfBirth() != 0)  // month of birth not known
            monthOfBirth = String.valueOf(petBean.getMonthOfBirth());



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
        String  dogSize = "";
        String dogEducation = "";
        String testFiv = "";
        String testFelv = "";
        // check if it's a dog
        if (petBean.getType() == 0) {
            dogSize = String.valueOf(
                    switch (petBean.getSize()) {
                        case 1 -> "Medium";
                        case 2 -> "Large";
                        case 3 -> "ExtraLarge";
                        default -> "Small";   //case 0
                    }
            );
            dogEducation = "\t\tProgram of dog education: Not needed\n";
            if (petBean.isDogEducation())
                dogEducation = "\t\tProgram of dog education: Needed\n";
        } else {
            testFiv = "\t\tTest Fiv: Negative\n";
            if (petBean.isTestFiv())
                testFiv = "\t\tTest Fiv: Positive\n";

            testFelv = "\t\tTest Felv: Negative\n";
            if (petBean.isTestFelv())
                testFelv = "\t\tTest Felv: Positive\n";
        }
        String generalInfo = getCommonGeneralInfo() + testFiv + testFelv + dogEducation;

        String compatibility = getCompatibility(petBean);

        CLIPetInformationView cliPetInformationView = new CLIPetInformationView(this);
        cliPetInformationView.showData(petBean.getName(), dayOfBirth, monthOfBirth, yearOfBirth, type, gender, coatLenght, dogSize, generalInfo, compatibility);
    }

    private String getCommonGeneralInfo() {
        String vaccinated = "\t\tVaccinations not completed\n";
        if (petBean.isVaccinated())
            vaccinated = "\t\tVaccinations completed\n";

        String microchipped = "\t\tNot microchipped\n";
        if (petBean.isMicrochipped())
            microchipped = "\t\tMicrochipped\n";

        String dewormed = "\t\tNot dewormed\n";
        if (petBean.isDewormed())
            dewormed = "\t\tDewormed\n";

        String sterilized = "\t\tNot sterilized\n";
        if (petBean.isSterilized())
            sterilized = "\t\tSterilized\n";

        String disability = "";
        String disabilityType = "";
        if (petBean.isDisability()) {
            disability = "\t\tDisability";
            disabilityType = "(Not specified)\n";
            if (!petBean.getDisabilityType().equals(""))
                disabilityType= "(" + petBean.getDisabilityType() + ")\n";
        }
        return vaccinated + microchipped + dewormed + sterilized + disability + disabilityType;
    }

    private String getCompatibility(PetBean petBean) {
        String compatibility = "";
        if (petBean.isMaleDog()) {
            compatibility = compatibility.concat( "\t\tMale dogs\n");
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
        return compatibility;
    }


}
