package com.ispwproject.adoptme.controller.appcontroller;

import com.ispwproject.adoptme.model.PetModel;
import com.ispwproject.adoptme.engineering.bean.PetBean;
import com.ispwproject.adoptme.engineering.bean.QuestionnaireResultBean;
import com.ispwproject.adoptme.engineering.dao.PetDAO;
import com.ispwproject.adoptme.engineering.decorator.*;

import java.util.ArrayList;
import java.util.List;

public class QuestionnaireResultController {
    public List<PetBean> searchPets(QuestionnaireResultBean questionnaireResultBean) throws Exception {
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
                    questionnaireQuery = new SizeDecorator(questionnaireQuery, questionnaireResultBean.getSize());
                    questionnaireQuery = new AndDecorator(questionnaireQuery);
                }
                questionnaireQuery = new DogEducationDecorator(questionnaireQuery, questionnaireResultBean.isProgramEducation());
                questionnaireQuery = new AndDecorator(questionnaireQuery);
            }
        }
        if (questionnaireResultBean.isGender() != -1) {
            questionnaireQuery = new GenderDecorator(questionnaireQuery, questionnaireResultBean.isGender());
            questionnaireQuery = new AndDecorator(questionnaireQuery);
        }
        if (!questionnaireResultBean.getAge().equals("")) {
            questionnaireQuery = new AgeDecorator(questionnaireQuery, questionnaireResultBean.getAge());
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
            questionnaireQuery = new GardenDecorator(new AndDecorator(new SleepOutsideDecorator(questionnaireQuery, questionnaireResultBean.isSleepOutside())), questionnaireResultBean.isHaveAGarden());
            questionnaireQuery = new AndDecorator(questionnaireQuery);
        } else {
            questionnaireQuery = new GardenDecorator(questionnaireQuery, questionnaireResultBean.isHaveAGarden());
            questionnaireQuery = new AndDecorator(questionnaireQuery);
        }
        if (questionnaireResultBean.isHaveATerrace() == 1) {
            questionnaireQuery = new TerraceDecorator(new AndDecorator(new SleepOutsideDecorator(questionnaireQuery, questionnaireResultBean.isSleepOutside())), questionnaireResultBean.isHaveATerrace());
            questionnaireQuery = new AndDecorator(questionnaireQuery);
        } else {
            questionnaireQuery = new TerraceDecorator(questionnaireQuery, questionnaireResultBean.isHaveATerrace());
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
        if(questionnaireResultBean.isFirstPet() == 1) {
            questionnaireQuery = new FirstExperienceDecorator(questionnaireQuery);
            questionnaireQuery = new AndDecorator(questionnaireQuery);
        }
        questionnaireQuery = new HoursAloneDecorator(questionnaireQuery, questionnaireResultBean.getHoursAlone());
        if(questionnaireResultBean.isSpecificArea())
            questionnaireQuery = new CityDecorator(new AndDecorator(questionnaireQuery), questionnaireResultBean.getCity());
        questionnaireQuery = new EndDecorator(questionnaireQuery);

        List<PetBean> petList = new ArrayList<>();
        for (PetModel pet : PetDAO.retrievePetByQuestionnaire(questionnaireQuery.getQuery())) {
            PetBean petBean = new PetBean(pet);
            petList.add(petBean);
        }
        return petList;
    }
}
