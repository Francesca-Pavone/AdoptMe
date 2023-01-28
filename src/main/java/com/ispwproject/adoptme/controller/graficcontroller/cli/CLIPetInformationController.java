package com.ispwproject.adoptme.controller.graficcontroller.cli;

import com.ispwproject.adoptme.engineering.bean.PetBean;
import com.ispwproject.adoptme.view.cli.CLIPetInformationView;

public class CLIPetInformationController {

    public void showPet(PetBean petBean) {
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
            compatibility = compatibility + "          Male dogs \n";
        }
        if (petBean.isFemaleDog()) {
            compatibility = compatibility + "          Female dogs\n";
        }
        if (petBean.isMaleCat()) {
            compatibility = compatibility + "          Male cats\n";
        }
        if (petBean.isFemaleCat()) {
            compatibility = compatibility + "          Female cats\n";
        }
        if (petBean.isChildren()) {
            compatibility = compatibility + "          Children\n";
        }
        if (petBean.isElders()) {
            compatibility = compatibility + "          Elders\n";
        }
        if (petBean.isApartmentNoGarden()) {
            compatibility = compatibility + "          Apartments without garden\n";
        }
        if (petBean.isApartmentNoTerrace()) {
            compatibility = compatibility + "          Apartments without terrace\n";
        }
        if (petBean.isSleepOutside()) {
            compatibility = compatibility + "          Sleeping outside\n";
        }
        if (petBean.isFirstExperience()) {
            compatibility = compatibility + "          First experience\n";
        }

        compatibility = compatibility + (switch (petBean.getHoursAlone()) {
                    case 0 -> "            Stay from 1 to 3 hours alone";
                    case 1 -> "            Stay from 4 to 6 hours alone";
                    default -> "            Stay more than 6 hours alone"; // case 2
                }
        );
        CLIPetInformationView.setData(petBean.getName(), dayOfBirth, monthOfBirth, yearOfBirth, type, gender, coatLenght, dogSize, dogEducation, vaccinated, microchipped, dewormed, sterilized, testFiv, testFelv, disability, disabilityType, compatibility);
    }
}
