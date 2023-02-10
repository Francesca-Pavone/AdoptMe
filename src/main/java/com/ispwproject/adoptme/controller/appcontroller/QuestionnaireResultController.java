package com.ispwproject.adoptme.controller.appcontroller;

import com.ispwproject.adoptme.engineering.exception.NoPetsFoundQuestionnaireException;
import com.ispwproject.adoptme.engineering.utils.ShowExceptionSupport;
import com.ispwproject.adoptme.model.PetModel;
import com.ispwproject.adoptme.engineering.bean.PetBean;
import com.ispwproject.adoptme.engineering.bean.QuestionnaireResultBean;
import com.ispwproject.adoptme.engineering.dao.PetDAO;
import com.ispwproject.adoptme.engineering.decorator.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionnaireResultController {
    public List<PetBean> searchPets(QuestionnaireResultBean questionnaireResultBean) {
        this.generateQuery(questionnaireResultBean);

        List<PetBean> petList = new ArrayList<>();
        Map<PetModel, Integer> hashMap = new HashMap<>();
        try { hashMap = PetDAO.retrievePetByQuestionnaire(generateQuery(questionnaireResultBean)); }
        catch (
                NoPetsFoundQuestionnaireException e) {
            ShowExceptionSupport.showExceptionGUI(e.getMessage());
        }
        for (Map.Entry<PetModel, Integer> entry : hashMap.entrySet()) {
            PetModel petModel = entry.getKey();
            PetBean petBean = new PetBean();
            petBean.setPetId(petModel.getPetId());
            petBean.setShelterId(hashMap.get(petModel));
            petBean.setPetImage(petModel.getPetImage());
            petBean.setName(petModel.getName());
            petBean.setType(petModel.getType());
            petBean.setGender(petModel.getGender());

            petList.add(petBean);
        }
        return petList;
    }

    public String generateQuery(QuestionnaireResultBean questionnaireResultBean) {
        IQuestionnaireQuery questionnaireQuery;
        if (questionnaireResultBean.isType() == 1) {
            questionnaireQuery = new CatQuery();
            if (questionnaireResultBean.isFemaleCat() || questionnaireResultBean.isMaleCat()) {
                questionnaireQuery = new CatTestFivFelvDecorator(questionnaireQuery);
                questionnaireQuery = new AndDecorator(questionnaireQuery);
            }
        }
        else {
            questionnaireQuery = new DogQuery();
            if (questionnaireResultBean.getSize() != -1) {
                questionnaireQuery = new SizeDecorator(questionnaireQuery, questionnaireResultBean.getSize());
                questionnaireQuery = new AndDecorator(questionnaireQuery);
            }
            questionnaireQuery = new DogEducationDecorator(questionnaireQuery, questionnaireResultBean.isProgramEducation());
            questionnaireQuery = new AndDecorator(questionnaireQuery);
        }

        if (questionnaireResultBean.isGender() != -1) {
            questionnaireQuery = new GenderDecorator(questionnaireQuery, questionnaireResultBean.isGender());
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
        questionnaireQuery = addInformation(questionnaireQuery, questionnaireResultBean);
        return questionnaireQuery.getQuery();
    }

    private IQuestionnaireQuery addInformation(IQuestionnaireQuery questionnaireQuery, QuestionnaireResultBean questionnaireResultBean) {

        if(questionnaireResultBean.isSleepOutside() == 1) {
            questionnaireQuery = new SleepOutsideDecorator(questionnaireQuery, questionnaireResultBean.isSleepOutside());
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
        return questionnaireQuery;
    }

}
