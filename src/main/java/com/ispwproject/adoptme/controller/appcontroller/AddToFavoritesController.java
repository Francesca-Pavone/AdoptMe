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

            FavoritesDAO.addFavorite(userModel.getId(), petBean.getPetBeanId(), petBean.getPetBeanShelter());
            ShelterModel shelter = ShelterDAO.retrieveShelterById(petBean.getPetBeanShelter());

            PetModel pet = null;
            if (petBean.getPetBeanType() == 0) {
                PetCompatibility petCompatibility = setCompatibility();
                DogModel dogModel = new DogModel(petBean.getPetBeanName(), petBean.getPetBeanImage(), petCompatibility, petInformationBean.isDogEducationBean(), petInformationBean.getSizeBean());
                dogModel.setPetId(petBean.getPetBeanId());
                setCommonInfo(dogModel);
                pet = dogModel;
            } else if (petBean.getPetBeanType() == 1) {
                PetCompatibility petCompatibility = setCompatibility();
                CatModel catModel = new CatModel(petBean.getPetBeanName(), petBean.getPetBeanImage(), petInformationBean.isTestFivBean(), petInformationBean.isTestFelvBean(), petCompatibility);
                catModel.setPetId(petBean.getPetBeanId());
                setCommonInfo(catModel);
                pet = catModel;
            }
            userFavoritesPetsList.addPet(pet, shelter.getId());
            userFavoritesPetsList.notifyObservers(pet, object);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private PetCompatibility setCompatibility() {
        PetCompatibility petCompatibility = new PetCompatibility(petInformationBean.isMaleDogBean(), petInformationBean.isFemaleDogBean(), petInformationBean.isMaleCatBean(), petInformationBean.isFemaleCatBean(), petInformationBean.isChildrenBean(), petInformationBean.isEldersBean(), petInformationBean.isFirstExperienceBean());
        petCompatibility.setSleepOutside(petInformationBean.isSleepOutsideBean());
        petCompatibility.setHoursAlone(petInformationBean.getHoursAloneBean());
        return petCompatibility;
    }

    private void setCommonInfo(PetModel petModel) {
        SetPetInfoSupport.setPetModel(petModel, petBean, petInformationBean);
    }

    public void removePet(UserBean userBean, Observer observer, Object object){
        try {
            UserModel userModel = new UserModel(userBean.getUserId(), userBean.getProfileImg(), userBean.getName(), userBean.getSurname());
            UserFavoritesPetsList userFavoritesPetsList = new UserFavoritesPetsList(observer, userModel);

            FavoritesDAO.removeFavorite(userModel.getId(), petBean.getPetBeanId(), petBean.getPetBeanShelter());
            ShelterModel shelter = ShelterDAO.retrieveShelterById(petBean.getPetBeanShelter());


            if (petBean.getPetBeanType() == 0) {
                PetCompatibility petCompatibility = setCompatibility();
                DogModel dogModel = new DogModel(petBean.getPetBeanName(), petBean.getPetBeanImage(), petCompatibility, petInformationBean.isDogEducationBean(), petInformationBean.getSizeBean());
                dogModel.setPetId(petBean.getPetBeanId());
                setCommonInfo(dogModel);
                userFavoritesPetsList.removePet(dogModel, shelter.getId());
                userFavoritesPetsList.notifyObservers(dogModel,object);
            } else if (petBean.getPetBeanType() == 1) {
                PetCompatibility petCompatibility = setCompatibility();
                CatModel catModel = new CatModel(petBean.getPetBeanName(), petBean.getPetBeanImage(), petInformationBean.isTestFivBean(), petInformationBean.isTestFelvBean(), petCompatibility);
                catModel.setPetId(petBean.getPetBeanId());
                setCommonInfo(catModel);
                userFavoritesPetsList.removePet(catModel, shelter.getId());
                userFavoritesPetsList.notifyObservers(catModel,object);
            }
        } catch (Exception ignored) {
            //exception ignored
        }
    }
}
