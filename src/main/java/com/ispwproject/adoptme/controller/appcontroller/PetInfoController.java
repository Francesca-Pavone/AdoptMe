package com.ispwproject.adoptme.controller.appcontroller;

import com.ispwproject.adoptme.engineering.bean.PetInformationBean;
import com.ispwproject.adoptme.engineering.dao.*;
import com.ispwproject.adoptme.engineering.session.Session;
import com.ispwproject.adoptme.model.*;
import com.ispwproject.adoptme.engineering.bean.PetBean;
import com.ispwproject.adoptme.engineering.bean.ShelterBean;


public class PetInfoController {

    public void getPetInfo(PetBean petBean, PetInformationBean petInformationBean, ShelterBean shelterBean){
        try {
            ShelterModel shelterModel = ShelterDAO.retrieveShelterById(petBean.getShelterId());

            if (petBean.getType() == 0) {
                DogModel dogModel = DogDAO.retrieveDogById(petBean.getPetId(), petBean.getShelterId());

                //vado a settare nel bean le nuove info del pet che mi servono
                setGeneralInfo(petBean, dogModel.getYearOfBirth(), dogModel.getMonthOfBirth(), dogModel.getDayOfBirth());
                setMedicalInfo(petInformationBean, dogModel.isVaccinated(), dogModel.isMicrochipped(), dogModel.isDewormed(), dogModel.isSterilized(), dogModel.isDisability(), dogModel.getDisabilityType(), dogModel.getCoatLenght());
                setCompatibility(petInformationBean, dogModel.getPetCompatibility());
                petInformationBean.setDogEducation(dogModel.isProgramEducation());
                petInformationBean.setSize(dogModel.getSize());
                petBean.setFav(this.checkFavorite(petBean));

            }
            else {
                CatModel catModel = CatDAO.retrieveCatById(petBean.getPetId(), petBean.getShelterId());

                setGeneralInfo(petBean, catModel.getYearOfBirth(), catModel.getMonthOfBirth(), catModel.getDayOfBirth());
                setMedicalInfo(petInformationBean, catModel.isVaccinated(), catModel.isMicrochipped(), catModel.isDewormed(), catModel.isSterilized(), catModel.isDisability(), catModel.getDisabilityType(), catModel.getCoatLenght());
                setCompatibility(petInformationBean, catModel.getPetCompatibility());
                petInformationBean.setTestFiv(catModel.isTestFiv());
                petInformationBean.setTestFelv(catModel.isTestFelv());
                petBean.setFav(this.checkFavorite(petBean));
            }
            shelterBean.setShelterId(shelterModel.getId());
            shelterBean.setName(shelterModel.getShelterName());
            shelterBean.setPhoneNumber(shelterModel.getPhoneNumber());
            shelterBean.setAddress(shelterModel.getAddress());
            shelterBean.setCity(shelterModel.getCity());
            shelterBean.setWebSite(shelterModel.getWebSite());
            shelterBean.setEmail(shelterModel.getEmail());
            shelterBean.setShelterImg(shelterModel.getImage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkFavorite(PetBean petBean) {
        boolean fav = false;
        if(Session.getCurrentSession().getUserBean() != null)
            fav = FavoritesDAO.checkFav(petBean.getPetId(), petBean.getShelterId(), Session.getCurrentSession().getUserBean().getUserId());
        return fav;
    }

    private void setGeneralInfo(PetBean petBean, int yearOfBirth, int monthOfBirth, int dayOfBirth) {
        petBean.setYearOfBirth(yearOfBirth);
        petBean.setMonthOfBirth(monthOfBirth);
        petBean.setDayOfBirth(dayOfBirth);
    }

    private void setMedicalInfo(PetInformationBean petInformationBean, boolean vaccinated, boolean microchipped, boolean dewormed, boolean sterilized, boolean disability, String disabilityType, int coatLenght){
        petInformationBean.setCoatLenght(coatLenght);
        petInformationBean.setVaccinated(vaccinated);
        petInformationBean.setMicrochipped(microchipped);
        petInformationBean.setDewormed(dewormed);
        petInformationBean.setSterilized(sterilized);
        petInformationBean.setDisability(disability);
        petInformationBean.setDisabilityType(disabilityType);
    }

    private void setCompatibility(PetInformationBean petInformationBean, PetCompatibility petCompatibility) {
        petInformationBean.setMaleDog(petCompatibility.isMaleDog());
        petInformationBean.setFemaleDog(petCompatibility.isFemaleDog());
        petInformationBean.setMaleCat(petCompatibility.isMaleCat());
        petInformationBean.setFemaleCat(petCompatibility.isFemaleCat());
        petInformationBean.setChildren(petCompatibility.isChildren());
        petInformationBean.setElders(petCompatibility.isElders());
        petInformationBean.setSleepOutside(petCompatibility.isSleepOutside());
        petInformationBean.setFirstExperience(petCompatibility.isFirstExperience());
        petInformationBean.setHoursAlone(petCompatibility.getHoursAlone());
    }

}
