package com.ispwproject.adoptme.view.cli;

import com.ispwproject.adoptme.controller.graficcontroller.cli.CLIPetInformationController;
import com.ispwproject.adoptme.controller.graficcontroller.cli.CLIShelterInfoController;
import com.ispwproject.adoptme.engineering.bean.PetBean;
import com.ispwproject.adoptme.engineering.bean.ShelterBean;
import com.ispwproject.adoptme.engineering.utils.PrintSupport;

import java.util.List;
import java.util.Scanner;

public class CLIShelterInfoView {
    public static void showShelter(ShelterBean shelterBean, List<PetBean> petBeanList) throws Exception {
        PrintSupport.printMessage("\n\n-------------------------------------- " + shelterBean.getName().toUpperCase() + " --------------------------------------");
        PrintSupport.printMessage("------------------------------------- Shelter Information -------------------------------------\n  Email: " + shelterBean.getEmail() + "\n  Phone number: " + shelterBean.getPhoneNumber() + "\n  Web site: " + shelterBean.getWebSite() + "\n  Address: " + shelterBean.getAddress() + ", " + shelterBean.getCity());
        PrintSupport.printMessage("--------------------------------------- Shelter's pets ---------------------------------------");
        int i = 1;
        String gender, type;
        for(PetBean petBean: petBeanList) {
            PrintSupport.printMessage( i + ")  Name: " + petBean.getName());
            gender = (switch (petBean.getGender()) {
                case 1 -> "Female";
                default -> "Male";
            });
            type = (switch (petBean.getType()) {
                case 1 -> "Cat";
                default -> "Dog";
            });
            PrintSupport.printMessage("\n\tType: " + type);
            PrintSupport.printMessage("\n\tGender: " + gender);
            PrintSupport.printMessage("\n\tAge: " + petBean.getAge());
            PrintSupport.printSeparatorLine();
            i++;
        }

        PrintSupport.printMessage("\nInsert the number of the pet you want to see\n----- or\nInsert 0 to go back to Homepage");
        Scanner scanner = new Scanner(System.in);

        int input = scanner.nextInt();

        if (input == 0) {
            CLIShelterInfoController cliShelterInfoController = new CLIShelterInfoController();
            cliShelterInfoController.goBack();
        }
        else {
            PetBean petBean = petBeanList.get(input - 1);
            CLIPetInformationController cliPetInformationController = new CLIPetInformationController(petBean);
            cliPetInformationController.setPetInfo();
        }
    }
}
