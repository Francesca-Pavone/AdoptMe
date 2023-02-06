package com.ispwproject.adoptme.controller.graficcontroller.cli.requests;

import com.ispwproject.adoptme.controller.appcontroller.SendRequestController;
import com.ispwproject.adoptme.controller.graficcontroller.cli.CLIPetInformationController;
import com.ispwproject.adoptme.engineering.bean.PetBean;
import com.ispwproject.adoptme.engineering.bean.RequestBean;
import com.ispwproject.adoptme.engineering.bean.UserBean;
import com.ispwproject.adoptme.engineering.exception.*;
import com.ispwproject.adoptme.engineering.observer.Observer;
import com.ispwproject.adoptme.engineering.session.Session;
import com.ispwproject.adoptme.engineering.utils.ShowExceptionSupport;
import com.ispwproject.adoptme.view.cli.requests.CLISendRequestView;


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
        this.requestBean.setDate(date);
    }

    public void setRequestTime(String time) throws TimeFormatException {
        this.requestBean.setTime(time);
    }

    public void executeCommand(String command) {
        if (command.equals(SEND)){
            SendRequestController sendRequestController = new SendRequestController();
            try {
                sendRequestController.sendUserRequest(this.petBean, this.requestBean, this);
            }
            catch (PastDateException e) {
                ShowExceptionSupport.showExceptionCLI(e.getMessage());
                sendRequest(petBean);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        else if (command.equals(BACK)){
            CLIPetInformationController cliPetInformationController = new CLIPetInformationController(petBean);
            try {
                cliPetInformationController.setPetInfo();
            } catch (Exception e) {
                e.printStackTrace();
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
        //ignore
    }
}










