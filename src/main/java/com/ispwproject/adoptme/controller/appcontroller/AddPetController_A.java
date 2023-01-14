package com.ispwproject.adoptme.controller.appcontroller;

import com.ispwproject.adoptme.model.CatModel;
import com.ispwproject.adoptme.model.DogModel;
import com.ispwproject.adoptme.model.Shelter;
import com.ispwproject.adoptme.utils.bean.PetBean;
import com.ispwproject.adoptme.utils.dao.PetDAO;

public class AddPetController_A {

    private PetBean petBean;

    public AddPetController_A(PetBean petBean) {
        this.petBean = petBean;
    }

    public void addPet() throws Exception {
        PetDAO petDAO = new PetDAO();

        //Factory factory = new Factory();
        Shelter shelter = new Shelter(1);

        if (petBean.getType() == 0) {
            DogModel dogModel = new DogModel(petBean.getName(), petBean.getPetImage(), petBean.getYearOfBirth(), petBean.getMonthOfBirth(), petBean.getDayOfBirth(), petBean.getGender(), petBean.getCoatLenght(), petBean.isVaccinated(), petBean.isMicrochipped(), petBean.isDewormed(), petBean.isSterilized(), petBean.isDisability(), petBean.getDisabilityType(), petBean.isMaleDog(), petBean.isFemaleDog(), petBean.isMaleCat(), petBean.isFemaleCat(), petBean.isChildren(), petBean.isElders(), petBean.isApartmentNoGarden(), petBean.isApartmentNoTerrace(), petBean.isSleepOutside(), petBean.isFirstExperience(), petBean.getHoursAlone(), petBean.isDogEducation(), petBean.getSize(), shelter);
            petDAO.savePet(dogModel);
        } else if (petBean.getType() == 1) {
            CatModel catModel = new CatModel(petBean.getName(), petBean.getPetImage(), petBean.getYearOfBirth(), petBean.getMonthOfBirth(), petBean.getDayOfBirth(), petBean.getGender(), petBean.getCoatLenght(), petBean.isVaccinated(), petBean.isMicrochipped(), petBean.isDewormed(), petBean.isSterilized(), petBean.isDisability(), petBean.getDisabilityType(), petBean.isMaleDog(), petBean.isFemaleDog(), petBean.isMaleCat(), petBean.isFemaleCat(), petBean.isChildren(), petBean.isElders(), petBean.isApartmentNoGarden(), petBean.isApartmentNoTerrace(), petBean.isSleepOutside(), petBean.isFirstExperience(), petBean.getHoursAlone(), petBean.isTestFiv(), petBean.isTestFelv(), shelter);
            petDAO.savePet(catModel);
        }

    }
}
