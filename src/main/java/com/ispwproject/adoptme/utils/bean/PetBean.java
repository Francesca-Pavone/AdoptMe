package com.ispwproject.adoptme.utils.bean;

import com.ispwproject.adoptme.utils.ImageUtils;
import javafx.scene.image.Image;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.util.Date;

public class PetBean {
    private File petImage;
    private String name;
    private int type; // 0 -> DOG  |  1 -> CAT
    private LocalDate fullDateOfBirth;
    private String yearOfBirth;
    private String monthOfBirth;
    private int gender; // 0 -> MALE  |  1 -> FEMALE
    private String coatLenght;

    private boolean vaccinated;
    private boolean microchipped;
    private boolean dewormed;
    private boolean sterilized;
    private boolean disability;
    private String disabilityType;

    // compatibility
    private boolean sterMaleDog;
    private boolean notSterMaleDog;
    private boolean sterFemaleDog;
    private boolean notSterFemaleDog;
    private boolean sterMaleCat;
    private boolean notSterMaleCat;
    private boolean sterFemaleCat;
    private boolean notSterFemaleCat;
    private boolean children;
    private boolean elders;
    private boolean apartmentNoGarden;
    private boolean apartmentNoTerrace;
    private boolean sleepOutside;
    private boolean firstExperience;
    private String hoursAlone;

    public PetBean() {
    }



    // constructor with full date
    public PetBean(File petImage, String name, int type, LocalDate fullDateOfBirth, String gender, String coatLenght, String vaccinated, String microchipped, String dewormed, String sterilized, String disability, String disabilityType, boolean sterMaleDog, boolean notSterMaleDog, boolean sterFemaleDog, boolean notSterFemaleDog, boolean sterMaleCat, boolean notSterMaleCat, boolean sterFemaleCat, boolean notSterFemaleCat, boolean children, boolean elders, boolean apartmentNoGarden, boolean apartmentNoTerrace, boolean sleepOutside, boolean firstExperience, String hoursAlone) {
        this.setPetImage(petImage);
        this.setName(name);
        this.setType(type);
        this.setFullDateOfBirth(fullDateOfBirth);
        this.setGender(gender);
        this.setCoatLenght(coatLenght);
        this.setVaccinated(vaccinated);
        this.setMicrochipped(microchipped);
        this.setDewormed(dewormed);
        this.setSterilized(sterilized);
        this.setDisability(disability);
        this.setDisabilityType(disabilityType);
        this.setSterMaleDog(sterMaleDog);
        this.setNotSterMaleDog(notSterMaleDog);
        this.setSterFemaleDog(sterFemaleDog);
        this.setNotSterFemaleDog(notSterFemaleDog);
        this.setSterMaleCat(sterMaleCat);
        this.setNotSterMaleCat(notSterMaleCat);
        this.setSterFemaleCat(sterFemaleCat);
        this.setNotSterFemaleCat(notSterFemaleCat);
        this.setChildren(children);
        this.setElders(elders);
        this.setApartmentNoGarden(apartmentNoGarden);
        this.setApartmentNoTerrace(apartmentNoTerrace);
        this.setSleepOutside(sleepOutside);
        this.setFirstExperience(firstExperience);
        this.setHoursAlone(hoursAlone);
    }

    // constructor without full date, with year and month of birth
    public PetBean(File petImage, String name, int type, String yearOfBirth, String monthOfBirth, String gender, String coatLenght, String vaccinated, String microchipped, String dewormed, String sterilized, String disability, String disabilityType, boolean sterMaleDog, boolean notSterMaleDog, boolean sterFemaleDog, boolean notSterFemaleDog, boolean sterMaleCat, boolean notSterMaleCat, boolean sterFemaleCat, boolean notSterFemaleCat, boolean children, boolean elders, boolean apartmentNoGarden, boolean apartmentNoTerrace, boolean sleepOutside, boolean firstExperience, String hoursAlone) {
        this.setPetImage(petImage);
        this.setName(name);
        this.setType(type);
        this.setYearOfBirth(yearOfBirth);
        this.setMonthOfBirth(monthOfBirth);
        this.setGender(gender);
        this.setCoatLenght(coatLenght);
        this.setVaccinated(vaccinated);
        this.setMicrochipped(microchipped);
        this.setDewormed(dewormed);
        this.setSterilized(sterilized);
        this.setDisability(disability);
        this.setDisabilityType(disabilityType);
        this.setSterMaleDog(sterMaleDog);
        this.setNotSterMaleDog(notSterMaleDog);
        this.setSterFemaleDog(sterFemaleDog);
        this.setNotSterFemaleDog(notSterFemaleDog);
        this.setSterMaleCat(sterMaleCat);
        this.setNotSterMaleCat(notSterMaleCat);
        this.setSterFemaleCat(sterFemaleCat);
        this.setNotSterFemaleCat(notSterFemaleCat);
        this.setChildren(children);
        this.setElders(elders);
        this.setApartmentNoGarden(apartmentNoGarden);
        this.setApartmentNoTerrace(apartmentNoTerrace);
        this.setSleepOutside(sleepOutside);
        this.setFirstExperience(firstExperience);
        this.setHoursAlone(hoursAlone);
    }

