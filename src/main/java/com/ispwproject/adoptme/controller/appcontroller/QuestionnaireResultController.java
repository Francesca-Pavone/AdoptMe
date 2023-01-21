package com.ispwproject.adoptme.controller.appcontroller;

import com.ispwproject.adoptme.utils.bean.QuestionnaireResultBean;
import com.ispwproject.adoptme.utils.dao.PetDAO;
import com.ispwproject.adoptme.utils.decorator.*;

public class QuestionnaireResultController {
    public void searchPets(QuestionnaireResultBean questionnaireResultBean) throws Exception {
        IQuestionnaireQuery questionnaireQuery;
        switch (questionnaireResultBean.isType()) {
            case 1 -> {
                questionnaireQuery = new CatQuery();
                if(questionnaireResultBean.isFemaleCat() || questionnaireResultBean.isMaleCat()) {
                    questionnaireQuery = new CatTestFivFelv(questionnaireQuery);
                    questionnaireQuery = new AndDecorator(questionnaireQuery);
                }
            }
            default -> {
                questionnaireQuery = new DogQuery();
                if (questionnaireResultBean.getSize() != -1) {
                    questionnaireQuery = new SizeDecorator(questionnaireQuery);
                    questionnaireQuery = new AndDecorator(questionnaireQuery);
                }
                questionnaireQuery = new DogEducationDecorator(questionnaireQuery);
                questionnaireQuery = new AndDecorator(questionnaireQuery);
            }
        }
        if (questionnaireResultBean.isGender() != -1) {
            questionnaireQuery = new GenderDecorator(questionnaireQuery);
            questionnaireQuery = new AndDecorator(questionnaireQuery);
        }
        if (questionnaireResultBean.getAge() != -1) {
            questionnaireQuery = new AgeDecorator(questionnaireQuery);
            questionnaireQuery = new AndDecorator(questionnaireQuery);
        }
        if (questionnaireResultBean.isMaleCat()) {
            questionnaireQuery = new MaleCatDecorator(questionnaireQuery);
            questionnaireQuery = new AndDecorator(questionnaireQuery);
        }
        if (questionnaireResultBean.isFemaleCat()) {
            questionnaireQuery = new FemaleCatDecorator(questionnaireQuery);
            questionnaireQuery = new AndDecorator(questionnaireQuery);
        }
        if (questionnaireResultBean.isMaleDog()) {
            questionnaireQuery = new MaleDogDecorator(questionnaireQuery);
            questionnaireQuery = new AndDecorator(questionnaireQuery);
        }
        if (questionnaireResultBean.isFemaleDog()) {
            questionnaireQuery = new FemaleDogDecorator(questionnaireQuery);
            questionnaireQuery = new AndDecorator(questionnaireQuery);
        }
        if (questionnaireResultBean.isHaveAGarden() == 1) {
            questionnaireQuery = new GardenDecorator(new AndDecorator(new SleepOutsideDecorator(questionnaireQuery)));
            questionnaireQuery = new AndDecorator(questionnaireQuery);
        } else {
            questionnaireQuery = new GardenDecorator(questionnaireQuery);
            questionnaireQuery = new AndDecorator(questionnaireQuery);
        }
        if (questionnaireResultBean.isHaveATerrace() == 1) {
            questionnaireQuery = new TerraceDecorator(new AndDecorator(new SleepOutsideDecorator(questionnaireQuery)));
            questionnaireQuery = new AndDecorator(questionnaireQuery);
        } else {
            questionnaireQuery = new TerraceDecorator(questionnaireQuery);
            questionnaireQuery = new AndDecorator(questionnaireQuery);
        }
        if(!questionnaireResultBean.isSterilizePet()) {
            questionnaireQuery = new SterilizeDecorator(questionnaireQuery);
            questionnaireQuery = new AndDecorator(questionnaireQuery);
        }
        if(!questionnaireResultBean.isDisabledPet()) {
            questionnaireQuery = new DisabledDecorator(questionnaireQuery);
            questionnaireQuery = new AndDecorator(questionnaireQuery);
        }
        questionnaireQuery = new HoursAloneDecorator(new AndDecorator(new FirstExperienceDecorator(questionnaireQuery)));
        if(questionnaireResultBean.isSpecificArea())
            questionnaireQuery = new CityDecorator(new AndDecorator(questionnaireQuery));
        PetDAO.retrievePetByQuestionnaire(questionnaireQuery.getQuery(), questionnaireResultBean.isGender(), questionnaireResultBean.getAge(), questionnaireResultBean.getCity(), questionnaireResultBean.isProgramEducation(), questionnaireResultBean.isFirstPet(), questionnaireResultBean.isHaveAGarden(), questionnaireResultBean.getHoursAlone(), questionnaireResultBean.getSize(), questionnaireResultBean.isHaveATerrace());
    }
}
