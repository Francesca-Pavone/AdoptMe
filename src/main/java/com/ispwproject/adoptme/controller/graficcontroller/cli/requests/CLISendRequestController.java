package com.ispwproject.adoptme.controller.graficcontroller.cli.requests;

import com.ispwproject.adoptme.controller.appcontroller.ManageRequestController;
import com.ispwproject.adoptme.controller.graficcontroller.cli.CLIGraficController;
import com.ispwproject.adoptme.controller.graficcontroller.cli.CLIPetInformationController;
import com.ispwproject.adoptme.engineering.bean.PetBean;
import com.ispwproject.adoptme.engineering.bean.RequestBean;
import com.ispwproject.adoptme.engineering.bean.UserBean;
import com.ispwproject.adoptme.engineering.exception.*;
import com.ispwproject.adoptme.engineering.observer.Observer;
import com.ispwproject.adoptme.engineering.session.Session;
import com.ispwproject.adoptme.engineering.utils.ShowExceptionSupport;
import com.ispwproject.adoptme.view.cli.requests.CLISendRequestView;


public class CLISendRequestController implements CLIGraficController, Observer {

    private static final String SEND = "";
    private static final String BACK = "1";

    private final CLISendRequestView view;
    private RequestBean requestBean;
    private final PetBean petBean;

    public CLISendRequestController(PetBean petBean) {
        this.view = new CLISendRequestView();
        this.petBean = petBean;
    }

    @Override
    public void start() {
        UserBean user = Session.getCurrentSession().getUserBean();
        this.requestBean = new RequestBean(petBean.getPetBeanName(), petBean.getPetBeanId(), petBean.getPetBeanShelter(), user.getName(), user.getUserId());
        this.view.showSendRequestForm(requestBean.getUserName(), requestBean.getPetName(), this);
    }

    public void setRequestDate(String date) throws DateFormatException {
        this.requestBean.setDate(date);
    }

    public void setRequestTime(String time) throws TimeFormatException {
        this.requestBean.setTime(time);
    }

    public void executeCommand(String command) {
        if (command.equals(SEND)){
            ManageRequestController manageRequestController = new ManageRequestController();
            this.requestBean.register(this);
            try {
                manageRequestController.sendRequest(this.petBean, this.requestBean);
            }
            catch (PastDateException | DuplicateRequestException | NotFoundException e) {
                ShowExceptionSupport.showExceptionCLI(e.getMessage());
                start();
            }
        }
        else if (command.equals(BACK)){
            CLIPetInformationController cliPetInformationController = new CLIPetInformationController(petBean);
            cliPetInformationController.start();
        }

    }

    @Override
    public void update(Object object) {
        view.showSuccessful();
        CLIPetInformationController cliPetInformationController = new CLIPetInformationController(this.petBean);
        cliPetInformationController.start();
    }

    @Override
    public void update2(Object object1, Object object2) {
        //ignore
    }

}










