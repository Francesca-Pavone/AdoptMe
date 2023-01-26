package com.ispwproject.adoptme.controller.appcontroller;

import com.ispwproject.adoptme.model.CatModel;
import com.ispwproject.adoptme.model.DogModel;
import com.ispwproject.adoptme.model.PetCompatibility;
import com.ispwproject.adoptme.model.ShelterModel;
import com.ispwproject.adoptme.engineering.bean.PetBean;
import com.ispwproject.adoptme.engineering.bean.ShelterBean;
import com.ispwproject.adoptme.engineering.dao.CatDAO;
import com.ispwproject.adoptme.engineering.dao.DogDAO;
import com.ispwproject.adoptme.engineering.dao.ShelterDAO;

public class PetInfoController {


    public ShelterBean getPetInfo(PetBean petBean) throws Exception {
        ShelterModel shelterModel = ShelterDAO.retrieveShelterById(petBean.getShelterId());

        if (petBean.getType() == 0) {
            DogModel dogModel = DogDAO.retrieveDogById(petBean.getPetId(), petBean.getShelterId());

            //vado a settare nel bean le nuove info del pet che mi servono
            setGeneralInfo(petBean, dogModel.getYearOfBirth(), dogModel.getMonthOfBirth(), dogModel.getDayOfBirth(), dogModel.getCoatLenght());
            setMedicalInfo(petBean, dogModel.isVaccinated(), dogModel.isMicrochipped(), dogModel.isDewormed(), dogModel.isSterilized(), dogModel.isDisability(), dogModel.getDisabilityType());
            setCompatibility(petBean, dogModel.getPetCompatibility());
            petBean.setDogEducation(dogModel.isProgramEducation());
            petBean.setSize(dogModel.getSize());

        }
        else {
            CatModel catModel = CatDAO.retrieveCatById(petBean.getPetId(), petBean.getShelterId());

            setGeneralInfo(petBean, catModel.getYearOfBirth(), catModel.getMonthOfBirth(), catModel.getDayOfBirth(), catModel.getCoatLenght());
            setMedicalInfo(petBean, catModel.isVaccinated(), catModel.isMicrochipped(), catModel.isDewormed(), catModel.isSterilized(), catModel.isDisability(), catModel.getDisabilityType());
            setCompatibility(petBean, catModel.getPetCompatibility());
            petBean.setTestFiv(catModel.isTestFiv());
            petBean.setTestFelv(catModel.isTestFelv());
        }
        return new ShelterBean(shelterModel);

    }

    private void setGeneralInfo(PetBean petBean, int yearOfBirth, int monthOfBirth, int dayOfBirth, int coatLenght) {
        petBean.setYearOfBirth(yearOfBirth);
        petBean.setMonthOfBirth(monthOfBirth);
        petBean.setDayOfBirth(dayOfBirth);
        petBean.setCoatLenght(coatLenght);
    }

    private void setMedicalInfo(PetBean petBean, boolean vaccinated, boolean microchipped, boolean dewormed, boolean sterilized, boolean disability, String disabilityType){
        petBean.setVaccinated(vaccinated);
        petBean.setMicrochipped(microchipped);
        petBean.setDewormed(dewormed);
        petBean.setSterilized(sterilized);
        petBean.setDisability(disability);
        petBean.setDisabilityType(disabilityType);
    }

    private void setCompatibility(PetBean petBean, PetCompatibility petCompatibility) {
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
