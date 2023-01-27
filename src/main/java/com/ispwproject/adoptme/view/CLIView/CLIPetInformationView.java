package com.ispwproject.adoptme.view.CLIView;

import java.util.Scanner;

public class CLIPetInformationView {

    public static void setData(String name, String dayOfBirth, String monthOfBirth, String yearOfBirth, String type, String gender, String coatLenght, String dogSize, String dogEducation, String vaccinated, String microchipped, String dewormed, String sterilized, String testFiv, String testFelv, String disability, String disabilityType, String compatibility) {
        System.out.println("\n---------------------------------------- " + name + " ----------------------------------------");
        System.out.println("   Name: " + name);
        if(!dayOfBirth.equals("") && !monthOfBirth.equals(""))
            System.out.println("   Date of birth: " + dayOfBirth + " / " + monthOfBirth + " / " + yearOfBirth);
        else if(!dayOfBirth.equals(""))
            System.out.println("   Date of birth: " + monthOfBirth + " / " + yearOfBirth);
        else
            System.out.println("   Year of birth: " + yearOfBirth);
        System.out.println("   Type: " + type);
        System.out.println("   Gender: " + gender);
        System.out.println("   Coat length: " + coatLenght);
        if(!dogSize.equals(""))
            System.out.println("   Size: " + dogSize);
        if(type.equals("Cat") && disability.equals("Disability"))
            System.out.println("   General informations:\n   " + vaccinated + "\n            " + microchipped + "\n            " + dewormed + "\n            " + sterilized + "\n            " + testFiv + "\n            " + testFelv + "\n            " + disability + "( " + disabilityType + " )");
        else if(type.equals("Cat"))
            System.out.println("   General informations:\n   " + vaccinated + "\n            " + microchipped + "\n            " + dewormed + "\n            " + sterilized + "\n            " + testFiv + "\n            " + testFelv );
        else if(type.equals("Dog") && disability.equals("Disability"))
            System.out.println("   General informations:\n   " + vaccinated + "\n            " + microchipped + "\n            " + dewormed + "\n            " + sterilized + "\n            " + disability + "( " + disabilityType + " )" + "\n            " + dogEducation);
        else if(type.equals("Dog"))
            System.out.println("   General informations:\n   " + vaccinated + "\n            " + microchipped + "\n            " + dewormed + "\n            " + sterilized + "\n            " + dogEducation);
        System.out.println("   Compatibility:\n" + compatibility);

        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine();
    }
}
