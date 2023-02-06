package com.ispwproject.adoptme.controller.appcontroller;

import com.ispwproject.adoptme.engineering.bean.PetBean;
import com.ispwproject.adoptme.engineering.bean.UserBean;
import com.ispwproject.adoptme.engineering.dao.FavoritesDAO;
import com.ispwproject.adoptme.engineering.dao.ShelterDAO;
import com.ispwproject.adoptme.engineering.observer.Observer;
import com.ispwproject.adoptme.engineering.observer.concretesubjects.UserFavoritesPetsList;
import com.ispwproject.adoptme.model.*;

public class AddToFavoritesController {

    private final PetBean petBean;

    public AddToFavoritesController(PetBean petBean) {
        this.petBean = petBean;
    }

    public void addPet(UserBean userBean, Observer observer, Object object) {
        try {
            UserModel userModel = new UserModel(userBean.getUserId(), userBean.getProfileImg(), userBean.getName(), userBean.getSurname());
            UserFavoritesPetsList userFavoritesPetsList = new UserFavoritesPetsList(observer, userModel);

            FavoritesDAO.addFavorite(userModel.getId(), petBean.getPetId(), petBean.getShelterId());
            ShelterModel shelter = ShelterDAO.retrieveShelterById(petBean.getShelterId());

            if (petBean.getType() == 0) {
                PetCompatibility petCompatibility = setCompatibility();
                DogModel dogModel = new DogModel(petBean.getName(), petBean.getPetImage(), petCompatibility, petBean.isDogEducation(), petBean.getSize());
                dogModel.setPetId(petBean.getPetId());
                setCommonInfo(dogModel);
                userFavoritesPetsList.addPet(dogModel);
                userFavoritesPetsList.notifyObservers(dogModel, object);
            } else if (petBean.getType() == 1) {
                PetCompatibility petCompatibility = setCompatibility();
                CatModel catModel = new CatModel(petBean.getName(), petBean.getPetImage(), petBean.isTestFiv(), petBean.isTestFelv(), petCompatibility);
                catModel.setPetId(petBean.getPetId());
                setCommonInfo(catModel);
                userFavoritesPetsList.addPet(catModel);
                userFavoritesPetsList.notifyObservers(catModel, object);
            }
        } catch (Exception e) {
            e.printStackTrace();
            //todo fare exception
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

    public void removePet(UserBean userBean, Observer observer, Object object){
        try {
            UserModel userModel = new UserModel(userBean.getUserId(), userBean.getProfileImg(), userBean.getName(), userBean.getSurname());
            UserFavoritesPetsList userFavoritesPetsList = new UserFavoritesPetsList(observer, userModel);

            FavoritesDAO.removeFavorite(userModel.getId(), petBean.getPetId(), petBean.getShelterId());

            if (petBean.getType() == 0) {
                PetCompatibility petCompatibility = setCompatibility();
                DogModel dogModel = new DogModel(petBean.getName(), petBean.getPetImage(), petCompatibility, petBean.isDogEducation(), petBean.getSize());
                dogModel.setPetId(petBean.getPetId());
                setCommonInfo(dogModel);
                userFavoritesPetsList.removePet(dogModel);
                userFavoritesPetsList.notifyObservers(dogModel,object);
            } else if (petBean.getType() == 1) {
                PetCompatibility petCompatibility = setCompatibility();
                CatModel catModel = new CatModel(petBean.getName(), petBean.getPetImage(), petBean.isTestFiv(), petBean.isTestFelv(), petCompatibility);
                catModel.setPetId(petBean.getPetId());
                setCommonInfo(catModel);
                userFavoritesPetsList.removePet(catModel);
                userFavoritesPetsList.notifyObservers(catModel,object);
            }
        } catch (Exception e) {
            e.printStackTrace();
            //todo fare exception
        }
    }

}
