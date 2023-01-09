package com.ispwproject.adoptme.controller.appcontroller;

import com.ispwproject.adoptme.utils.bean.QuestionnaireResultBean;
import com.ispwproject.adoptme.utils.dao.PetDAO;
import com.ispwproject.adoptme.utils.dao.ShelterDAOJDBC;

import java.util.List;

public class QuestionnaireResultApplicativeController {
    public static class Tuple {
        private final int minAge;
        private final int maxAge;
        public Tuple(int minAge, int maxAge) {
            this.minAge = minAge;
            this.maxAge = maxAge;
        }
    }

    public void searchCities(QuestionnaireResultBean questionnaireResultBean) throws Exception {
        String city = questionnaireResultBean.getCity();
        //questionnaireResultBean.setListOfCities(ShelterDAOJDBC.retrieveCityByInputCity(city));
    }

    public void findPets(QuestionnaireResultBean questionnaireResultBean) throws Exception {
        //adapter

        List<Tuple> rangeAge = null;
        int i;
        for (i = 0; i < questionnaireResultBean.getAge().size(); i++) {
            if (questionnaireResultBean.getAge().get(i) == QuestionnaireResultBean.PetAge.puppy) {
                rangeAge.add(new Tuple(0, 1));
            }
            if (questionnaireResultBean.getAge().get(i) == QuestionnaireResultBean.PetAge.young) {
                rangeAge.add(new Tuple(2, 3));
            }
            if (questionnaireResultBean.getAge().get(i) == QuestionnaireResultBean.PetAge.adult) {
                rangeAge.add(new Tuple(4, 10));
            }
            if (questionnaireResultBean.getAge().get(i) == QuestionnaireResultBean.PetAge.senior) {
                rangeAge.add(new Tuple(11, 30));
            }
        }
        String effectiveGender;
        if(questionnaireResultBean.getGender() == 0)
            effectiveGender = "Male";
        else if(questionnaireResultBean.getGender() == 1)
            effectiveGender = "Female";
        else
            effectiveGender = "";
        //Capisci se puoi passare null come valori nel caso in cui, per esempio, non importa il gender o non ha altri animali o non ha nè garden nè terrace quindi non ho informazioni se dorme fuori ecc...

        //questionnaireResultBean.setListOfPets(PetDAO.retreivePetFromQuestionnaire(questionnaireResultBean.getType(), effectiveGender, rangeAge, );



    }
}
