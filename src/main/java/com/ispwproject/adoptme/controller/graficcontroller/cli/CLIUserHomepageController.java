package com.ispwproject.adoptme.controller.graficcontroller.cli;

import com.ispwproject.adoptme.controller.graficcontroller.cli.requests.CLIAppointmentsPageController;
import com.ispwproject.adoptme.engineering.exception.NoCityFoundException;
import com.ispwproject.adoptme.engineering.utils.PrintSupport;
import com.ispwproject.adoptme.engineering.utils.ScannerSupport;
import com.ispwproject.adoptme.engineering.utils.ShowExceptionSupport;
import com.ispwproject.adoptme.view.cli.CLIQuestionnaireView;
import com.ispwproject.adoptme.view.cli.CLIUserHomepageView;
import com.ispwproject.adoptme.controller.appcontroller.UserResearchController;
import com.ispwproject.adoptme.engineering.bean.ShelterBean;
import com.ispwproject.adoptme.engineering.bean.UserResearchBean;
import com.ispwproject.adoptme.engineering.session.Session;

import java.util.List;

public class CLIUserHomepageController implements CLIGraficController{
    private final CLIUserHomepageView view;

    private static final String SEARCH_CITY = "1";
    private static final String SEARCH_SHELTER = "2";
    private static final String QUESTIONNAIRE = "3";
    private static final String FAVORITES = "4";
    private static final String APPOINTMENTS = "5";
    private static final String SETTINGS = "6";

    private static final String MSG_ERROR = "Input not valid. Try with: 1 | 2 | 3";

    public CLIUserHomepageController() {
        this.view = new CLIUserHomepageView(this);
    }

    @Override
    public void start() {
        this.view.run();
    }
    public void executeCommand(String input) throws Exception {
        if(Session.getCurrentSession().getUserBean() == null) {
            switch (input) {
                case QUESTIONNAIRE -> CLIQuestionnaireView.main();
                case SEARCH_CITY -> this.view.searchCity();

                // vai a search shelter
                case SEARCH_SHELTER -> this.view.searchShelter();
                default ->  {
                    PrintSupport.printError(MSG_ERROR);
                    if (Session.getCurrentSession().getUserBean() != null)
                        PrintSupport.printError(" 4 | 5 | 6");
                    PrintSupport.printError("\n\tPress ENTER to continue");
                    ScannerSupport.waitEnter();
                    start();
                }
            }
        }

        if (Session.getCurrentSession().getUserBean() != null) {
            switch (input) {
                case QUESTIONNAIRE -> CLIQuestionnaireView.main();
                case SEARCH_CITY -> this.view.searchCity();
                // vai a search shelter
                case SEARCH_SHELTER -> this.view.searchShelter();
                case FAVORITES -> {
                    CLIUserFavoritesController cliUserFavoritesController = new CLIUserFavoritesController();
                    cliUserFavoritesController.start();
                    cliUserFavoritesController.setPreviousPage(this.view);
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

    public void showShelter(ShelterBean shelterBean) {
        CLIShelterInfoController cliShelterInfoController = new CLIShelterInfoController();
        cliShelterInfoController.setPreviousPage(this);
        cliShelterInfoController.setShelter(shelterBean.getName());
    }

    public void searchCity(String city) throws Exception {
        try {
            UserResearchBean userResearchBean = new UserResearchBean();
            userResearchBean.setCityShelter(city);
            UserResearchController userResearchController = new UserResearchController();
            List<ShelterBean> shelterList = userResearchController.searchCity(userResearchBean);
            this.view.showShelterList(shelterList);
        } catch(NoCityFoundException e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
            ScannerSupport.waitEnter();
            this.view.run();
        }
    }

    public void searchShelter(String shelterName) {
        CLIShelterInfoController cliShelterInfoController = new CLIShelterInfoController();
        cliShelterInfoController.setPreviousPage(this);
        cliShelterInfoController.setShelter(shelterName);
    }
}
