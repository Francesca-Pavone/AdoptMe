package com.ispwproject.adoptme.controller.graficcontroller.cli;

import com.ispwproject.adoptme.engineering.bean.PetBean;
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
            String gender;
            try {
                gender = (switch ((petBean).getGender()) {
                    case 1 -> "Female";
                    default -> "Male";
                });
                this.cliQuestionnaireResultView.printPet((petBean).getName(), gender, (petBean).getAge(), i);
                i++;
            } catch (Exception e) {
                e.printStackTrace();
            }
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
