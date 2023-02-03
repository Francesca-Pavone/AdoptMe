package com.ispwproject.adoptme.controller.appcontroller;

import com.ispwproject.adoptme.engineering.observer.Observer;
import com.ispwproject.adoptme.model.PetCompatibility;
import com.ispwproject.adoptme.model.PetModel;
import com.ispwproject.adoptme.model.ShelterModel;
import com.ispwproject.adoptme.engineering.bean.PetBean;
import com.ispwproject.adoptme.engineering.bean.ShelterBean;
import com.ispwproject.adoptme.engineering.dao.PetDAO;
import com.ispwproject.adoptme.engineering.observer.concretesubjects.ShelterPetsList;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShelterPageController {
    private final ShelterModel shelterModel;
    private ShelterPetsList shelterPetsList;

    public ShelterPageController(ShelterBean shelterBean) {
        this.shelterModel = new ShelterModel(shelterBean.getShelterId());
    }

    public List<PetBean> getPetList(Observer observer) {
        try {
            shelterPetsList = PetDAO.retrievePetByShelterId(shelterModel,observer);

        } catch (SQLException se) {
            // Errore durante l'apertura della connessione
            se.printStackTrace();
        } catch (Exception e) {
            // Errore nel loading del driver o possibilmente nell'accesso al filesystem
            e.printStackTrace();
        }

        List<PetBean> petBeanList = new ArrayList<>();

        for (PetModel petModel : shelterPetsList.getPetList()) {
            PetBean petBean = new PetBean();
            petBean.setPetId(petModel.getPetId());
            petBean.setShelterId(petModel.getShelter().getId());
            petBean.setPetImage(petModel.getPetImage());
            petBean.setName(petModel.getName());
            petBean.setType(petModel.getType());
            petBean.setYearOfBirth(petModel.getYearOfBirth());
            petBean.setMonthOfBirth(petModel.getMonthOfBirth());
            petBean.setDayOfBirth(petModel.getDayOfBirth());
            petBean.setAge(petModel.getAge());
            petBean.setGender(petModel.getGender());
            petBean.setCoatLenght(petModel.getCoatLenght());
            petBean.setVaccinated(petModel.isVaccinated());
            petBean.setMicrochipped(petModel.isMicrochipped());
            petBean.setDewormed(petModel.isDewormed());
            petBean.setSterilized(petModel.isSterilized());
            petBean.setDisability(petModel.isDisability());
            petBean.setDisabilityType(petModel.getDisabilityType());

            PetCompatibility petCompatibility = petModel.getPetCompatibility();
            petBean.setMaleDog(petCompatibility.isMaleDog());
            petBean.setFemaleDog(petCompatibility.isFemaleDog());
            petBean.setMaleCat(petCompatibility.isMaleCat());
            petBean.setFemaleCat(petCompatibility.isFemaleCat());
            petBean.setChildren(petCompatibility.isChildren());
            petBean.setElders(petCompatibility.isElders());
            petBean.setApartmentNoGarden(petCompatibility.isApartmentNoGarden());
            petBean.setApartmentNoTerrace(petCompatibility.isApartmentNoTerrace());
            petBean.setSleepOutside(petCompatibility.isSleepOutside());
            petBean.setFirstExperience(petCompatibility.isFirstExperience());
            petBean.setHoursAlone(petCompatibility.getHoursAlone());
            petBeanList.add(petBean);
        }
        return petBeanList;
    }

}
