package com.ispwproject.adoptme.utils.bean;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

public class CatBean extends PetBean{
    public CatBean() {
    }

    public CatBean(File petImage, String name, LocalDate fullDateOfBirth, String disabilityType, boolean maleDog, boolean femaleDog, boolean maleCat, boolean femaleCat, boolean children, boolean elders, boolean apartmentNoGarden, boolean apartmentNoTerrace, boolean sleepOutside, boolean firstExperience) {
        this.setPetImage(petImage);
        this.setName(name);
        this.setFullDateOfBirth(fullDateOfBirth);
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

    public CatBean(CatBean catBean) {
        this.setPetImage(catBean.getPetImage());
        this.setName(catBean.getName());
        this.setType(catBean.getType());
        this.setFullDateOfBirth(catBean.getFullDateOfBirth());
        this.setYearOfBirth(catBean.getYearOfBirth());
        this.setMonthOfBirth(catBean.getMonthOfBirth());
        this.setGender(catBean.getGender());
        this.setCoatLenght(catBean.getCoatLenght());
        this.setVaccinated(catBean.isVaccinated());
        this.setMicrochipped(catBean.isMicrochipped());
        this.setDewormed(catBean.isDewormed());
        this.setSterilized(catBean.isSterilized());
        this.setDisability(catBean.isDisability());
        this.setDisabilityType(catBean.getDisabilityType());
        this.setMaleDog(catBean.isMaleDog());
        this.setFemaleDog(catBean.isFemaleDog());
        this.setMaleCat(catBean.isMaleCat());
        this.setFemaleCat(catBean.isFemaleCat());
        this.setChildren(catBean.isChildren());
        this.setElders(catBean.isElders());
        this.setApartmentNoGarden(catBean.isApartmentNoGarden());
        this.setApartmentNoTerrace(catBean.isApartmentNoTerrace());
        this.setSleepOutside(catBean.isSleepOutside());
        this.setFirstExperience(catBean.isFirstExperience());
        this.setHoursAlone(catBean.getHoursAlone());


        this.setTestFiv(catBean.isTestFiv());
        this.setTestFelv(catBean.isTestFelv());

    }


}
