package com.ispwproject.adoptme.utils.bean;

import com.ispwproject.adoptme.model.DogModel;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class DogBean extends PetBean{

    public DogBean() {
    }

    public DogBean(File petImage, String name, String disabilityType, boolean maleDog, boolean femaleDog, boolean maleCat, boolean femaleCat, boolean children, boolean elders, boolean apartmentNoGarden, boolean apartmentNoTerrace, boolean sleepOutside, boolean firstExperience) {
        this.setPetImage(petImage);
        this.setName(name);
        this.setDisabilityType(disabilityType);
        this.setMaleDog(maleDog);
        this.setFemaleDog(femaleDog);
        this.setMaleCat(maleCat);
        this.setFemaleCat(femaleCat);
        this.setChildren(children);
        this.setChildren(elders);
        this.setApartmentNoGarden(apartmentNoGarden);
        this.setApartmentNoTerrace(apartmentNoTerrace);
        this.setSleepOutside(sleepOutside);
        this.setFirstExperience(firstExperience);
    }

    public DogBean(DogBean dogBean) throws IOException {
        this.setPetImage(dogBean.getPetImage());
        this.setName(dogBean.getName());
        this.setType(dogBean.getType());
        this.setYearOfBirth(dogBean.getYearOfBirth());
        this.setMonthOfBirth(dogBean.getMonthOfBirth());
        this.setDayOfBirth(dogBean.getDayOfBirth());
        this.setGender(dogBean.getGender());
        this.setCoatLenght(dogBean.getCoatLenght());
        this.setVaccinated(dogBean.isVaccinated());
        this.setMicrochipped(dogBean.isMicrochipped());
        this.setDewormed(dogBean.isDewormed());
        this.setSterilized(dogBean.isSterilized());
        this.setDisability(dogBean.isDisability());
        this.setDisabilityType(dogBean.getDisabilityType());
        this.setMaleDog(dogBean.isMaleDog());
        this.setFemaleDog(dogBean.isFemaleDog());
        this.setMaleCat(dogBean.isMaleCat());
        this.setFemaleCat(dogBean.isFemaleCat());
        this.setChildren(dogBean.isChildren());
        this.setElders(dogBean.isElders());
        this.setApartmentNoGarden(dogBean.isApartmentNoGarden());
        this.setApartmentNoTerrace(dogBean.isApartmentNoTerrace());
        this.setSleepOutside(dogBean.isSleepOutside());
        this.setFirstExperience(dogBean.isFirstExperience());
        this.setHoursAlone(dogBean.getHoursAlone());

        this.setDogEducation(dogBean.isDogEducation());
        this.setSize(dogBean.getSize());
    }




}