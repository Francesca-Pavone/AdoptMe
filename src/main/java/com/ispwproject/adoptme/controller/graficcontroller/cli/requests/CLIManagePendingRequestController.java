package com.ispwproject.adoptme.controller.graficcontroller.cli.requests;

import com.ispwproject.adoptme.controller.appcontroller.ManageRequestController;
import com.ispwproject.adoptme.controller.graficcontroller.cli.CLIGraficController;
import com.ispwproject.adoptme.engineering.bean.RequestBean;
import com.ispwproject.adoptme.engineering.exception.CommandNotFoundException;
import com.ispwproject.adoptme.engineering.exception.DateFormatException;
import com.ispwproject.adoptme.engineering.exception.TimeFormatException;
import com.ispwproject.adoptme.engineering.utils.ShowExceptionSupport;
import com.ispwproject.adoptme.view.cli.requests.CLIManagePendingRequestView;

public class CLIManagePendingRequestController implements CLIGraficController {
    private static final String ACCEPT = "1";
    private static final String DELETE = "2";
    private static final String MODIFY = "3";
    private static final String BACK = "4";
    private final RequestBean requestBean;
    private final CLIManagePendingRequestView view;
    private CLIAppointmentsPageController previousPage;

    public CLIManagePendingRequestController(RequestBean requestBean) {
        this.requestBean = requestBean;
        this.view = new CLIManagePendingRequestView(this);

    }

    @Override
    public void start(){
        this.view.showRequestNow(this.requestBean.getDate(), this.requestBean.getTime());
        this.view.showForm();
    }

    public void setPreviousPage(CLIAppointmentsPageController previousPage) {
        this.previousPage = previousPage;
    }

    public void executeCommand(String command) throws CommandNotFoundException {
        switch (command) {
            case ACCEPT -> acceptRequest();

            case DELETE -> annulRequest();

            case MODIFY -> {
                setNewDate();
                setNewTime();
                modifyRequest();
            }

            case BACK -> this.previousPage.showAppointments(this.requestBean.getUserName());

            default -> throw new CommandNotFoundException();

        }
    }

    private void acceptRequest() {
        if (this.view.askConfirmation() == 1) {
            ManageRequestController manageRequestController = new ManageRequestController();
            try {
                manageRequestController.acceptRequest(this.requestBean, this.requestBean, null, this.previousPage);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        this.previousPage.showAppointments(this.requestBean.getUserName());

    }

    public void modifyRequest()  {

        if (view.askConfirmation() == 1){
            ManageRequestController manageRequestController = new ManageRequestController();
            try {
                manageRequestController.modifyRequest(requestBean, requestBean, null, this.previousPage);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        this.previousPage.showAppointments(this.requestBean.getUserName());
    }

    private void setNewTime() {
        while (true) {
            try {
                this.requestBean.setTime(this.view.askTime());
                return;
            }
            catch (TimeFormatException e) {
                ShowExceptionSupport.showExceptionCLI(e.getMessage());
            }
        }
    }

    private void setNewDate() {
        while (true) {
            try {
                this.requestBean.setDate(this.view.askDate());
                return;
            }
            catch (DateFormatException e) {
                ShowExceptionSupport.showExceptionCLI(e.getMessage());
            }
        }
    }

    private void annulRequest() {
        if (this.view.askConfirmation() == 1) {
            ManageRequestController manageRequestController = new ManageRequestController();
            try {
                manageRequestController.deleteRequest(this.requestBean, this.requestBean, this.previousPage);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        this.previousPage.showAppointments(this.requestBean.getUserName());
    }
}
