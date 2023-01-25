package com.ispwproject.adoptme.utils.builder;

import com.ispwproject.adoptme.utils.bean.QuestionnaireResultBean;

public final class QuestionnaireResultBeanBuilder {
    private int type;
    private int gender;
    private String age;
    /*private boolean puppy;
    private boolean young;
    private boolean adult;
    private boolean senior;*/
    private int size;
    private boolean maleCat;
    private boolean femaleCat;
    private boolean maleDog;
    private boolean femaleDog;
    private int haveAGarden;
    private int sleepOutside;
    private int haveATerrace;
    private boolean terraceSleepOutside;
    private int hoursAlone;
    private int firstPet;
    private boolean sterilizePet;
    private int programEducation;
    private boolean disabledPet;
    private boolean specificArea;
    private String city;

    private QuestionnaireResultBeanBuilder() {
    }

    public static QuestionnaireResultBeanBuilder newQuestionnaireResultBean() {
        return new QuestionnaireResultBeanBuilder();
    }

    public QuestionnaireResultBeanBuilder type(int type) {
        this.type = type;
        return this;
    }

    public QuestionnaireResultBeanBuilder gender(int gender) {
        this.gender = gender;
        return this;
    }

    /*public QuestionnaireResultBeanBuilder puppy(boolean puppy) {
        this.puppy = puppy;
        return this;
    }

    public QuestionnaireResultBeanBuilder young(boolean young) {
        this.young = young;
        return this;
    }

    public QuestionnaireResultBeanBuilder adult(boolean adult) {
        this.adult = adult;
        return this;
    }

    public QuestionnaireResultBeanBuilder senior(boolean senior) {
        this.senior = senior;
        return this;
    }*/

    public QuestionnaireResultBeanBuilder age(String age) {
        this.age = age;
        return this;
    }

    public QuestionnaireResultBeanBuilder size(int size) {
        this.size = size;
        return this;
    }

    public QuestionnaireResultBeanBuilder maleCat(boolean maleCat) {
        this.maleCat = maleCat;
        return this;
    }

    public QuestionnaireResultBeanBuilder femaleCat(boolean femaleCat) {
        this.femaleCat = femaleCat;
        return this;
    }

    public QuestionnaireResultBeanBuilder maleDog(boolean maleDog) {
        this.maleDog = maleDog;
        return this;
    }

    public QuestionnaireResultBeanBuilder femaleDog(boolean femaleDog) {
        this.femaleDog = femaleDog;
        return this;
    }

    public QuestionnaireResultBeanBuilder haveAGarden(int haveAGarden) {
        this.haveAGarden = haveAGarden;
        return this;
    }

    public QuestionnaireResultBeanBuilder sleepOutside(int gardenSleepOutside) {
        this.sleepOutside = gardenSleepOutside;
        return this;
    }

    public QuestionnaireResultBeanBuilder haveATerrace(int haveATerrace) {
        this.haveATerrace = haveATerrace;
        return this;
    }

    public QuestionnaireResultBeanBuilder hoursAlone(int hoursAlone) {
        this.hoursAlone = hoursAlone;
        return this;
    }

    public QuestionnaireResultBeanBuilder firstPet(int firstPet) {
        this.firstPet = firstPet;
        return this;
    }

    public QuestionnaireResultBeanBuilder sterilizePet(boolean sterilizePet) {
        this.sterilizePet = sterilizePet;
        return this;
    }

    public QuestionnaireResultBeanBuilder programEducation(int programEducation) {
        this.programEducation = programEducation;
        return this;
    }

    public QuestionnaireResultBeanBuilder specificArea(boolean specificArea) {
        this.specificArea = specificArea;
        return this;
    }

    public QuestionnaireResultBeanBuilder disabledPet(boolean disabledPet) {
        this.disabledPet = disabledPet;
        return this;
    }

    public QuestionnaireResultBeanBuilder city(String city) {
        this.city = city;
        return this;
    }

    public QuestionnaireResultBean build() {
        QuestionnaireResultBean questionnaireResultBean = new QuestionnaireResultBean();
        questionnaireResultBean.setType(type);
        questionnaireResultBean.setGender(gender);
        /*questionnaireResultBean.setPuppy(puppy);
        questionnaireResultBean.setYoung(young);
        questionnaireResultBean.setAdult(adult);
        questionnaireResultBean.setSenior(senior);*/
        questionnaireResultBean.setAge(age);
        questionnaireResultBean.setSize(size);
        questionnaireResultBean.setMaleCat(maleCat);
        questionnaireResultBean.setFemaleCat(femaleCat);
        questionnaireResultBean.setMaleDog(maleDog);
        questionnaireResultBean.setFemaleDog(femaleDog);
        questionnaireResultBean.setHaveAGarden(haveAGarden);
        questionnaireResultBean.setSleepOutside(sleepOutside);
        questionnaireResultBean.setHaveATerrace(haveATerrace);
        questionnaireResultBean.setHoursAlone(hoursAlone);
        questionnaireResultBean.setFirstPet(firstPet);
        questionnaireResultBean.setSterilizePet(sterilizePet);
        questionnaireResultBean.setProgramEducation(programEducation);
        questionnaireResultBean.setDisabledPet(disabledPet);
        questionnaireResultBean.setSpecificArea(specificArea);
        questionnaireResultBean.setCity(city);
        return questionnaireResultBean;
    }
}
