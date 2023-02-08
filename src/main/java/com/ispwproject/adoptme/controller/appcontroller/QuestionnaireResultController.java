package com.ispwproject.adoptme.controller.appcontroller;

import com.ispwproject.adoptme.engineering.exception.NoPetsFoundQuestionnaireException;
import com.ispwproject.adoptme.engineering.utils.ShowExceptionSupport;
import com.ispwproject.adoptme.model.PetCompatibility;
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
        IQuestionnaireQuery questionnaireQuery;
        if (questionnaireResultBean.isType() == 1) {
            questionnaireQuery = new CatQuery();
            if (questionnaireResultBean.isFemaleCat() || questionnaireResultBean.isMaleCat()) {
                questionnaireQuery = new CatTestFivFelv(questionnaireQuery);
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
        questionnaireQuery = addInformation(questionnaireQuery, questionnaireResultBean);

        List<PetBean> petList = new ArrayList<>();
        HashMap<PetModel, Integer> hashMap = new HashMap<>();
        try { hashMap = PetDAO.retrievePetByQuestionnaire(questionnaireQuery.getQuery()); } catch (NoPetsFoundQuestionnaireException e) {
            ShowExceptionSupport.showExceptionGUI(e.getMessage());
        }
        for (Map.Entry entry : hashMap.entrySet()) {
            PetModel petModel = (PetModel) entry.getKey();
            PetBean petBean = new PetBean();
            petBean.setPetId(petModel.getPetId());
            petBean.setShelterId(hashMap.get(petModel));
            petBean.setPetImage(petModel.getPetImage());
            petBean.setName(petModel.getName());
            petBean.setType(petModel.getType());
            petBean.setYearOfBirth(petModel.getYearOfBirth());
            petBean.setMonthOfBirth(petModel.getMonthOfBirth());
            petBean.setDayOfBirth(petModel.getDayOfBirth());
            petBean.setAge(petModel.getAge());
            petBean.setGender(petModel.getGender());
            petBean.setCoatLenght(petModel.getCoatLenght());
            petBean.setVaccinated(petModel.isVaccinated());
            petBean.setMicrochipped(petModel.isMicrochipped());
            petBean.setDewormed(petModel.isDewormed());
            petBean.setSterilized(petModel.isSterilized());
            petBean.setDisability(petModel.isDisability());
            petBean.setDisabilityType(petModel.getDisabilityType());

            PetCompatibility petCompatibility = petModel.getPetCompatibility();
            petBean.setMaleDog(petCompatibility.isMaleDog());
            petBean.setFemaleDog(petCompatibility.isFemaleDog());
            petBean.setMaleCat(petCompatibility.isMaleCat());
            petBean.setFemaleCat(petCompatibility.isFemaleCat());
            petBean.setChildren(petCompatibility.isChildren());
            petBean.setElders(petCompatibility.isElders());
            petBean.setNoGarden(petCompatibility.isApartmentNoGarden());
            petBean.setNoTerrace(petCompatibility.isApartmentNoTerrace());
            petBean.setSleepOutside(petCompatibility.isSleepOutside());
            petBean.setFirstExperience(petCompatibility.isFirstExperience());
            petBean.setHoursAlone(petCompatibility.getHoursAlone());
            petList.add(petBean);

        }
        return petList;
    }

    private IQuestionnaireQuery addInformation(IQuestionnaireQuery questionnaireQuery, QuestionnaireResultBean questionnaireResultBean) {
        if (questionnaireResultBean.isHaveAGarden() == 1) {
            questionnaireQuery = new GardenDecorator(questionnaireQuery, questionnaireResultBean.isHaveAGarden());
            questionnaireQuery = new AndDecorator(questionnaireQuery);
        }
        if (questionnaireResultBean.isHaveATerrace() == 1) {
            questionnaireQuery = new TerraceDecorator(questionnaireQuery, questionnaireResultBean.isHaveATerrace());
            questionnaireQuery = new AndDecorator(questionnaireQuery);
        }
        if(questionnaireResultBean.isHaveAGarden() == 1 || questionnaireResultBean.isHaveATerrace() == 1) {
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
