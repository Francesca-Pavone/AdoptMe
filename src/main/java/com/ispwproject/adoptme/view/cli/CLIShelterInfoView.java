package com.ispwproject.adoptme.view.cli;

import com.ispwproject.adoptme.controller.graficcontroller.cli.CLIPetInformationController;
import com.ispwproject.adoptme.controller.graficcontroller.cli.CLIShelterInfoController;
import com.ispwproject.adoptme.engineering.bean.PetBean;
import com.ispwproject.adoptme.engineering.bean.ShelterBean;

import java.util.List;
import java.util.Scanner;

public class CLIShelterInfoView {
    public static void showShelter(ShelterBean shelterBean, List<PetBean> petBeanList) throws Exception {
        System.out.println("\n\n-------------------------------------- " + shelterBean.getName().toUpperCase() + " --------------------------------------");
        System.out.println("------------------------------------- Shelter Information -------------------------------------\n  Email: " + shelterBean.getEmail() + "\n  Phone number: " + shelterBean.getPhoneNumber() + "\n  Web site: " + shelterBean.getWebSite() + "\n  Address: " + shelterBean.getAddress() + ", " + shelterBean.getCity());
        System.out.println("--------------------------------------- Shelter's pets ---------------------------------------");
        int i = 1;
        String gender;
        for(PetBean petBean: petBeanList) {
            System.out.println("    " + i + ") Name: " + petBean.getName() + "     ");
            gender = (switch (petBean.getGender()) {
                case 1 -> "Female";
                default -> "Male";
            });
            System.out.println("\n       Gender: " + gender + "        ");
            System.out.println("\n       Age: " + petBean.getAge() + "      ");
            System.out.println("-----------------------------------------------------------------------------------------------");
            i++;
        }

        System.out.println("\nInsert the number of the pet you want to see:");
        System.out.println(  "********** or insert 0 to go back  **********");
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
