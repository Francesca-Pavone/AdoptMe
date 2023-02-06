package com.ispwproject.adoptme.controller.graficcontroller.cli.requests;

import com.ispwproject.adoptme.controller.appcontroller.ManageRequestController;
import com.ispwproject.adoptme.engineering.bean.RequestBean;
import com.ispwproject.adoptme.engineering.exception.CommandNotFoundException;
import com.ispwproject.adoptme.view.cli.requests.CLIManageRejectedRequestView;


public class CLIManageRejectedRequestController {
    private CLIManageRejectedRequestView view;
    private RequestBean requestBean;
    private CLIAppointmentsPageController previousPage;
    private static final String DELETE = "1";
    private static final String BACK = "2";

    public CLIManageRejectedRequestController(RequestBean requestBean) {
        this.requestBean = requestBean;
    }
    public void setPreviousPage(CLIAppointmentsPageController previousPage) {
        this.previousPage = previousPage;
    }


    public void start() {
        this.view = new CLIManageRejectedRequestView(this);
        this.view.showRequestNow(this.requestBean.getDate(),
                this.requestBean.getTime());
        this.view.showForm();
    }

    public void executeCommand(String command) throws Exception {
        switch (command) {
            case DELETE -> deleteRejectedRequest();

            case BACK -> this.previousPage.showAppointments(this.requestBean.getUserName());

            default -> throw new CommandNotFoundException();
        }
    }

    private void deleteRejectedRequest() throws Exception {
        if (this.view.askConfirmDelete() == 1) {
            ManageRequestController manageRequestController = new ManageRequestController();
            try {
                manageRequestController.deleteRequest(requestBean, requestBean, this.previousPage);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        this.previousPage.showAppointments(this.requestBean.getUserName());
    }
}