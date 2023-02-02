package com.ispwproject.adoptme.controller.graficcontroller.cli.requests;

import com.ispwproject.adoptme.controller.appcontroller.ManageRequestController;
import com.ispwproject.adoptme.engineering.bean.RequestBean;
import com.ispwproject.adoptme.engineering.exception.CommandNotFoundException;
import com.ispwproject.adoptme.engineering.exception.DateFormatException;
import com.ispwproject.adoptme.engineering.exception.TimeFormatException;
import com.ispwproject.adoptme.view.cli.requests.CLIManageSendRequestView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
        this.view.showRequestNow(this.requestBean.getDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                this.requestBean.getHour() + ":" + this.requestBean.getMinutes());
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

            String[] dateValues = date.split("-");
            if (dateValues.length < 3)
                throw new DateFormatException(date);
            this.requestBean.setDate(LocalDate.of(Integer.parseInt(dateValues[2]), Integer.parseInt(dateValues[1]), Integer.parseInt(dateValues[0])));

            String[] timeValues = time.split(":");
            if (timeValues.length < 2)
                throw new TimeFormatException(time);
            this.requestBean.setHour(timeValues[0]);
            this.requestBean.setMinutes(timeValues[1]);

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
