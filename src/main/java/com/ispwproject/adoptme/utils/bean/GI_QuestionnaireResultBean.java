package com.ispwproject.adoptme.utils.bean;

public class GI_QuestionnaireResultBean extends QuestionnaireResultBean{

    public GI_QuestionnaireResultBean() {}
    public GI_QuestionnaireResultBean(String type, String gender, boolean puppy, boolean young, boolean adult, boolean senior, String size, String haveAPet, boolean maleCat, boolean femaleCat, boolean maleDog, boolean femaleDog, String haveAGarden, String gardenSleepOutside, String haveATerrace, String terraceSleepOutside, String hoursAlone, String firstPet, String sterilizePet, String programEducation, String disabledPet, String specificArea, String city) {
        this.setTypeGI(type);
        this.setGenderGI(gender);
        setPuppy(puppy);
        setYoung(young);
        setAdult(adult);
        setSenior(senior);
        this.setSizeGI(size);
        this.setHaveAPetGI(haveAPet);
        setMaleCat(maleCat);
        setFemaleCat(femaleCat);
        setMaleDog(maleDog);
        setFemaleDog(femaleDog);
        this.setHaveAGardenGI(haveAGarden);
        this.setGardenSleepOutsideGI(gardenSleepOutside);
        this.setHaveATerraceGI(haveATerrace);
        this.setTerraceSleepOutsideGI(terraceSleepOutside);
        this.setHoursAloneGI(hoursAlone);
        this.setFirstPetGI(firstPet);
        this.setSterilizePetGI(sterilizePet);
        this.setProgramEducationGI(programEducation);
        this.setDisabledPetGI(disabledPet);
        this.setSpecificAreaGI(specificArea);
        this.setCity(city);
    }


    public void setTypeGI(String type) {
        if(type.equals("Dog"))
            this.type = 0;
        else if(type.equals("Cat"))
            this.type = 1;
    }

    public void setGenderGI(String gender) {
        if(gender.equals("Male"))
            this.gender = 0;
        else if(gender.equals("Female"))
            this.gender = 1;
    }

    public void setSizeGI(String size) {
        switch (size) {
            case "Small" -> this.gender = 0;
            case "Medium" -> this.gender = 1;
            case "Large" -> this.gender = 2;
            case "Extra Large" -> this.gender = 3;
        }
    }

    public void setHaveAPetGI(String haveAPet) {
        if(haveAPet.equals("Yes"))
            this.haveAPet = true;
        else if(haveAPet.equals("No"))
            this.haveAPet = false;
    }

    public void setHaveAGardenGI(String haveAGarden) {
        if(haveAGarden.equals("Yes"))
            this.haveAGarden = true;
        else if(haveAGarden.equals("No"))
            this.haveAGarden = false;
    }

    public void setGardenSleepOutsideGI(String gardenSleepOutside) {
        if(gardenSleepOutside.equals("Yes"))
            this.gardenSleepOutside = true;
        else if(gardenSleepOutside.equals("No"))
            this.gardenSleepOutside = false;
    }

    public void setHaveATerraceGI(String haveATerrace) {
        if(haveATerrace.equals("Yes"))
            this.haveATerrace = true;
        else if(haveATerrace.equals("No"))
            this.haveATerrace = false;
    }

    public void setTerraceSleepOutsideGI(String terraceSleepOutside) {
        if(terraceSleepOutside.equals("Yes"))
            this.terraceSleepOutside = true;
        else if(terraceSleepOutside.equals("No"))
            this.terraceSleepOutside = false;
    }

    public void setHoursAloneGI(String hoursAlone) {
        switch (hoursAlone) {
            case "1 - 3 hours" -> this.hoursAlone = 0;
            case "4 - 6 hours" -> this.hoursAlone = 1;
            case "More than 6 hours" -> this.hoursAlone = 2;
        }
    }

    public void setFirstPetGI(String firstPet) {
        if(firstPet.equals("Yes"))
            this.firstPet = true;
        else if(firstPet.equals("No"))
            this.firstPet = false;
    }

    public void setSterilizePetGI(String sterilizePet) {
        if(sterilizePet.equals("Yes"))
            this.sterilizePet = true;
        else if(sterilizePet.equals("No"))
            this.sterilizePet = false;
    }

    public void setProgramEducationGI(String programEducation) {
        if(programEducation.equals("Yes"))
            this.programEducation = true;
        else if(programEducation.equals("No"))
            this.programEducation = false;
    }

    public void setDisabledPetGI(String disabledPet) {
        if(disabledPet.equals("Yes"))
            this.disabledPet = true;
        else if(disabledPet.equals("No"))
            this.disabledPet = false;
    }

    public void setSpecificAreaGI(String specificArea) {
        if(specificArea.equals("Yes"))
            this.specificArea = true;
        else if(specificArea.equals("No"))
            this.specificArea = false;
    }
}

