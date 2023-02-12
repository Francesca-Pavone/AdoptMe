package com.ispwproject.adoptme.controller.appcontroller;

import com.ispwproject.adoptme.engineering.bean.PetInformationBean;
import com.ispwproject.adoptme.engineering.exception.PetDateOfBirthException;
import com.ispwproject.adoptme.engineering.utils.SetPetInfoSupport;
import com.ispwproject.adoptme.model.*;
import com.ispwproject.adoptme.engineering.bean.PetBean;
import com.ispwproject.adoptme.engineering.dao.CatDAO;
import com.ispwproject.adoptme.engineering.dao.DogDAO;
import com.ispwproject.adoptme.engineering.observer.Observer;
import com.ispwproject.adoptme.engineering.observer.concretesubjects.ShelterPetsList;

import java.sql.SQLException;
import java.time.LocalDate;

public class AddPetController {

    private final PetBean petBean;
    private final PetInformationBean petInformationBean;

    public AddPetController(PetBean petBean, PetInformationBean petInformationBean) {
        this.petBean = petBean;
        this.petInformationBean = petInformationBean;
    }

    public int addNewPet(Observer observer) throws PetDateOfBirthException {
        int petId = -1;

        ShelterModel shelter = new ShelterModel(petBean.getPetBeanShelter());
        ShelterPetsList shelterPetsList = new ShelterPetsList(observer, shelter);

        if (petBean.getPetBeanType() == 0) {
            PetCompatibility petCompatibility = setCompatibility();

            DogModel dogModel = new DogModel(petBean.getPetBeanName(), petBean.getPetBeanImage(), petCompatibility, petInformationBean.isDogEducationBean(), petInformationBean.getSizeBean());
            setCommonInfo(dogModel);

            try {
                petId = DogDAO.saveDog(dogModel);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            dogModel.setPetId(petId);
            shelterPetsList.addPet(dogModel);

        } else if (petBean.getPetBeanType() == 1) {
            PetCompatibility petCompatibility = setCompatibility();

            CatModel catModel = new CatModel(petBean.getPetBeanName(), petBean.getPetBeanImage(), petInformationBean.isTestFivBean(), petInformationBean.isTestFelvBean(), petCompatibility);
            setCommonInfo(catModel);

            try {
                petId = CatDAO.saveCat(catModel);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            catModel.setPetId(petId);
            shelterPetsList.addPet(catModel);
        }
        return petId;
    }

    private PetCompatibility setCompatibility() {
        PetCompatibility petCompatibility = new PetCompatibility(petInformationBean.isMaleDogBean(), petInformationBean.isFemaleDogBean(), petInformationBean.isMaleCatBean(), petInformationBean.isFemaleCatBean(), petInformationBean.isChildrenBean(), petInformationBean.isEldersBean(), petInformationBean.isFirstExperienceBean());
        petCompatibility.setSleepOutside(petInformationBean.isSleepOutsideBean());
        petCompatibility.setHoursAlone(petInformationBean.getHoursAloneBean());
        return petCompatibility;
    }

    private void setCommonInfo(PetModel petModel) throws PetDateOfBirthException {
        if (LocalDate.now().getYear() < petBean.getPetBeanBirthYear())
            throw new PetDateOfBirthException("you have inserted a future year");
        else if (LocalDate.now().getYear() == petBean.getPetBeanBirthYear() && LocalDate.now().getMonthValue() < petBean.getPetBeanBirthMonth())
            throw new PetDateOfBirthException("you have insert a future month");
        else if (petBean.getPetBeanBirthDay() != 0 && LocalDate.now().isBefore(LocalDate.of(petBean.getPetBeanBirthYear(), petBean.getPetBeanBirthMonth(), petBean.getPetBeanBirthDay()))) {
            throw new PetDateOfBirthException("You have inserted a future date");
        }
        SetPetInfoSupport.setPetModel(petModel, petBean, petInformationBean);
    }



}
