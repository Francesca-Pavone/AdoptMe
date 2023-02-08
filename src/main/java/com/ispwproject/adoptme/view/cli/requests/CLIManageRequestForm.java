package com.ispwproject.adoptme.view.cli.requests;

import com.ispwproject.adoptme.engineering.utils.PrintSupport;

public abstract class CLIManageRequestForm {
    public void showRequestNow(String date, String time) {
        PrintSupport.printMessage("\n----------------  YOUR REQUEST NOW  ----------------");
        PrintSupport.printMessage("\tDate: "+ date + "\n\tTime: " + time);
    }
    public abstract void showForm();
}
