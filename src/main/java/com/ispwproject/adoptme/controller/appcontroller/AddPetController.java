package com.ispwproject.adoptme.controller.appcontroller;

import com.ispwproject.adoptme.engineering.exception.PetDateOfBirthException;
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

    public AddPetController(PetBean petBean) {
        this.petBean = petBean;
    }

    public int addNewPet(Observer observer) throws PetDateOfBirthException {
        int petId = -1;

        ShelterModel shelter = new ShelterModel(petBean.getShelterId());
        ShelterPetsList shelterPetsList = new ShelterPetsList(observer, shelter);

        if (petBean.getType() == 0) {
            PetCompatibility petCompatibility = setCompatibility();

            DogModel dogModel = new DogModel(petBean.getName(), petBean.getPetImage(), petCompatibility, petBean.isDogEducation(), petBean.getSize());
            setCommonInfo(dogModel);

            try {
                petId = DogDAO.saveDog(dogModel);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            dogModel.setPetId(petId);
            shelterPetsList.addPet(dogModel);

        } else if (petBean.getType() == 1) {
            PetCompatibility petCompatibility = setCompatibility();

            CatModel catModel = new CatModel(petBean.getName(), petBean.getPetImage(), petBean.isTestFiv(), petBean.isTestFelv(), petCompatibility);
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
        PetCompatibility petCompatibility = new PetCompatibility(petBean.isMaleDog(), petBean.isFemaleDog(), petBean.isMaleCat(), petBean.isFemaleCat(), petBean.isChildren(), petBean.isElders(), petBean.isFirstExperience());
        petCompatibility.setSleepOutside(petBean.isSleepOutside());
        petCompatibility.setHoursAlone(petBean.getHoursAlone());
        return petCompatibility;
    }

    private void setCommonInfo(PetModel petModel) throws PetDateOfBirthException {
        if (LocalDate.now().getYear() < petBean.getYearOfBirth())
            throw new PetDateOfBirthException("you have inserted a future year");
        else if (LocalDate.now().getYear() == petBean.getYearOfBirth() && LocalDate.now().getMonthValue() < petBean.getMonthOfBirth())
            throw new PetDateOfBirthException("you have insert a future month");
        else if (petBean.getDayOfBirth() != 0 && LocalDate.now().isBefore(LocalDate.of(petBean.getYearOfBirth(), petBean.getMonthOfBirth(), petBean.getDayOfBirth()))) {
            throw new PetDateOfBirthException("You have inserted a future date");
        }
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
