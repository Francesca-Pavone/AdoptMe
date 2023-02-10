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
                DogModel dogModel = new DogModel(petBean.getName(), petBean.getPetImage(), petCompatibility, petBean.getPetInformationBean().isDogEducation(), petBean.getPetInformationBean().getSize());
                dogModel.setPetId(petBean.getPetId());
                setCommonInfo(dogModel);
                userFavoritesPetsList.addPet(dogModel, shelter.getId());
                userFavoritesPetsList.notifyObservers(dogModel, object);
            } else if (petBean.getType() == 1) {
                PetCompatibility petCompatibility = setCompatibility();
                CatModel catModel = new CatModel(petBean.getName(), petBean.getPetImage(), petBean.getPetInformationBean().isTestFiv(), petBean.getPetInformationBean().isTestFelv(), petCompatibility);
                catModel.setPetId(petBean.getPetId());
                setCommonInfo(catModel);
                userFavoritesPetsList.addPet(catModel, shelter.getId());
                userFavoritesPetsList.notifyObservers(catModel, object);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private PetCompatibility setCompatibility() {
        PetCompatibility petCompatibility = new PetCompatibility(petBean.getPetInformationBean().isMaleDog(), petBean.getPetInformationBean().isFemaleDog(), petBean.getPetInformationBean().isMaleCat(), petBean.getPetInformationBean().isFemaleCat(), petBean.getPetInformationBean().isChildren(), petBean.getPetInformationBean().isElders(), petBean.getPetInformationBean().isFirstExperience());
        petCompatibility.setSleepOutside(petBean.getPetInformationBean().isSleepOutside());
        petCompatibility.setHoursAlone(petBean.getPetInformationBean().getHoursAlone());
        return petCompatibility;
    }

    private void setCommonInfo(PetModel petModel) {
        petModel.setYearOfBirth(petBean.getYearOfBirth());
        petModel.setMonthOfBirth(petBean.getMonthOfBirth());
        petModel.setDayOfBirth(petBean.getDayOfBirth());
        petModel.setGender(petBean.getGender());
        petModel.setCoatLenght(petBean.getPetInformationBean().getCoatLenght());
        petModel.setVaccinated(petBean.getPetInformationBean().isVaccinated());
        petModel.setMicrochipped(petBean.getPetInformationBean().isMicrochipped());
        petModel.setDewormed(petBean.getPetInformationBean().isDewormed());
        petModel.setSterilized(petBean.getPetInformationBean().isSterilized());
        petModel.setDisability(petBean.getPetInformationBean().isDisability());
        petModel.setDisabilityType(petBean.getPetInformationBean().getDisabilityType());
    }

    public void removePet(UserBean userBean, Observer observer, Object object){
        try {
            UserModel userModel = new UserModel(userBean.getUserId(), userBean.getProfileImg(), userBean.getName(), userBean.getSurname());
            UserFavoritesPetsList userFavoritesPetsList = new UserFavoritesPetsList(observer, userModel);

            FavoritesDAO.removeFavorite(userModel.getId(), petBean.getPetId(), petBean.getShelterId());
            ShelterModel shelter = ShelterDAO.retrieveShelterById(petBean.getShelterId());


            if (petBean.getType() == 0) {
                PetCompatibility petCompatibility = setCompatibility();
                DogModel dogModel = new DogModel(petBean.getName(), petBean.getPetImage(), petCompatibility, petBean.getPetInformationBean().isDogEducation(), petBean.getPetInformationBean().getSize());
                dogModel.setPetId(petBean.getPetId());
                setCommonInfo(dogModel);
                userFavoritesPetsList.removePet(dogModel, shelter.getId());
                userFavoritesPetsList.notifyObservers(dogModel,object);
            } else if (petBean.getType() == 1) {
                PetCompatibility petCompatibility = setCompatibility();
                CatModel catModel = new CatModel(petBean.getName(), petBean.getPetImage(), petBean.getPetInformationBean().isTestFiv(), petBean.getPetInformationBean().isTestFelv(), petCompatibility);
                catModel.setPetId(petBean.getPetId());
                setCommonInfo(catModel);
                userFavoritesPetsList.removePet(catModel, shelter.getId());
                userFavoritesPetsList.notifyObservers(catModel,object);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
