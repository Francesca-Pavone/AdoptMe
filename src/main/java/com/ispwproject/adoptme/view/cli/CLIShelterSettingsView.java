package com.ispwproject.adoptme.view.cli;

import com.ispwproject.adoptme.controller.graficcontroller.cli.CLIShelterSettingsController;
import com.ispwproject.adoptme.engineering.bean.ShelterBean;
import com.ispwproject.adoptme.engineering.session.Session;
import com.ispwproject.adoptme.engineering.utils.CLIPrintSettingsSupport;

public class CLIShelterSettingsView {
    private final CLIShelterSettingsController controller;

    public CLIShelterSettingsView(CLIShelterSettingsController controller) {
        this.controller = controller;
    }

    public void run() {
        ShelterBean shelterBean = Session.getCurrentSession().getShelterBean();

        CLIPrintSettingsSupport.printSettings("\tName: " + shelterBean.getShelterBeanName() + "\n\tPhone Number: " + shelterBean.getBeanPhoneNumber() + "\n\tAddress: " + shelterBean.getBeanAddress() + "\n\tWeb Site: " + shelterBean.getBeanWebSite() +"\n\tEmail: " + shelterBean.getBeanEmail(), " 1) Modify name.\n 2) Modify Phone Number.\n 3) Modify Address.\n 4) Modify Web Site.\n 5) Modify Email.\n 6) Modify Password.\n 7) Logout.", controller, this);
    }
}
