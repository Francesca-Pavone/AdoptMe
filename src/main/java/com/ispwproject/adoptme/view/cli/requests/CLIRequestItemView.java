package com.ispwproject.adoptme.view.cli.requests;

import com.ispwproject.adoptme.engineering.utils.PrintSupport;

public class CLIRequestItemView {

    public void showRequestInfo(int id, String status, String userName, String petName, String date, String time) {
        PrintSupport.printMessage("ID -> " + id + "\n\t*****  " + status + "  *****\n\tUser name: " + userName + "\n\tPet name: " + petName + "\n\tDate: " + date + "\n\tTime: " + time + "\n");
        PrintSupport.printSeparatorLine();
    }

    public void showStatus(String status) {
        PrintSupport.printMessage("***************\n|\t" + status + "\t|\n***************\n");
    }
}