    // constructor without full date, with year but without month of birth
    public PetBean(File petImage, String name, int type, String yearOfBirth, String gender, String coatLenght, String vaccinated, String microchipped, String dewormed, String sterilized, String disability, String disabilityType, boolean sterMaleDog, boolean notSterMaleDog, boolean sterFemaleDog, boolean notSterFemaleDog, boolean sterMaleCat, boolean notSterMaleCat, boolean sterFemaleCat, boolean notSterFemaleCat, boolean children, boolean elders, boolean apartmentNoGarden, boolean apartmentNoTerrace, boolean sleepOutside, boolean firstExperience, String hoursAlone) {
        this.setPetImage(petImage);
        this.setName(name);
        this.setType(type);
        this.setYearOfBirth(yearOfBirth);
        this.setGender(gender);
        this.setCoatLenght(coatLenght);
        this.setVaccinated(vaccinated);
        this.setMicrochipped(microchipped);
        this.setDewormed(dewormed);
        this.setSterilized(sterilized);
        this.setDisability(disability);
        this.setDisabilityType(disabilityType);
        this.setSterMaleDog(sterMaleDog);
        this.setNotSterMaleDog(notSterMaleDog);
        this.setSterFemaleDog(sterFemaleDog);
        this.setNotSterFemaleDog(notSterFemaleDog);
        this.setSterMaleCat(sterMaleCat);
        this.setNotSterMaleCat(notSterMaleCat);
        this.setSterFemaleCat(sterFemaleCat);
        this.setNotSterFemaleCat(notSterFemaleCat);
        this.setChildren(children);
        this.setElders(elders);
        this.setApartmentNoGarden(apartmentNoGarden);
        this.setApartmentNoTerrace(apartmentNoTerrace);
        this.setSleepOutside(sleepOutside);
        this.setFirstExperience(firstExperience);
        this.setHoursAlone(hoursAlone);
    }


