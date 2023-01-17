package com.ispwproject.adoptme.controller.appcontroller;

import com.ispwproject.adoptme.model.CatModel;
import com.ispwproject.adoptme.model.DogModel;
import com.ispwproject.adoptme.model.PetCompatibility;
import com.ispwproject.adoptme.utils.bean.PetBean;
import com.ispwproject.adoptme.utils.dao.CatDAO;
import com.ispwproject.adoptme.utils.dao.DogDAO;

public class PetInfoController_A {


    public void getPetInfo(PetBean petBean) throws Exception {
        if (petBean.getType() == 0) {
            DogModel dogModel = DogDAO.retriveDogById(petBean.getPetId(), petBean.getShelterId());

            //vado a settare nel bean le nuove info del pet che mi servono
            setCommonAttr(petBean, dogModel.getYearOfBirth(), dogModel.getMonthOfBirth(), dogModel.getDayOfBirth(), dogModel.getCoatLenght(), dogModel.isVaccinated(), dogModel.isMicrochipped(), dogModel.isDewormed(), dogModel.isSterilized(), dogModel.isDisability(), dogModel.getDisabilityType(), dogModel.getPetCompatibility());
            petBean.setDogEducation(dogModel.isProgramEducation());
            petBean.setSize(dogModel.getSize());

        }
        else {
            CatModel catModel = CatDAO.retriveCatById(petBean.getPetId(), petBean.getShelterId());
            //vado a settare nel bean le nuove info del pet che mi servono
            setCommonAttr(petBean, catModel.getYearOfBirth(), catModel.getMonthOfBirth(), catModel.getDayOfBirth(), catModel.getCoatLenght(), catModel.isVaccinated(), catModel.isMicrochipped(), catModel.isDewormed(), catModel.isSterilized(), catModel.isDisability(), catModel.getDisabilityType(), catModel.getPetCompatibility());
            petBean.setTestFiv(catModel.isTestFiv());
            petBean.setTestFelv(catModel.isTestFelv());
        }


    }

    private void setCommonAttr(PetBean petBean, int yearOfBirth, int monthOfBirth, int dayOfBirth, int coatLenght, boolean vaccinated, boolean microchipped, boolean dewormed, boolean sterilized, boolean disability, String disabilityType, PetCompatibility petCompatibility) {
        petBean.setYearOfBirth(yearOfBirth);
        petBean.setMonthOfBirth(monthOfBirth);
        petBean.setDayOfBirth(dayOfBirth);
        petBean.setCoatLenght(coatLenght);
        petBean.setVaccinated(vaccinated);
        petBean.setMicrochipped(microchipped);
        petBean.setDewormed(dewormed);
        petBean.setSterilized(sterilized);
        petBean.setDisability(disability);
        petBean.setDisabilityType(disabilityType);
        petBean.setMaleDog(petCompatibility.isMaleDog());
        petBean.setFemaleDog(petCompatibility.isFemaleDog());
        petBean.setMaleCat(petCompatibility.isMaleCat());
        petBean.setFemaleCat(petCompatibility.isFemaleCat());
        petBean.setChildren(petCompatibility.isChildren());
        petBean.setElders(petCompatibility.isElders());
        petBean.setApartmentNoGarden(petCompatibility.isApartmentNoGarden());
        petBean.setApartmentNoTerrace(petCompatibility.isApartmentNoTerrace());
        petBean.setSleepOutside(petCompatibility.isSleepOutside());
        petBean.setFirstExperience(petCompatibility.isFirstExperience());
        petBean.setHoursAlone(petCompatibility.getHoursAlone());
    }


}
