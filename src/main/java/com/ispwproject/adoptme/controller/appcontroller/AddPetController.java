package com.ispwproject.adoptme.controller.appcontroller;

import com.ispwproject.adoptme.model.CatModel;
import com.ispwproject.adoptme.model.DogModel;
import com.ispwproject.adoptme.model.ShelterModel;
import com.ispwproject.adoptme.engineering.bean.PetBean;
import com.ispwproject.adoptme.engineering.bean.ShelterBean;
import com.ispwproject.adoptme.engineering.dao.CatDAO;
import com.ispwproject.adoptme.engineering.dao.DogDAO;
import com.ispwproject.adoptme.engineering.observer.Observer;
import com.ispwproject.adoptme.engineering.observer.concreteSubjects.ShelterPetsList;

public class AddPetController {

    private final PetBean petBean;

    public AddPetController(PetBean petBean) {
        this.petBean = petBean;
    }

    public void addNewPet(ShelterBean shelterBean, Observer observer) throws Exception {
        int petId = -1;

        ShelterModel shelter = new ShelterModel(shelterBean);
        ShelterPetsList shelterPetsList = new ShelterPetsList(observer, shelter);

        if (petBean.getType() == 0) {
            DogModel dogModel = new DogModel(petBean.getName(), petBean.getPetImage(), petBean.getYearOfBirth(), petBean.getMonthOfBirth(), petBean.getDayOfBirth(), petBean.getGender(), petBean.getCoatLenght(), petBean.isVaccinated(), petBean.isMicrochipped(), petBean.isDewormed(), petBean.isSterilized(), petBean.isDisability(), petBean.getDisabilityType(), petBean.isMaleDog(), petBean.isFemaleDog(), petBean.isMaleCat(), petBean.isFemaleCat(), petBean.isChildren(), petBean.isElders(), petBean.isApartmentNoGarden(), petBean.isApartmentNoTerrace(), petBean.isSleepOutside(), petBean.isFirstExperience(), petBean.getHoursAlone(), petBean.isDogEducation(), petBean.getSize(), shelter);
            petId = DogDAO.saveDog(dogModel);
            dogModel.setPetId(petId);
            shelterPetsList.addPet(dogModel);
        } else if (petBean.getType() == 1) {
            CatModel catModel = new CatModel(petBean.getName(), petBean.getPetImage(), petBean.getYearOfBirth(), petBean.getMonthOfBirth(), petBean.getDayOfBirth(), petBean.getGender(), petBean.getCoatLenght(), petBean.isVaccinated(), petBean.isMicrochipped(), petBean.isDewormed(), petBean.isSterilized(), petBean.isDisability(), petBean.getDisabilityType(), petBean.isMaleDog(), petBean.isFemaleDog(), petBean.isMaleCat(), petBean.isFemaleCat(), petBean.isChildren(), petBean.isElders(), petBean.isApartmentNoGarden(), petBean.isApartmentNoTerrace(), petBean.isSleepOutside(), petBean.isFirstExperience(), petBean.getHoursAlone(), petBean.isTestFiv(), petBean.isTestFelv(), shelter);
            petId = CatDAO.saveCat(catModel);
            catModel.setPetId(petId);
            shelterPetsList.addPet(catModel);
        }
    }

}
