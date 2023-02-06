package com.ispwproject.adoptme.controller.graficcontroller.cli.requests;

import com.ispwproject.adoptme.controller.appcontroller.ManageRequestController;
import com.ispwproject.adoptme.engineering.bean.RequestBean;
import com.ispwproject.adoptme.engineering.exception.CommandNotFoundException;
import com.ispwproject.adoptme.engineering.exception.DateFormatException;
import com.ispwproject.adoptme.engineering.exception.TimeFormatException;
import com.ispwproject.adoptme.view.cli.requests.CLIManageSendRequestView;


public class CLIManageSendRequestController {

    private static final String ANNUL = "1";
    private static final String MODIFY = "2";
    private static final String BACK = "3";
    private CLIManageSendRequestView view;
    private final RequestBean requestBean;
    private CLIAppointmentsPageController previousPage;
    public CLIManageSendRequestController(RequestBean requestBean) {
        this.requestBean = requestBean;
    }

    public void setPreviousPage(CLIAppointmentsPageController previousPage) {
        this.previousPage = previousPage;
    }

    public void start(){
        this.view = new CLIManageSendRequestView(this);
        this.view.showRequestNow(this.requestBean.getDate(),
                this.requestBean.getTime());
        this.view.showForm();
    }

    public void executeCommand(String command) throws DateFormatException, TimeFormatException, Exception {
        switch (command) {
            case ANNUL -> annulRequest();

            case MODIFY -> modifyRequest();

            case BACK -> this.previousPage.showAppointments(this.requestBean.getUserName());

            default -> throw new CommandNotFoundException();
        }


    }

    private void modifyRequest() throws Exception {
        String date = this.view.askDate();
        String time = this.view.askTime();
        if (view.askConfirmation() == 1){

            this.requestBean.setDate(date);
            this.requestBean.setTime(time);

            ManageRequestController manageRequestController = new ManageRequestController();
            try {
                manageRequestController.modifyRequest(requestBean, requestBean, null, this.previousPage);
            }catch (Exception e){
                e.printStackTrace();
            }
            this.previousPage.showAppointments(this.requestBean.getUserName());
        }
        // todo verifica caso == 2 e solleva eccezione altrimenti
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
