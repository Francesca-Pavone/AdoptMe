package com.ispwproject.adoptme.controller.graficcontroller.cli;

import com.ispwproject.adoptme.controller.graficcontroller.cli.requests.CLIAppointmentsPageController;
import com.ispwproject.adoptme.engineering.utils.PrintSupport;
import com.ispwproject.adoptme.view.cli.CLIQuestionnaireView;
import com.ispwproject.adoptme.view.cli.CLIUserHomepageView;
import com.ispwproject.adoptme.controller.appcontroller.UserResearchController;
import com.ispwproject.adoptme.engineering.bean.ShelterBean;
import com.ispwproject.adoptme.engineering.bean.UserResearchBean;
import com.ispwproject.adoptme.engineering.session.Session;
import com.ispwproject.adoptme.view.cli.CLIUserSettingsView;

import java.util.List;

public class CLIUserHomepageController {
    private static final String SEARCH_CITY = "1";
    private static final String SEARCH_SHELTER = "2";
    private static final String QUESTIONNAIRE = "3";
    private static final String FAVORITES = "4";
    private static final String APPOINTMENTS = "5";
    private static final String SETTINGS = "6";

    private static final String MSG_ERROR = "Input not valid. Try with: 1 | 2 | 3 | 4 | 5 | 6.";

    public void executeCommand(String input) throws Exception {
        if(Session.getCurrentSession().getUserBean() == null) {
            switch (input) {
                case QUESTIONNAIRE -> CLIQuestionnaireView.main();
                case SEARCH_CITY -> CLIUserHomepageView.searchCity();

                // vai a search shelter
                case SEARCH_SHELTER -> CLIUserHomepageView.searchShelter();
                default -> PrintSupport.printError(MSG_ERROR);
            }
        }

        if (Session.getCurrentSession().getUserBean() != null) {
            switch (input) {
                case QUESTIONNAIRE -> CLIQuestionnaireView.main();
                case SEARCH_CITY -> CLIUserHomepageView.searchCity();
                // vai a search shelter
                case SEARCH_SHELTER -> CLIUserHomepageView.searchShelter();
                case FAVORITES -> {
                    //vai a favoriti
                }
                case APPOINTMENTS -> {
                    CLIAppointmentsPageController cliAppointmentsPageController = new CLIAppointmentsPageController();
                    cliAppointmentsPageController.start();
                }
                case SETTINGS -> {
                    CLIUserSettingsController cliUserSettingsController = new CLIUserSettingsController();
                    cliUserSettingsController.start();
                }
                default -> PrintSupport.printError(MSG_ERROR);
            }
        }
    }

    public void showShelter(ShelterBean shelterBean) throws Exception {
        CLIShelterInfoController cliShelterInfoController = new CLIShelterInfoController();
        cliShelterInfoController.setData(shelterBean);
    }

    public void searchCity(String city) throws Exception {
        UserResearchBean userResearchBean = new UserResearchBean();
        userResearchBean.setCityShelter(city);
        UserResearchController userResearchController = new UserResearchController();
        List<ShelterBean> shelterList = userResearchController.searchCity(userResearchBean);
        CLIUserHomepageView.showShelterList(shelterList);
    }

    public void searchShelter(String shelterName) throws Exception {
        CLIShelterInfoController cliShelterInfoController = new CLIShelterInfoController();
        cliShelterInfoController.setShelterData(shelterName);
    }
}
