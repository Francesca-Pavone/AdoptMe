package com.ispwproject.adoptme.controller.graficcontroller.cli.requests;

import com.ispwproject.adoptme.controller.appcontroller.ManageRequestController;
import com.ispwproject.adoptme.controller.graficcontroller.cli.CLIGraficController;
import com.ispwproject.adoptme.engineering.bean.RequestBean;
import com.ispwproject.adoptme.engineering.exception.francesca.*;
import com.ispwproject.adoptme.engineering.utils.ShowExceptionSupport;
import com.ispwproject.adoptme.view.cli.requests.CLIManageSendRequestView;


public class CLIManageSendRequestController implements CLIGraficController {

    private static final String DELETE = "1";
    private static final String MODIFY = "2";
    private static final String BACK = "3";
    private final CLIManageSendRequestView view;
    private final RequestBean requestBean;
    private CLIAppointmentsPageController previousPage;
    public CLIManageSendRequestController(RequestBean requestBean) {
        this.requestBean = requestBean;
        this.view = new CLIManageSendRequestView(this);

    }

    public void setPreviousPage(CLIAppointmentsPageController previousPage) {
        this.previousPage = previousPage;
    }

    @Override
    public void start(){
        this.view.showRequestNow(this.requestBean.getDate(), this.requestBean.getTime());
        this.view.showForm();
    }

    public void executeCommand(String command) throws CommandNotFoundException {
        switch (command) {
            case DELETE -> annulRequest();

            case MODIFY -> {
                String date = requestBean.getDate();
                String time = requestBean.getTime();
                setNewDate();
                setNewTime();
                modifyRequest(date, time);
            }

            case BACK -> this.previousPage.showAppointments(this.requestBean.getUserName());

            default -> throw new CommandNotFoundException();
        }
    }

    private void modifyRequest(String date, String time) {
        try {
            if (requestBean.getDate().equals(date) && requestBean.getTime().equals(time))
                throw new DuplicateRequestException();
            if (view.askConfirmation() == 1){
                ManageRequestController manageRequestController = new ManageRequestController();
                this.requestBean.register(this.previousPage);
                manageRequestController.updateRequest(requestBean, requestBean);
            }
        }
        catch (PastDateException | NotFoundException | DuplicateRequestException e){
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
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
            this.requestBean.register(this.previousPage);
            try {
                manageRequestController.deleteRequest(this.requestBean, this.requestBean);
            } catch (NotFoundException e) {
                ShowExceptionSupport.showExceptionCLI(e.getMessage());
            }
        }
        this.previousPage.showAppointments(this.requestBean.getUserName());
    }
}
