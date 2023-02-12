package com.ispwproject.adoptme.controller.appcontroller;

import com.ispwproject.adoptme.engineering.bean.PetInformationBean;
import com.ispwproject.adoptme.engineering.dao.*;
import com.ispwproject.adoptme.engineering.session.Session;
import com.ispwproject.adoptme.model.*;
import com.ispwproject.adoptme.engineering.bean.PetBean;
import com.ispwproject.adoptme.engineering.bean.ShelterBean;


public class PetInfoController {
    private PetInformationBean petInformationBean;


    public PetInformationBean getPetInfo(PetBean petBean, ShelterBean shelterBean){
        try {
            ShelterModel shelterModel = ShelterDAO.retrieveShelterById(petBean.getPetBeanShelter());

            if (petBean.getPetBeanType() == 0) {
                DogModel dogModel = DogDAO.retrieveDogById(petBean.getPetBeanId(), petBean.getPetBeanShelter());

                //vado a settare nel bean le nuove info del pet che mi servono
                setGeneralInfo(petBean, dogModel.getYearOfBirth(), dogModel.getMonthOfBirth(), dogModel.getDayOfBirth());
                setMedicalInfo(dogModel.isVaccinated(), dogModel.isMicrochipped(), dogModel.isDewormed(), dogModel.isSterilized(), dogModel.isDisability(), dogModel.getDisabilityType(), dogModel.getCoatLength());
                setCompatibility(dogModel.getPetCompatibility());
                petInformationBean.setDogEducationBean(dogModel.isProgramEducation());
                petInformationBean.setSizeBean(dogModel.getSize());
                petBean.setPetBeanFav(this.checkFavorite(petBean));

            }
            else {
                CatModel catModel = CatDAO.retrieveCatById(petBean.getPetBeanId(), petBean.getPetBeanShelter());

                setGeneralInfo(petBean, catModel.getYearOfBirth(), catModel.getMonthOfBirth(), catModel.getDayOfBirth());
                setMedicalInfo(catModel.isVaccinated(), catModel.isMicrochipped(), catModel.isDewormed(), catModel.isSterilized(), catModel.isDisability(), catModel.getDisabilityType(), catModel.getCoatLength());
                setCompatibility(catModel.getPetCompatibility());
                petInformationBean.setTestFivBean(catModel.isTestFiv());
                petInformationBean.setTestFelvBean(catModel.isTestFelv());
                petBean.setPetBeanFav(this.checkFavorite(petBean));
            }
            shelterBean.setShelterBeanId(shelterModel.getId());
            shelterBean.setShelterBeanName(shelterModel.getShelterName());
            shelterBean.setBeanPhoneNumber(shelterModel.getPhoneNumber());
            shelterBean.setBeanAddress(shelterModel.getAddress());
            shelterBean.setBeanCity(shelterModel.getCity());
            shelterBean.setBeanWebSite(shelterModel.getWebSite());
            shelterBean.setBeanEmail(shelterModel.getEmail());
            shelterBean.setShelterBeanImg(shelterModel.getImage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return petInformationBean;
    }

    public boolean checkFavorite(PetBean petBean) {
        boolean fav = false;
        if(Session.getCurrentSession().getUserBean() != null)
            fav = FavoritesDAO.checkFav(petBean.getPetBeanId(), petBean.getPetBeanShelter(), Session.getCurrentSession().getUserBean().getUserId());
        return fav;
    }

    private void setGeneralInfo(PetBean petBean, int yearOfBirth, int monthOfBirth, int dayOfBirth) {
        petBean.setPetBeanBirthYear(yearOfBirth);
        petBean.setPetBeanBirthMonth(monthOfBirth);
        petBean.setPetBeanBirthDay(dayOfBirth);
    }

    private void setMedicalInfo(boolean vaccinated, boolean microchipped, boolean dewormed, boolean sterilized, boolean disability, String disabilityType, int coatLenght){
        this.petInformationBean = new PetInformationBean();
        petInformationBean.setCoatLengthBean(coatLenght);
        petInformationBean.setVaccinatedBean(vaccinated);
        petInformationBean.setMicrochippedBean(microchipped);
        petInformationBean.setDewormedBean(dewormed);
        petInformationBean.setSterilizedBean(sterilized);
        petInformationBean.setDisabilityBean(disability);
        petInformationBean.setDisabilityTypeBean(disabilityType);
    }

    private void setCompatibility(PetCompatibility petCompatibility) {
        petInformationBean.setMaleDogBean(petCompatibility.isMaleDog());
        petInformationBean.setFemaleDogBean(petCompatibility.isFemaleDog());
        petInformationBean.setMaleCatBean(petCompatibility.isMaleCat());
        petInformationBean.setFemaleCatBean(petCompatibility.isFemaleCat());
        petInformationBean.setChildrenBean(petCompatibility.isChildren());
        petInformationBean.setEldersBean(petCompatibility.isElders());
        petInformationBean.setSleepOutsideBean(petCompatibility.isSleepOutside());
        petInformationBean.setFirstExperienceBean(petCompatibility.isFirstExperience());
        petInformationBean.setHoursAloneBean(petCompatibility.getHoursAlone());
    }

}
