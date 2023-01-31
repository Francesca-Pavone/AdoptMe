package com.ispwproject.adoptme.controller.graficcontroller.cli.requests;

import com.ispwproject.adoptme.controller.appcontroller.SendRequestController;
import com.ispwproject.adoptme.controller.graficcontroller.cli.CLIPetInformationController;
import com.ispwproject.adoptme.engineering.bean.PetBean;
import com.ispwproject.adoptme.engineering.bean.RequestBean;
import com.ispwproject.adoptme.engineering.bean.UserBean;
import com.ispwproject.adoptme.engineering.exception.*;
import com.ispwproject.adoptme.engineering.observer.Observer;
import com.ispwproject.adoptme.engineering.session.Session;
import com.ispwproject.adoptme.view.cli.requests.CLISendRequestView;

import java.time.LocalDate;

public class CLISendRequestController implements Observer {

    private static final String SEND = "";
    private static final String BACK = "1";

    private CLISendRequestView cliSendRequestView;
    private RequestBean requestBean;
    private PetBean petBean;

    public void sendRequest(PetBean petBean) {
        this.petBean = petBean;
        UserBean user = Session.getCurrentSession().getUserBean();
        this.requestBean = new RequestBean(petBean.getName(), petBean.getPetId(), petBean.getShelterId(), user.getName(), user.getUserId());

        this.cliSendRequestView = new CLISendRequestView();
        this.cliSendRequestView.showSendRequestForm(requestBean.getUserName(), requestBean.getPetName(), this);
    }

    public void setRequestDate(String date) throws DateFormatException {
        String[] dateValues = date.split("-");
        LocalDate localDate;

        if (dateValues.length < 3)
            throw new DateFormatException(date);
        localDate = LocalDate.of(Integer.parseInt(dateValues[2]), Integer.parseInt(dateValues[1]), Integer.parseInt(dateValues[0]));

        this.requestBean.setDate(localDate);
    }

    public void setRequestTime(String time) throws TimeFormatException {
        String[] timeValues = time.split(":");

        if (timeValues.length < 2)
            throw new TimeFormatException(time);

        this.requestBean.setHour(timeValues[0]);
        this.requestBean.setMinutes(timeValues[1]);
    }

    public void executeCommand(String command) {
        switch (command) {
            case SEND -> {
                SendRequestController sendRequestController = new SendRequestController();
                try {
                    sendRequestController.sendUserRequest(this.petBean, this.requestBean, this);
                }
                catch (PastDateException e) {
                    this.cliSendRequestView.showUnsuccessful();
                    sendRequest(petBean);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
            case BACK -> {
                CLIPetInformationController cliPetInformationController = new CLIPetInformationController(petBean);
                try {
                    cliPetInformationController.setPetInfo();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void update(Object object) {
        cliSendRequestView.showSuccessful();
        CLIPetInformationController cliPetInformationController = new CLIPetInformationController(this.petBean);
        try{
            cliPetInformationController.setPetInfo();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update2(Object object1, Object object2) {

    }
}










