package com.ispwproject.adoptme.engineering.utils;

public class PrintSupport {
    private PrintSupport() {
        //Costruttore privato perché ho tutti i metodi statici
    }

    public static void printError(String errorMsg) {
        System.err.println(errorMsg);
    }

    public static void printMessage(String msg){
        System.out.println(msg);
    }

    public static void printSeparatorLine(){
        System.out.println("-----------------------------------------------------------------------------------------------");
    }

}
