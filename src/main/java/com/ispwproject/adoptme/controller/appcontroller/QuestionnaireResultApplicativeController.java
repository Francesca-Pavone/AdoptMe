package com.ispwproject.adoptme.controller.appcontroller;

import com.ispwproject.adoptme.model.ModelQuestionnaireResult;
import com.ispwproject.adoptme.utils.bean.QuestionnaireResultBean;

public class QuestionnaireResultApplicativeController {
    public void findPets(QuestionnaireResultBean questionnaireResultBean) {
        ModelQuestionnaireResult modelQuestionnaireResult = new ModelQuestionnaireResult();

        modelQuestionnaireResult.setType(questionnaireResultBean.getType());
        modelQuestionnaireResult.setGender(questionnaireResultBean.getGender());

        int i;
        for (i = 0; i < questionnaireResultBean.getAge().length; i++) {
            if (questionnaireResultBean.getAge()[i] == QuestionnaireResultBean.PetAge.puppy) {
                modelQuestionnaireResult.setRangeAge(new ModelQuestionnaireResult.Tuple(0, 1));
            }
            if (questionnaireResultBean.getAge()[i] == QuestionnaireResultBean.PetAge.young) {
                modelQuestionnaireResult.setRangeAge(new ModelQuestionnaireResult.Tuple(2, 3));
            }
            if (questionnaireResultBean.getAge()[i] == QuestionnaireResultBean.PetAge.adult) {
                modelQuestionnaireResult.setRangeAge(new ModelQuestionnaireResult.Tuple(4, 10));
            }
            if (questionnaireResultBean.getAge()[i] == QuestionnaireResultBean.PetAge.senior) {
                modelQuestionnaireResult.setRangeAge(new ModelQuestionnaireResult.Tuple(11, 30));
            }
        }

       // modelQuestionnaireResult.se



    }
}
