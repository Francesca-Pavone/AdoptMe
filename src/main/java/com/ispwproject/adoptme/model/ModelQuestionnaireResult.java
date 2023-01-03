package com.ispwproject.adoptme.model;

import com.ispwproject.adoptme.utils.bean.QuestionnaireResultBean;

public class ModelQuestionnaireResult {
    public enum PetAlreadyHave {
        maleCatSterilized,
        maleCatNonSterilized,
        femaleCatSterilized,
        femaleCatNonSterilized,
        maleDog,
        femaleDog,
        maleDogSterilized,
        maleDogNonSterilized,
        femaleDogSterilized,
        femaleDogNonSterilized,
        maleCat,
        femaleCat
    }

   public static class Tuple {
       private final int minAge;
       private final int maxAge;
       public Tuple(int minAge, int maxAge) {
            this.minAge = minAge;
            this.maxAge = maxAge;
        }
   }

    private Tuple[] rangeAge;
    private int haveAPet; // 0 -> no | 1 -> yes
    private QuestionnaireResultBean.PetAlreadyHave[] petAlreadyHave;
    private int haveAGarden; // 0 -> no | 1 -> yes
    private int gardenSleepOutside; // 0 -> no | 1 -> yes
    private int haveATerrace; // 0 -> no | 1 -> yes
    private int terraceSleepOutside; // 0 -> no | 1 -> yes
    private int hoursAlone; // 0 -> 0-3 hours | 1 -> 4-6 hours | 2 -> more than 6 hours
    private int firstPet; // 0 -> no | 1 -> yes
    private int sterilizePet; // 0 -> no | 1 -> yes
    private int programEducation; // 0 -> no | 1 -> yes
    private int disabledPet; // 0 -> no | 1 -> yes
    private int specificArea; // 0 -> no | 1 -> yes
    private String city;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }


    public int getHaveAPet() {
        return haveAPet;
    }

    public void setHaveAPet(int haveAPet) {
        this.haveAPet = haveAPet;
    }

    public QuestionnaireResultBean.PetAlreadyHave[] getPetAlreadyHave() {
        return petAlreadyHave;
    }

    public void setPetAlreadyHave(QuestionnaireResultBean.PetAlreadyHave[] petAlreadyHave) {
        this.petAlreadyHave = petAlreadyHave;
    }

    public int getHaveAGarden() {
        return haveAGarden;
    }

    public void setHaveAGarden(int haveAGarden) {
        this.haveAGarden = haveAGarden;
    }

    public int getGardenSleepOutside() {
        return gardenSleepOutside;
    }

    public void setGardenSleepOutside(int gardenSleepOutside) {
        this.gardenSleepOutside = gardenSleepOutside;
    }

    public int getHaveATerrace() {
        return haveATerrace;
    }

    public void setHaveATerrace(int haveATerrace) {
        this.haveATerrace = haveATerrace;
    }

    public int getTerraceSleepOutside() {
        return terraceSleepOutside;
    }

    public void setTerraceSleepOutside(int terraceSleepOutside) {
        this.terraceSleepOutside = terraceSleepOutside;
    }

    public int getHoursAlone() {
        return hoursAlone;
    }

    public void setHoursAlone(int hoursAlone) {
        this.hoursAlone = hoursAlone;
    }

    public int getFirstPet() {
        return firstPet;
    }

    public void setFirstPet(int firstPet) {
        this.firstPet = firstPet;
    }

    public int getSterilizePet() {
        return sterilizePet;
    }

    public void setSterilizePet(int sterilizePet) {
        this.sterilizePet = sterilizePet;
    }

    public int getProgramEducation() {
        return programEducation;
    }

    public void setProgramEducation(int programEducation) {
        this.programEducation = programEducation;
    }

    public int getDisabledPet() {
        return disabledPet;
    }

    public void setDisabledPet(int disabledPet) {
        this.disabledPet = disabledPet;
    }

    public int getSpecificArea() {
        return specificArea;
    }

    public void setSpecificArea(int specificArea) {
        this.specificArea = specificArea;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    private int type; // 0 -> Dog | 1 -> Cat
    private int gender; // 0 -> male | 1 -> female

    public Tuple[] getRangeAge() {
        return rangeAge;
    }

    public void setRangeAge(Tuple newRangeAge) {
        int i;
        int n = rangeAge.length;
        Tuple[] newArr = new Tuple[n+1];
        for (i = 0; i < n; i++) {
            newArr[i] = rangeAge[i];
        }
        newArr[n] = newRangeAge;
        this.rangeAge = newArr;
    }







}

/*  public class Adapter implements QuestionnaireResultBean {
    private QuestionnaireResultModel questionnaireResultModel;

    public Adapter(QuestionnaireResultModel questionnaireResultModel) {
        this.questionnaireResultModel = questionnaireResultModel;
    }

    @Override
    public
}
 */