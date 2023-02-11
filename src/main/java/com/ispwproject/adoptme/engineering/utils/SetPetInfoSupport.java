package com.ispwproject.adoptme.engineering.utils;

import com.ispwproject.adoptme.engineering.bean.PetBean;
import com.ispwproject.adoptme.engineering.bean.PetInformationBean;
import com.ispwproject.adoptme.model.PetModel;

public class SetPetInfoSupport {
    private SetPetInfoSupport() {
        //private constructor
    }

    public static void setPetModel(PetModel petModel, PetBean petBean, PetInformationBean petInformationBean) {
        petModel.setYearOfBirth(petBean.getYearOfBirth());
        petModel.setMonthOfBirth(petBean.getMonthOfBirth());
        petModel.setDayOfBirth(petBean.getDayOfBirth());
        petModel.setGender(petBean.getGender());
        petModel.setCoatLenght(petInformationBean.getCoatLenght());
        petModel.setVaccinated(petInformationBean.isVaccinated());
        petModel.setMicrochipped(petInformationBean.isMicrochipped());
        petModel.setDewormed(petInformationBean.isDewormed());
        petModel.setSterilized(petInformationBean.isSterilized());
        petModel.setDisability(petInformationBean.isDisability());
        petModel.setDisabilityType(petInformationBean.getDisabilityType());
    }
}