    // setter
    public void setPetImage(File petImage) {
        this.petImage = petImage;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setType(/*String*/ int type) {
        /*
        if (type.equals("Dog"))
            this.type = 0;
        else if (type.equals("Cat"))
            this.type = 1;
         */
        this.type = type;
    }

    public void setFullDateOfBirth(LocalDate fullDateOfBirth) {
        this.fullDateOfBirth = fullDateOfBirth;
    }
    public void setYearOfBirth(String yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public void setMonthOfBirth(String monthOfBirth) {
        this.monthOfBirth = monthOfBirth;
    }

    public void setGender(String gender) {
        if (gender.equals("Male"))
            this.gender = 0;
        else if (gender.equals("Female"))
            this.gender = 1;

    }

    public void setCoatLenght(String coatLenght) {
        this.coatLenght = coatLenght;
    }

    public void setVaccinated(String vaccinated) {
        if (vaccinated.equals("Yes"))
            this.vaccinated = true;
        else if (vaccinated.equals("No"))
            this.vaccinated = false;
    }

    public void setMicrochipped(String microchipped) {
        if (microchipped.equals("Yes"))
            this.microchipped = true;
        else if (microchipped.equals("No"))
            this.microchipped = false;
    }

    public void setDewormed(String dewormed) {
        if (dewormed.equals("Yes"))
            this.dewormed = true;
        else if (dewormed.equals("No"))
            this.dewormed = false;
    }

    public void setSterilized(String sterilized) {
        if (sterilized.equals("Yes"))
            this.sterilized = true;
        else if (sterilized.equals("No"))
            this.sterilized = false;
    }

    public void setDisability(String disability) {
        if (disability.equals("Yes"))
            this.disability = true;
        else if (disability.equals("No"))
            this.disability = false;
    }

    public void setDisabilityType(String disabilityType) {
        this.disabilityType = disabilityType;
    }



    public void setSterMaleDog(boolean sterMaleDog) {
        this.sterMaleDog = sterMaleDog;
    }

    public void setNotSterMaleDog(boolean notSterMaleDog) {
        this.notSterMaleDog = notSterMaleDog;
    }

    public void setSterFemaleDog(boolean sterFemaleDog) {
        this.sterFemaleDog = sterFemaleDog;
    }

    public void setNotSterFemaleDog(boolean notSterFemaleDog) {
        this.notSterFemaleDog = notSterFemaleDog;
    }

    public void setSterMaleCat(boolean sterMaleCat) {
        this.sterMaleCat = sterMaleCat;
    }

    public void setNotSterMaleCat(boolean notSterMaleCat) {
        this.notSterMaleCat = notSterMaleCat;
    }

    public void setSterFemaleCat(boolean sterFemaleCat) {
        this.sterFemaleCat = sterFemaleCat;
    }

    public void setNotSterFemaleCat(boolean notSterFemaleCat) {
        this.notSterFemaleCat = notSterFemaleCat;
    }

    public void setChildren(boolean children) {
        this.children = children;
    }

    public void setElders(boolean elders) {
        this.elders = elders;
    }

    public void setApartmentNoGarden(boolean apartmentNoGarden) {
        this.apartmentNoGarden = apartmentNoGarden;
    }

    public void setApartmentNoTerrace(boolean apartmentNoTerrace) {
        this.apartmentNoTerrace = apartmentNoTerrace;
    }

    public void setSleepOutside(boolean sleepOutside) {
        this.sleepOutside = sleepOutside;
    }

    public void setFirstExperience(boolean firstExperience) {
        this.firstExperience = firstExperience;
    }

    public void setHoursAlone(String hoursAlone) {
        this.hoursAlone = hoursAlone;
    }


// getter

    public Image getPetImage() throws IOException {
        return ImageUtils.fromFileToImage(petImage);
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }

    public LocalDate getFullDateOfBirth() {
        return fullDateOfBirth;
    }
    public String getYearOfBirth() {
        return yearOfBirth;
    }

    public String getMonthOfBirth() {
        return monthOfBirth;
    }

    public int getGender() {
        return gender;
    }

    public String getCoatLenght() {
        return coatLenght;
    }

    public boolean getVaccinated() {
        return vaccinated;
    }

    public boolean getMicrochipped() {
        return microchipped;
    }

    public boolean getDewormed() {
        return dewormed;
    }

    public boolean getSterilized() {
        return sterilized;
    }

    public boolean getDisability() {
        return disability;
    }

    public String getDisabilityType() {
        return disabilityType;
    }

    public boolean isCompSterMaleDog() {
        return sterMaleDog;
    }

    public boolean isCompNotSterMaleDog() {
        return notSterMaleDog;
    }

    public boolean isCompSterFemaleDog() {
        return sterFemaleDog;
    }

    public boolean isCompNotSterFemaleDog() {
        return notSterFemaleDog;
    }

    public boolean isCompSterMaleCat() {
        return sterMaleCat;
    }

    public boolean isCompNotSterMaleCat() {
        return notSterMaleCat;
    }

    public boolean isCompSterFemaleCat() {
        return sterFemaleCat;
    }

    public boolean isCompNotSterFemaleCat() {
        return notSterFemaleCat;
    }

    public boolean isCompChildren() {
        return children;
    }

    public boolean isCompElders() {
        return elders;
    }

    public boolean isCompApartmentNoGarden() {
        return apartmentNoGarden;
    }

    public boolean isCompApartmentNoTerrace() {
        return apartmentNoTerrace;
    }

    public boolean isCompSleepOutside() {
        return sleepOutside;
    }

    public boolean isCompFirstExperience() {
        return firstExperience;
    }

    public String getCompHoursAlone() {
        return hoursAlone;
    }

    /*
    public String getAge(){
        int currYear = Year.now().getValue();
        LocalDate currDate = LocalDate.now();
        int yearOfBirth;
        int monthOfBirth;
        if (fullDateOfBirth != null){
            Period months = Period.between(currDate, fullDateOfBirth);
            System.out.println(months);
            return months.toString();

        }
        return "boh";
    }

     */

}
