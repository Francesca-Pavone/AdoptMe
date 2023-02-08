package com.ispwproject.adoptme.controller.graficcontroller.cli;

import com.ispwproject.adoptme.controller.appcontroller.AddToFavoritesController;
import com.ispwproject.adoptme.controller.appcontroller.PetInfoController;
import com.ispwproject.adoptme.controller.graficcontroller.cli.requests.CLISendRequestController;
import com.ispwproject.adoptme.engineering.bean.PetBean;
import com.ispwproject.adoptme.engineering.bean.UserBean;
import com.ispwproject.adoptme.engineering.exception.Fra.CommandNotFoundException;
import com.ispwproject.adoptme.engineering.exception.Fede.FavoriteListEmptyException;
import com.ispwproject.adoptme.engineering.exception.Fra.NoAccountException;
import com.ispwproject.adoptme.engineering.observer.Observer;
import com.ispwproject.adoptme.engineering.session.Session;
import com.ispwproject.adoptme.engineering.utils.PrintSupport;
import com.ispwproject.adoptme.engineering.utils.ScannerSupport;
import com.ispwproject.adoptme.engineering.utils.ShowExceptionSupport;
import com.ispwproject.adoptme.view.cli.CLIPetInformationView;

public class CLIPetInformationController implements CLIGraficController, Observer {

    private final PetBean petBean;
    private boolean fav = false;

    private Observer favObserver;

    private final CLIPetInformationView view;
    private static final String REQUEST = "1";
    private static final String FAVORITE = "2";
    private static final String HOMEPAGE = "3";
    private int index;
    private Object object = null;

    public CLIPetInformationController(PetBean petBean) {
        this.petBean = petBean;
        this.view = new CLIPetInformationView(this);
    }

    public void setFavObserver(Observer favObserver) {
        this.favObserver = favObserver;
    }

    @Override
    public void start(){
        PetInfoController petInfoControllerA = new PetInfoController();
        petInfoControllerA.getPetInfo(petBean);

        String dateOfBirth;

        if (petBean.getMonthOfBirth() != 0) {  // month of birth not known
            if (petBean.getDayOfBirth() != 0)  // day of birth not known
                dateOfBirth = "\tDate of birth: " + petBean.getDayOfBirth() + " / " + petBean.getMonthOfBirth() + " / " + petBean.getYearOfBirth();
            else
                dateOfBirth = "\tDate of birth: " + petBean.getMonthOfBirth() + " / " + petBean.getYearOfBirth();
        }
        else {
            // year of birth is mandatory information on pet registration
            dateOfBirth = "\tYear of birth: " + petBean.getYearOfBirth();
        }


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
        cliPetInformationView.showTitle(petBean.getName());
        cliPetInformationView.showData(dateOfBirth, type, gender, coatLenght, dogSize, generalInfo, compatibility);
        cliPetInformationView.showCommand(petBean.isFav());
    }


    public void executeCommand(String inputLine){
        try {
            switch (inputLine) {
                case REQUEST -> {
                    if (Session.getCurrentSession().getUserBean() == null)
                        throw new NoAccountException();
                    this.executeRequest(); }
                case FAVORITE -> {
                    if (Session.getCurrentSession().getUserBean() == null)
                        throw new NoAccountException();
                    this.addToFavorite();}
                case HOMEPAGE -> {
                    if(object instanceof CLIUserFavoritesController) {
                        ((CLIUserFavoritesController)object).start();
                    }
                    else if(object instanceof CLIShelterInfoController) {
                        ((CLIShelterInfoController)object).start();
                    }
                    else if (Session.getCurrentSession().getShelterBean() == null) {
                        CLIUserHomepageController cliUserHomepageController = new CLIUserHomepageController();
                        cliUserHomepageController.start();
                    }
                }
                default -> throw new CommandNotFoundException();
            }
        } catch (CommandNotFoundException e) {
            PrintSupport.printError(e.getMessage() + "1 | 2 | 3\nPress ENTER to continue");
            ScannerSupport.waitEnter();
            this.view.showCommand(petBean.isFav());
        } catch(NoAccountException e) {
            PrintSupport.printError(e.getMessage() + "\n\t Press ENTER to continue");
            ScannerSupport.waitEnter();
            this.view.showCommand(petBean.isFav());
        } catch (FavoriteListEmptyException e) {
            e.printStackTrace();
        }
    }

    private void addToFavorite(){
        UserBean userBean = Session.getCurrentSession().getUserBean();
        AddToFavoritesController addToFavoritesController = new AddToFavoritesController(this.petBean);
        PetInfoController petInfoController = new PetInfoController();
        fav = petInfoController.checkFavorite(petBean);
        if (fav) {
            addToFavoritesController.removePet(userBean, this, index);
            addToFavoritesController.removePet(userBean, favObserver, index);
        } else {
            addToFavoritesController.addPet(userBean, this, index);
        }
        this.view.showCommand(!fav);
    }

    private void executeRequest() {
        try {
            if (Session.getCurrentSession().getUserBean() == null)
                throw new NoAccountException();
            else {
                CLISendRequestController cliSendRequestController = new CLISendRequestController(petBean);
                cliSendRequestController.start();
            }
        } catch (NoAccountException e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
            CLINeedAccountController cliNeedAccountController = new CLINeedAccountController();
            cliNeedAccountController.start();
        }
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
        if (petBean.isNoGarden()) {
            compatibility = compatibility.concat("\t\tApartments without garden\n");
        }
        if (petBean.isNoTerrace()) {
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


    @Override
    public void update(Object object){
        if(!fav)
            PrintSupport.printMessage(petBean.getName() + " has been added to your favorite pets!");
        else
            PrintSupport.printMessage(petBean.getName() + " has been removed from your favorite pets");
    }

    @Override
    public void update2(Object object1, Object object2) {
        //ignore
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setPreviousPage(Object object) {
        this.object = object;
    }


}
