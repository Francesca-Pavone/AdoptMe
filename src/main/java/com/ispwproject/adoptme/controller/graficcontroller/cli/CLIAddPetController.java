package com.ispwproject.adoptme.controller.graficcontroller.cli;

import com.ispwproject.adoptme.controller.appcontroller.AddPetController;
import com.ispwproject.adoptme.engineering.bean.PetBean;
import com.ispwproject.adoptme.engineering.bean.PetInformationBean;
import com.ispwproject.adoptme.engineering.builder.PetInformationBeanBuilder;
import com.ispwproject.adoptme.engineering.exception.PetDateOfBirthException;
import com.ispwproject.adoptme.engineering.session.Session;
import com.ispwproject.adoptme.engineering.utils.ShowExceptionSupport;
import com.ispwproject.adoptme.view.cli.CLIAddPetView;

public class CLIAddPetController implements CLIGraficController {
    private final CLIAddPetView view;
    private PetBean petBean;
    private PetInformationBean petInformationBean;
    private PetInformationBeanBuilder petInformationBeanBuilder;

    public CLIAddPetController() {
        this.view = new CLIAddPetView(this);
    }

    @Override
    public void start() {
        petBean = new PetBean();
        this.view.showForm();
    }

    public void setMainInfo(String name, String date, String year, String month, int type, int gender){
        petBean.setPetBeanName(name);
        if (date.equals("")){
            petBean.setPetBeanBirthYear(Integer.parseInt(year));
            if (!month.equals(""))
                petBean.setPetBeanBirthMonth(Integer.parseInt(month));
            else
                petBean.setPetBeanBirthMonth(0);
            petBean.setPetBeanBirthDay(0);
        }
        else {
            String[] dateValues = date.split("-");
            petBean.setPetBeanBirthDay(Integer.parseInt(dateValues[0]));
            petBean.setPetBeanBirthMonth(Integer.parseInt(dateValues[1]));
            petBean.setPetBeanBirthYear(Integer.parseInt(dateValues[2]));
        }
        petBean.setPetBeanGender(gender);
        petBean.setPetBeanType(type);
        petBean.setPetBeanShelter(Session.getCurrentSession().getShelterBean().getShelterBeanId());
    }

    public void setGeneralInfo(int coatLength, String vaccinated, String microchipped, String dewormed, String sterilized, String disability, String disabilityType){
        petInformationBeanBuilder = PetInformationBeanBuilder.newPetBean()
                .coatLength(coatLength)
                .vaccinated(vaccinated.equals("1"))
                .microchipped(microchipped.equals("1"))
                .dewormed(dewormed.equals("1"))
                .sterilized(sterilized.equals("1"))
                .disability(disability.equals("1"))
                .disabilityType(disabilityType);

    }

    public void setCompatibilityWithPets(String maleDog, String femaleDog, String maleCat, String femaleCat){
        petInformationBeanBuilder
                .maleDog(maleDog.equals("1"))
                .femaleDog(femaleDog.equals("1"))
                .maleCat(maleCat.equals("1"))
                .femaleCat(femaleCat.equals("1"));
    }
    public void setOtherCompatibility(String children, String elders, String sleepOutside, String firstExperience, int hoursAlone){
        petInformationBeanBuilder
                .children(children.equals("1"))
                .elders(elders.equals("1"))
                .sleepOutside(sleepOutside.equals("1"))
                .firstExperience(firstExperience.equals("1"))
                .hoursAlone(hoursAlone);
    }

    public void setDogInfo(int size, String education) {
        petInformationBean = petInformationBeanBuilder
                .size(size)
                .dogEducation(education.equals("1"))
                .build();
    }

    public void setCatInfo(String testFiv, String testFelv) {
        petInformationBean = petInformationBeanBuilder
                .testFiv(testFiv.equals("1"))
                .testFelv(testFelv.equals("1"))
                .build();
    }


    public void complete(){
        AddPetController addPetController = new AddPetController(petBean, petInformationBean);
        try {
            addPetController.addNewPet(null);
        }
        catch (PetDateOfBirthException e) {
            ShowExceptionSupport.showExceptionGUI(e.getMessage());
        }
    }

}
