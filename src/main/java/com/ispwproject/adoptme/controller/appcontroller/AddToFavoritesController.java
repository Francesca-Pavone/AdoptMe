package com.ispwproject.adoptme.controller.appcontroller;

import com.ispwproject.adoptme.engineering.bean.PetBean;
import com.ispwproject.adoptme.engineering.bean.PetInformationBean;
import com.ispwproject.adoptme.engineering.bean.UserBean;
import com.ispwproject.adoptme.engineering.dao.FavoritesDAO;
import com.ispwproject.adoptme.engineering.dao.ShelterDAO;
import com.ispwproject.adoptme.engineering.observer.Observer;
import com.ispwproject.adoptme.engineering.observer.concretesubjects.UserFavoritesPetsList;
import com.ispwproject.adoptme.engineering.utils.SetPetInfoSupport;
import com.ispwproject.adoptme.model.*;

public class AddToFavoritesController {

    private final PetBean petBean;
    private final PetInformationBean petInformationBean;

    public AddToFavoritesController(PetBean petBean, PetInformationBean petInformationBean) {
        this.petBean = petBean;
        this.petInformationBean = petInformationBean;
    }

    public void addPet(UserBean userBean, Observer observer, Object object) {
        try {
            UserModel userModel = new UserModel(userBean.getUserId(), userBean.getProfileImg(), userBean.getName(), userBean.getSurname());
            UserFavoritesPetsList userFavoritesPetsList = new UserFavoritesPetsList(observer, userModel);

            try {FavoritesDAO.addFavorite(userModel.getId(), petBean.getPetId(), petBean.getShelterId());} catch (Exception ignored) {
                //exception ignored
            }
            ShelterModel shelter = ShelterDAO.retrieveShelterById(petBean.getShelterId());

            if (petBean.getType() == 0) {
                PetCompatibility petCompatibility = setCompatibility();
                DogModel dogModel = new DogModel(petBean.getName(), petBean.getPetImage(), petCompatibility, petInformationBean.isDogEducation(), petInformationBean.getSize());
                dogModel.setPetId(petBean.getPetId());
                setCommonInfo(dogModel);
                userFavoritesPetsList.addPet(dogModel, shelter.getId());
                userFavoritesPetsList.notifyObservers(dogModel, object);
            } else if (petBean.getType() == 1) {
                PetCompatibility petCompatibility = setCompatibility();
                CatModel catModel = new CatModel(petBean.getName(), petBean.getPetImage(), petInformationBean.isTestFiv(), petInformationBean.isTestFelv(), petCompatibility);
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
        PetCompatibility petCompatibility = new PetCompatibility(petInformationBean.isMaleDog(), petInformationBean.isFemaleDog(), petInformationBean.isMaleCat(), petInformationBean.isFemaleCat(), petInformationBean.isChildren(), petInformationBean.isElders(), petInformationBean.isFirstExperience());
        petCompatibility.setSleepOutside(petInformationBean.isSleepOutside());
        petCompatibility.setHoursAlone(petInformationBean.getHoursAlone());
        return petCompatibility;
    }

    private void setCommonInfo(PetModel petModel) {
        SetPetInfoSupport.setPetModel(petModel, petBean, petInformationBean);
    }

    public void removePet(UserBean userBean, Observer observer, Object object){
        try {
            UserModel userModel = new UserModel(userBean.getUserId(), userBean.getProfileImg(), userBean.getName(), userBean.getSurname());
            UserFavoritesPetsList userFavoritesPetsList = new UserFavoritesPetsList(observer, userModel);

            FavoritesDAO.removeFavorite(userModel.getId(), petBean.getPetId(), petBean.getShelterId());
            ShelterModel shelter = ShelterDAO.retrieveShelterById(petBean.getShelterId());


            if (petBean.getType() == 0) {
                PetCompatibility petCompatibility = setCompatibility();
                DogModel dogModel = new DogModel(petBean.getName(), petBean.getPetImage(), petCompatibility, petInformationBean.isDogEducation(), petInformationBean.getSize());
                dogModel.setPetId(petBean.getPetId());
                setCommonInfo(dogModel);
                userFavoritesPetsList.removePet(dogModel, shelter.getId());
                userFavoritesPetsList.notifyObservers(dogModel,object);
            } else if (petBean.getType() == 1) {
                PetCompatibility petCompatibility = setCompatibility();
                CatModel catModel = new CatModel(petBean.getName(), petBean.getPetImage(), petInformationBean.isTestFiv(), petInformationBean.isTestFelv(), petCompatibility);
                catModel.setPetId(petBean.getPetId());
                setCommonInfo(catModel);
                userFavoritesPetsList.removePet(catModel, shelter.getId());
                userFavoritesPetsList.notifyObservers(catModel,object);
            }
        } catch (Exception ignored) {
            //exception ignored
        }
    }
}
