package com.ispwproject.adoptme.engineering.utils;

import com.ispwproject.adoptme.engineering.bean.PetBean;
import com.ispwproject.adoptme.engineering.bean.PetInformationBean;
import com.ispwproject.adoptme.model.PetModel;

public class SetPetInfoSupport {
    private SetPetInfoSupport() {
        //private constructor
    }

    public static void setPetModel(PetModel petModel, PetBean petBean, PetInformationBean petInformationBean) {
        petModel.setYearOfBirth(petBean.getPetBeanBirthYear());
        petModel.setMonthOfBirth(petBean.getPetBeanBirthMonth());
        petModel.setDayOfBirth(petBean.getPetBeanBirthDay());
        petModel.setGender(petBean.getPetBeanGender());
        petModel.setCoatLength(petInformationBean.getCoatLengthBean());
        petModel.setVaccinated(petInformationBean.isVaccinatedBean());
        petModel.setMicrochipped(petInformationBean.isMicrochippedBean());
        petModel.setDewormed(petInformationBean.isDewormedBean());
        petModel.setSterilized(petInformationBean.isSterilizedBean());
        petModel.setDisability(petInformationBean.isDisabilityBean());
        petModel.setDisabilityType(petInformationBean.getDisabilityTypeBean());
    }
}
