package com.ispwproject.adoptme.controller.appcontroller;

import com.ispwproject.adoptme.model.CatModel;
import com.ispwproject.adoptme.model.DogModel;
import com.ispwproject.adoptme.model.ShelterModel;
import com.ispwproject.adoptme.utils.bean.PetBean;
import com.ispwproject.adoptme.utils.bean.ShelterBean;
import com.ispwproject.adoptme.utils.dao.CatDAO;
import com.ispwproject.adoptme.utils.dao.DogDAO;
import com.ispwproject.adoptme.utils.dao.PetDAO;
import com.ispwproject.adoptme.utils.observer.Observer;
import com.ispwproject.adoptme.utils.observer.concreteSubjects.ShelterPetsList;

public class AddPetController implements Observer {

    private final PetBean petBean;

    public AddPetController(PetBean petBean) {
        this.petBean = petBean;
    }

    public void addNewPet(ShelterBean shelterBean) throws Exception {

        ShelterModel shelter = new ShelterModel(shelterBean);
        if (petBean.getType() == 0) {
            DogModel dogModel = new DogModel(petBean.getName(), petBean.getPetImage(), petBean.getYearOfBirth(), petBean.getMonthOfBirth(), petBean.getDayOfBirth(), petBean.getGender(), petBean.getCoatLenght(), petBean.isVaccinated(), petBean.isMicrochipped(), petBean.isDewormed(), petBean.isSterilized(), petBean.isDisability(), petBean.getDisabilityType(), petBean.isMaleDog(), petBean.isFemaleDog(), petBean.isMaleCat(), petBean.isFemaleCat(), petBean.isChildren(), petBean.isElders(), petBean.isApartmentNoGarden(), petBean.isApartmentNoTerrace(), petBean.isSleepOutside(), petBean.isFirstExperience(), petBean.getHoursAlone(), petBean.isDogEducation(), petBean.getSize(), shelter);
            DogDAO.saveDog(dogModel);
        } else if (petBean.getType() == 1) {
            CatModel catModel = new CatModel(petBean.getName(), petBean.getPetImage(), petBean.getYearOfBirth(), petBean.getMonthOfBirth(), petBean.getDayOfBirth(), petBean.getGender(), petBean.getCoatLenght(), petBean.isVaccinated(), petBean.isMicrochipped(), petBean.isDewormed(), petBean.isSterilized(), petBean.isDisability(), petBean.getDisabilityType(), petBean.isMaleDog(), petBean.isFemaleDog(), petBean.isMaleCat(), petBean.isFemaleCat(), petBean.isChildren(), petBean.isElders(), petBean.isApartmentNoGarden(), petBean.isApartmentNoTerrace(), petBean.isSleepOutside(), petBean.isFirstExperience(), petBean.getHoursAlone(), petBean.isTestFiv(), petBean.isTestFelv(), shelter);
            CatDAO.saveCat(catModel);
        }
    }

    @Override
    public void update(Object object) {

    }
}
