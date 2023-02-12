package com.ispwproject.adoptme.view.cli;

import com.ispwproject.adoptme.controller.graficcontroller.cli.CLIUserSettingsController;
import com.ispwproject.adoptme.engineering.bean.UserBean;
import com.ispwproject.adoptme.engineering.session.Session;
import com.ispwproject.adoptme.engineering.utils.CLIPrintSettingsSupport;

public class CLIUserSettingsView {
    private final CLIUserSettingsController controller;

    public CLIUserSettingsView(CLIUserSettingsController controller) {
        this.controller = controller;
    }
    public void run() {
        UserBean user = Session.getCurrentSession().getUserBean();

        CLIPrintSettingsSupport.printSettings("\tName: " + user.getName() + "\n\tSurname: " + user.getSurname() + "\n\tEmail: " + user.getEmail(), " 1) Modify name.\n 2) Modify surname.\n 3) Modify email.\n 4) Modify password.\n 5) Logout.", controller, this );
    }
}
