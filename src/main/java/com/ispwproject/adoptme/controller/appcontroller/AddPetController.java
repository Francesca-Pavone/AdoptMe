package com.ispwproject.adoptme.controller.appcontroller;

import com.ispwproject.adoptme.model.*;
import com.ispwproject.adoptme.engineering.bean.PetBean;
import com.ispwproject.adoptme.engineering.bean.ShelterBean;
import com.ispwproject.adoptme.engineering.dao.CatDAO;
import com.ispwproject.adoptme.engineering.dao.DogDAO;
import com.ispwproject.adoptme.engineering.observer.Observer;
import com.ispwproject.adoptme.engineering.observer.concretesubjects.ShelterPetsList;

public class AddPetController {

    private final PetBean petBean;

    public AddPetController(PetBean petBean) {
        this.petBean = petBean;
    }

    public void addNewPet(ShelterBean shelterBean, Observer observer) throws Exception {
        int petId;

        ShelterModel shelter = new ShelterModel(shelterBean.getShelterId());
        ShelterPetsList shelterPetsList = new ShelterPetsList(observer, shelter);

        if (petBean.getType() == 0) {
            PetCompatibility petCompatibility = setCompatibility();

            DogModel dogModel = new DogModel(petBean.getName(), petBean.getPetImage(), petCompatibility, petBean.isDogEducation(), petBean.getSize(), shelter);
            setCommonInfo(dogModel);

            petId = DogDAO.saveDog(dogModel);
            dogModel.setPetId(petId);
            shelterPetsList.addPet(dogModel);

        } else if (petBean.getType() == 1) {
            PetCompatibility petCompatibility = setCompatibility();

            CatModel catModel = new CatModel(petBean.getName(), petBean.getPetImage(), petBean.isTestFiv(), petBean.isTestFelv(), petCompatibility, shelter);
            setCommonInfo(catModel);

            petId = CatDAO.saveCat(catModel);
            catModel.setPetId(petId);
            shelterPetsList.addPet(catModel);
        }
    }

    private PetCompatibility setCompatibility() {
        PetCompatibility petCompatibility = new PetCompatibility(petBean.isMaleDog(), petBean.isFemaleDog(), petBean.isMaleCat(), petBean.isFemaleCat(), petBean.isChildren(), petBean.isElders(), petBean.isFirstExperience());
        petCompatibility.setApartmentNoGarden(petBean.isApartmentNoGarden());
        petCompatibility.setApartmentNoTerrace(petBean.isApartmentNoTerrace());
        petCompatibility.setSleepOutside(petBean.isSleepOutside());
        petCompatibility.setHoursAlone(petBean.getHoursAlone());
        return petCompatibility;
    }

    private void setCommonInfo(PetModel petModel) {
        petModel.setYearOfBirth(petBean.getYearOfBirth());
        petModel.setMonthOfBirth(petBean.getMonthOfBirth());
        petModel.setDayOfBirth(petBean.getDayOfBirth());
        petModel.setGender(petBean.getGender());
        petModel.setCoatLenght(petBean.getCoatLenght());
        petModel.setVaccinated(petBean.isVaccinated());
        petModel.setMicrochipped(petBean.isMicrochipped());
        petModel.setDewormed(petBean.isDewormed());
        petModel.setSterilized(petBean.isSterilized());
        petModel.setDisability(petBean.isDisability());
        petModel.setDisabilityType(petBean.getDisabilityType());
    }

}
