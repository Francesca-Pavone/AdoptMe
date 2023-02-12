package com.ispwproject.adoptme.controller.graficcontroller.cli;

import com.ispwproject.adoptme.engineering.bean.PetBean;
import com.ispwproject.adoptme.engineering.utils.ComputeAgeSupport;
import com.ispwproject.adoptme.engineering.utils.ComputeGenderSupport;
import com.ispwproject.adoptme.view.cli.CLIQuestionnaireResultView;

import java.util.List;

public class CLIQuestionnaireResultController {

    private final CLIQuestionnaireResultView cliQuestionnaireResultView;
    private List<PetBean> petBeanList;

    public CLIQuestionnaireResultController() {
        this.cliQuestionnaireResultView = new CLIQuestionnaireResultView(this);
    }

    public void setData(List<PetBean> petList) {
        this.cliQuestionnaireResultView.run();
        int i = 1;
        this.petBeanList = petList;
        for(PetBean petBean: petList) {
            String gender = ComputeGenderSupport.computeGender(petBean);
            String age = ComputeAgeSupport.computeAge(petBean);
            this.cliQuestionnaireResultView.printPet((petBean).getPetBeanName(), gender, age, i);
            i++;
        }
        this.cliQuestionnaireResultView.printCommands();
    }

    public void executeCommand(int i) {
        if (i == 0) {
            this.goBack();
        }
        else {
            PetBean petBean = this.petBeanList.get(i-1);
            this.petBeanList.clear();
            CLIPetInformationController cliPetInformationController = new CLIPetInformationController(petBean);
            cliPetInformationController.setIndex(i);
            cliPetInformationController.setPreviousPage(this);
            cliPetInformationController.start();
        }
    }

    private void goBack() {
        CLIUserHomepageController cliUserHomepageController = new CLIUserHomepageController();
        cliUserHomepageController.start();
    }
}
