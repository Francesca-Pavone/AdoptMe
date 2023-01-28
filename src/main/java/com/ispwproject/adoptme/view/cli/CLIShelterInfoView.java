package com.ispwproject.adoptme.view.cli;

import com.ispwproject.adoptme.controller.graficcontroller.cli.CLIShelterInfoController;
import com.ispwproject.adoptme.engineering.bean.PetBean;
import com.ispwproject.adoptme.engineering.bean.ShelterBean;

import java.util.List;
import java.util.Scanner;

public class CLIShelterInfoView {
    public static void showShelter(ShelterBean shelterBean, List<PetBean> petBeanList) {
        System.out.println("\n\n----------------------------------- " + shelterBean.getName() + " -----------------------------------");
        System.out.println("\nInsert the pet's name you want to view");
        System.out.println("\n---------------------------");
        for(PetBean petBean: petBeanList) {
            System.out.println("\n|     " + petBean.getName() + "     |");
            System.out.println("\n|       " + petBean.getGender() + "       |");
            System.out.println("\n|     " + petBean.getAge() + "     |");
            System.out.println("\n---------------------------");
        }
        Scanner scanner = new Scanner(System.in);
        String petName = scanner.nextLine();

        CLIShelterInfoController cliShelterInfoController = new CLIShelterInfoController();
        cliShelterInfoController.showPet(petName);
    }
}
