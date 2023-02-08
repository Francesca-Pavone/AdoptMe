package com.ispwproject.adoptme.controller.graficcontroller.cli.requests;

import com.ispwproject.adoptme.controller.appcontroller.ManageRequestController;
import com.ispwproject.adoptme.controller.graficcontroller.cli.CLIGraficController;
import com.ispwproject.adoptme.engineering.bean.RequestBean;
import com.ispwproject.adoptme.engineering.exception.francesca.CommandNotFoundException;
import com.ispwproject.adoptme.engineering.exception.francesca.NotFoundException;
import com.ispwproject.adoptme.engineering.utils.ShowExceptionSupport;
import com.ispwproject.adoptme.view.cli.requests.CLIManageRejectedRequestView;


public class CLIManageRejectedRequestController implements CLIGraficController {
    private final CLIManageRejectedRequestView view;
    private final RequestBean requestBean;
    private CLIAppointmentsPageController previousPage;
    private static final String DELETE = "1";
    private static final String BACK = "2";

    public CLIManageRejectedRequestController(RequestBean requestBean) {
        this.requestBean = requestBean;
        this.view = new CLIManageRejectedRequestView(this);
    }
    public void setPreviousPage(CLIAppointmentsPageController previousPage) {
        this.previousPage = previousPage;
    }

    @Override
    public void start() {
        this.view.showRequestNow(this.requestBean.getDate(),
                this.requestBean.getTime());
        this.view.showForm();
    }

    public void executeCommand(String command) throws CommandNotFoundException {
        switch (command) {
            case DELETE -> deleteRejectedRequest();

            case BACK -> this.previousPage.showAppointments(this.requestBean.getUserName());

            default -> throw new CommandNotFoundException();
        }
    }

    private void deleteRejectedRequest() {
        if (this.view.askConfirmDelete() == 1) {
            ManageRequestController manageRequestController = new ManageRequestController();
            this.requestBean.register(this.previousPage);
            try {
                manageRequestController.deleteRequest(requestBean, requestBean);
            }  catch (NotFoundException e) {
                ShowExceptionSupport.showExceptionCLI(e.getMessage());
            }
        }
        this.previousPage.showAppointments(this.requestBean.getUserName());
    }
}