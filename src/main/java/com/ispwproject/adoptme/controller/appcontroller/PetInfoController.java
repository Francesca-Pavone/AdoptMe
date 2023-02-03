package com.ispwproject.adoptme.controller.appcontroller;

import com.ispwproject.adoptme.engineering.dao.PetDAO;
import com.ispwproject.adoptme.engineering.observer.Observer;
import com.ispwproject.adoptme.engineering.observer.concreteSubjects.UserFavoritesPetsList;
import com.ispwproject.adoptme.engineering.session.Session;
import com.ispwproject.adoptme.model.*;
import com.ispwproject.adoptme.engineering.bean.PetBean;
import com.ispwproject.adoptme.engineering.bean.ShelterBean;
import com.ispwproject.adoptme.engineering.dao.CatDAO;
import com.ispwproject.adoptme.engineering.dao.DogDAO;
import com.ispwproject.adoptme.engineering.dao.ShelterDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PetInfoController {


    public ShelterBean getPetInfo(PetBean petBean) throws Exception {
        ShelterModel shelterModel = ShelterDAO.retrieveShelterById(petBean.getShelterId());

        if (petBean.getType() == 0) {
            DogModel dogModel = DogDAO.retrieveDogById(petBean.getPetId(), petBean.getShelterId());

            //vado a settare nel bean le nuove info del pet che mi servono
            setGeneralInfo(petBean, dogModel.getYearOfBirth(), dogModel.getMonthOfBirth(), dogModel.getDayOfBirth(), dogModel.getCoatLenght());
            setMedicalInfo(petBean, dogModel.isVaccinated(), dogModel.isMicrochipped(), dogModel.isDewormed(), dogModel.isSterilized(), dogModel.isDisability(), dogModel.getDisabilityType());
            setCompatibility(petBean, dogModel.getPetCompatibility());
            petBean.setDogEducation(dogModel.isProgramEducation());
            petBean.setSize(dogModel.getSize());

        }
        else {
            CatModel catModel = CatDAO.retrieveCatById(petBean.getPetId(), petBean.getShelterId());

            setGeneralInfo(petBean, catModel.getYearOfBirth(), catModel.getMonthOfBirth(), catModel.getDayOfBirth(), catModel.getCoatLenght());
            setMedicalInfo(petBean, catModel.isVaccinated(), catModel.isMicrochipped(), catModel.isDewormed(), catModel.isSterilized(), catModel.isDisability(), catModel.getDisabilityType());
            setCompatibility(petBean, catModel.getPetCompatibility());
            petBean.setTestFiv(catModel.isTestFiv());
            petBean.setTestFelv(catModel.isTestFelv());
        }
        return new ShelterBean(shelterModel);

    }

    public boolean checkFavorite(PetBean petBean, Observer observer) {
        UserFavoritesPetsList userFavoritesPetsList = new UserFavoritesPetsList(observer, new UserModel(Session.getCurrentSession().getUserBean()));
        try {
            userFavoritesPetsList = PetDAO.retrieveUserFavoritesPets(new UserModel(Session.getCurrentSession().getUserBean()), observer);
        } catch (SQLException se) {
            // Errore durante l'apertura della connessione
            se.printStackTrace();
        } catch (ClassNotFoundException driverEx) {
            // Errore nel loading del driver
            driverEx.printStackTrace();
        } catch (Exception e) {
            // Errore nel loading del driver o possibilmente nell'accesso al filesystem
            e.printStackTrace();
        }

        for (PetModel petModel : userFavoritesPetsList.getPetList()) {
            if (petModel.getPetId() == petBean.getPetId() && petModel.getShelter().getId() == petBean.getShelterId())
                return true;
        }
        return false;
    }

    private void setGeneralInfo(PetBean petBean, int yearOfBirth, int monthOfBirth, int dayOfBirth, int coatLenght) {
        petBean.setYearOfBirth(yearOfBirth);
        petBean.setMonthOfBirth(monthOfBirth);
        petBean.setDayOfBirth(dayOfBirth);
        petBean.setCoatLenght(coatLenght);
    }

    private void setMedicalInfo(PetBean petBean, boolean vaccinated, boolean microchipped, boolean dewormed, boolean sterilized, boolean disability, String disabilityType){
        petBean.setVaccinated(vaccinated);
        petBean.setMicrochipped(microchipped);
        petBean.setDewormed(dewormed);
        petBean.setSterilized(sterilized);
        petBean.setDisability(disability);
        petBean.setDisabilityType(disabilityType);
    }

    private void setCompatibility(PetBean petBean, PetCompatibility petCompatibility) {
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
    }

}
