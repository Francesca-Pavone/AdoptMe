package com.ispwproject.adoptme.controller.graficcontroller.cli.requests;

import com.ispwproject.adoptme.controller.appcontroller.ManageRequestController;
import com.ispwproject.adoptme.engineering.bean.RequestBean;
import com.ispwproject.adoptme.engineering.exception.DateFormatException;
import com.ispwproject.adoptme.engineering.exception.TimeFormatException;
import com.ispwproject.adoptme.view.cli.requests.CLIManageSendRequestView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CLIManageSendRequestController {

    private static final String ANNUL = "1";
    private static final String MODIFY = "2";
    private CLIManageSendRequestView cliManageSendRequestView;
    private final RequestBean requestBean;
    private CLIAppointmentsPageController previousPage;
    public CLIManageSendRequestController(RequestBean requestBean) {
        this.requestBean = requestBean;
    }

    public void setPreviousPage(CLIAppointmentsPageController previousPage) {
        this.previousPage = previousPage;
    }

    public void start(){
        this.cliManageSendRequestView = new CLIManageSendRequestView(this);
        this.cliManageSendRequestView.showForm(this.requestBean.getDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                this.requestBean.getHour() + ":" + this.requestBean.getMinutes());
    }

    public void executeCommand(String command) throws DateFormatException, TimeFormatException {
        switch(command) {
            case ANNUL -> {
                if (this.cliManageSendRequestView.askConfirmation().equals("1")) {
                    ManageRequestController manageRequestController = new ManageRequestController();
                    try {
                        manageRequestController.deleteRequest(this.requestBean, this.previousPage.getRequestList(), this.previousPage);
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
                this.previousPage.start();

            }
            case MODIFY -> {
                String date = this.cliManageSendRequestView.askDate();
                String time = this.cliManageSendRequestView.askTime();
                if (cliManageSendRequestView.askConfirmation().equals("1")){

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
                        manageRequestController.modifyRequest(requestBean, this.previousPage.getRequestList(), this.previousPage, this.previousPage);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    this.previousPage.start();
                }
            }
            default -> this.previousPage.start();

        }
    }
}
