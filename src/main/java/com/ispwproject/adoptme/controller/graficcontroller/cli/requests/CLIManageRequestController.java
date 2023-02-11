package com.ispwproject.adoptme.controller.graficcontroller.cli.requests;

import com.ispwproject.adoptme.controller.appcontroller.ManageRequestController;
import com.ispwproject.adoptme.controller.graficcontroller.cli.CLIGraficController;
import com.ispwproject.adoptme.engineering.bean.RequestBean;
import com.ispwproject.adoptme.engineering.exception.*;
import com.ispwproject.adoptme.engineering.session.Session;
import com.ispwproject.adoptme.engineering.utils.ShowExceptionSupport;
import com.ispwproject.adoptme.view.cli.requests.CLIManageRequestView;

public class CLIManageRequestController implements CLIGraficController {

    private final RequestBean requestBean;
    private final CLIManageRequestView view;
    private CLIAppointmentsPageController previousPage;
    private final int status;

    public CLIManageRequestController(RequestBean requestBean) {
        this.requestBean = requestBean;
        Session session = Session.getCurrentSession();
        if ((session.getUserBean() != null && requestBean.getStatus() == 0) || (session.getShelterBean() != null && requestBean.getStatus() == 1))
            status = 0;
        else if ((session.getShelterBean() != null && requestBean.getStatus() == 0) || (session.getUserBean() != null && requestBean.getStatus() == 1))
            status = 1;
        else if (requestBean.getStatus() == 2)
            status = 2; //accepted
        else
            status = 3; //rejected

        this.view = new CLIManageRequestView(this);
    }

    @Override
    public void start(){
        if (status != 2)
            this.view.showRequestNow(this.requestBean.getDate(), this.requestBean.getTime());
        this.view.showForm(status, requestBean.getId(), requestBean.getDate(), requestBean.getTime());
    }

    public void setPreviousPage(CLIAppointmentsPageController previousPage) {
        this.previousPage = previousPage;
    }

    public void executeCommand(String command) throws CommandNotFoundException {

        if (status == 0)
            manageSentRequest(command);
        else if (status == 1)
            managePendingRequest(command);
        else
           manageRejectedRequest(command);
    }

    private void manageRejectedRequest(String command) throws CommandNotFoundException {
        switch (command) {
            case "1" -> annulRequest();

            case "2" -> this.previousPage.showAppointments(this.requestBean.getUserName());

            default -> throw new CommandNotFoundException("1 | 2");
        }
    }

    private void manageSentRequest(String command) throws CommandNotFoundException {
        switch (command) {
            case "1" -> annulRequest();

            case "2" -> {
                String date = requestBean.getDate();
                String time = requestBean.getTime();
                setNewDate();
                setNewTime();
                modifyRequest(date, time);
            }

            case "3" -> this.previousPage.showAppointments(this.requestBean.getUserName());

            default -> throw new CommandNotFoundException("1 | 2 | 3");
        }
    }

    private void managePendingRequest(String command) throws CommandNotFoundException {
        switch (command) {
            case "1" -> acceptRequest();

            case "2" -> annulRequest();

            case "3" -> {
                String date = requestBean.getDate();
                String time = requestBean.getTime();
                setNewDate();
                setNewTime();
                modifyRequest(date, time);
            }

            case "4" -> this.previousPage.showAppointments(this.requestBean.getUserName());

            default -> throw new CommandNotFoundException("1 | 2 | 3 | 4");
        }
    }

    private void acceptRequest() {
        if (this.view.askConfirmation() == 1) {
            ManageRequestController manageRequestController = new ManageRequestController();
            this.requestBean.register(this.previousPage);
            try {
                manageRequestController.acceptRequest(this.requestBean, this.requestBean);
                this.previousPage.showAppointments(this.requestBean.getUserName());

            } catch (NotFoundException e) {
                ShowExceptionSupport.showExceptionCLI(e.getMessage());
            }
        }
        else start();
    }
    private void annulRequest() {
        if (this.view.askConfirmation() == 1) {
            ManageRequestController manageRequestController = new ManageRequestController();
            this.requestBean.register(this.previousPage);
            try {
                manageRequestController.deleteRequest(this.requestBean, this.requestBean);
                this.previousPage.showAppointments(this.requestBean.getUserName());
            } catch (NotFoundException e){
                ShowExceptionSupport.showExceptionCLI(e.getMessage());
            }
        }
        else start();
    }

    private void modifyRequest(String date, String time) {
        try {
            if (requestBean.getDate().equals(date) && requestBean.getTime().equals(time))
                throw new DuplicateRequestException();
            if (view.askConfirmation() == 1){
                ManageRequestController manageRequestController = new ManageRequestController();
                this.requestBean.register(this.previousPage);
                manageRequestController.updateRequest(requestBean, requestBean);
                this.previousPage.showAppointments(this.requestBean.getUserName());
            }
            else { // reimposto data e ora precedenti
                requestBean.setDate(date);
                requestBean.setTime(time);
                start();
            }        }
        catch (PastDateException | NotFoundException | DuplicateRequestException e){
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
            start();
        } catch (DateFormatException | TimeFormatException ignored) {
            //ignore
        }
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
}
