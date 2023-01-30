package com.ispwproject.adoptme.controller.appcontroller;

import com.ispwproject.adoptme.controller.graficcontroller.gui.GUIPetInfoController;
import com.ispwproject.adoptme.engineering.bean.PetBean;
import com.ispwproject.adoptme.engineering.bean.UserBean;
import com.ispwproject.adoptme.engineering.dao.DogDAO;
import com.ispwproject.adoptme.engineering.dao.FavoritesDAO;
import com.ispwproject.adoptme.engineering.dao.ShelterDAO;
import com.ispwproject.adoptme.engineering.observer.Observer;
import com.ispwproject.adoptme.engineering.observer.concreteSubjects.UserFavoritesPetsList;
import com.ispwproject.adoptme.engineering.session.Session;
import com.ispwproject.adoptme.model.CatModel;
import com.ispwproject.adoptme.model.DogModel;
import com.ispwproject.adoptme.model.ShelterModel;
import com.ispwproject.adoptme.model.UserModel;

public class AddToFavoritesController {

    private final PetBean petBean;

    public AddToFavoritesController(PetBean petBean) {
        this.petBean = petBean;
    }

    public void addPet(UserBean userBean, Observer observer) throws Exception {
        UserModel userModel = new UserModel(userBean);
        UserFavoritesPetsList userFavoritesPetsList = new UserFavoritesPetsList(observer, userModel);

        FavoritesDAO.addFavorite(userModel.getId(), petBean.getPetId(), petBean.getShelterId());
        ShelterModel shelter = ShelterDAO.retrieveShelterById(petBean.getShelterId());

        if (petBean.getType() == 0) {
            DogModel dogModel = new DogModel(petBean.getName(), petBean.getPetImage(), petBean.getYearOfBirth(), petBean.getMonthOfBirth(), petBean.getDayOfBirth(), petBean.getGender(), petBean.getCoatLenght(), petBean.isVaccinated(), petBean.isMicrochipped(), petBean.isDewormed(), petBean.isSterilized(), petBean.isDisability(), petBean.getDisabilityType(), petBean.isMaleDog(), petBean.isFemaleDog(), petBean.isMaleCat(), petBean.isFemaleCat(), petBean.isChildren(), petBean.isElders(), petBean.isApartmentNoGarden(), petBean.isApartmentNoTerrace(), petBean.isSleepOutside(), petBean.isFirstExperience(), petBean.getHoursAlone(), petBean.isDogEducation(), petBean.getSize(), shelter);
            userFavoritesPetsList.addPet(dogModel);
        } else if (petBean.getType() == 1) {
            CatModel catModel = new CatModel(petBean.getName(), petBean.getPetImage(), petBean.getYearOfBirth(), petBean.getMonthOfBirth(), petBean.getDayOfBirth(), petBean.getGender(), petBean.getCoatLenght(), petBean.isVaccinated(), petBean.isMicrochipped(), petBean.isDewormed(), petBean.isSterilized(), petBean.isDisability(), petBean.getDisabilityType(), petBean.isMaleDog(), petBean.isFemaleDog(), petBean.isMaleCat(), petBean.isFemaleCat(), petBean.isChildren(), petBean.isElders(), petBean.isApartmentNoGarden(), petBean.isApartmentNoTerrace(), petBean.isSleepOutside(), petBean.isFirstExperience(), petBean.getHoursAlone(), petBean.isTestFiv(), petBean.isTestFelv(), shelter);
            userFavoritesPetsList.addPet(catModel);
        }
    }

    public void removePet(UserBean userBean, Observer observer) throws Exception {
        UserModel userModel = new UserModel(userBean);
        UserFavoritesPetsList userFavoritesPetsList = new UserFavoritesPetsList(observer, userModel);

        FavoritesDAO.removeFavorite(userModel.getId(), petBean.getPetId(), petBean.getShelterId());
        ShelterModel shelter = ShelterDAO.retrieveShelterById(petBean.getShelterId());

        if (petBean.getType() == 0) {
            DogModel dogModel = new DogModel(petBean.getName(), petBean.getPetImage(), petBean.getYearOfBirth(), petBean.getMonthOfBirth(), petBean.getDayOfBirth(), petBean.getGender(), petBean.getCoatLenght(), petBean.isVaccinated(), petBean.isMicrochipped(), petBean.isDewormed(), petBean.isSterilized(), petBean.isDisability(), petBean.getDisabilityType(), petBean.isMaleDog(), petBean.isFemaleDog(), petBean.isMaleCat(), petBean.isFemaleCat(), petBean.isChildren(), petBean.isElders(), petBean.isApartmentNoGarden(), petBean.isApartmentNoTerrace(), petBean.isSleepOutside(), petBean.isFirstExperience(), petBean.getHoursAlone(), petBean.isDogEducation(), petBean.getSize(), shelter);
            userFavoritesPetsList.removePet(dogModel);
        } else if (petBean.getType() == 1) {
            CatModel catModel = new CatModel(petBean.getName(), petBean.getPetImage(), petBean.getYearOfBirth(), petBean.getMonthOfBirth(), petBean.getDayOfBirth(), petBean.getGender(), petBean.getCoatLenght(), petBean.isVaccinated(), petBean.isMicrochipped(), petBean.isDewormed(), petBean.isSterilized(), petBean.isDisability(), petBean.getDisabilityType(), petBean.isMaleDog(), petBean.isFemaleDog(), petBean.isMaleCat(), petBean.isFemaleCat(), petBean.isChildren(), petBean.isElders(), petBean.isApartmentNoGarden(), petBean.isApartmentNoTerrace(), petBean.isSleepOutside(), petBean.isFirstExperience(), petBean.getHoursAlone(), petBean.isTestFiv(), petBean.isTestFelv(), shelter);
            userFavoritesPetsList.removePet(catModel);
        }
    }
}
