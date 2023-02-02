package com.ispwproject.adoptme.controller.graficcontroller.cli.requests;

import com.ispwproject.adoptme.controller.appcontroller.ShowRequestsController;
import com.ispwproject.adoptme.engineering.bean.RequestBean;
import com.ispwproject.adoptme.engineering.exception.NotExistingRequestException;
import com.ispwproject.adoptme.engineering.observer.Observer;
import com.ispwproject.adoptme.engineering.session.Session;
import com.ispwproject.adoptme.view.cli.requests.CLIAppointmentsPageView;
import com.ispwproject.adoptme.view.cli.CLIUserHomepageView;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CLIAppointmentsPageController implements Observer {

    private List<RequestBean> requestList = new ArrayList<>();
    private CLIAppointmentsPageView cliAppointmentsPageView;


    public void start(){
        String ownerName = null;
        Session session = Session.getCurrentSession();
        if (session.getUserBean() != null)
            ownerName = session.getUserBean().getName();
        else if (session.getShelterBean() != null )
            ownerName = session.getShelterBean().getName();
        this.cliAppointmentsPageView = new CLIAppointmentsPageView(this);
        this.cliAppointmentsPageView.showTitle(ownerName);

        ShowRequestsController showRequestsController = new ShowRequestsController();
        showRequestsController.getRequestList(this);

        this.cliAppointmentsPageView.showCommands();

    }

    public void executeCommand(String input) throws NotExistingRequestException {
        Session session = Session.getCurrentSession();
        if (input.equals("0"))
            goToHomepage(session);
        else
            goToRequest(input, session);
    }

    private void goToRequest(String input, Session session) throws NotExistingRequestException {
        //verifico se all'interno della lista di RequestBean trovo un RequestBean con id uguale al valore inserito dall'utente
        RequestBean requestBean = null;
        for (int i=0; i<requestList.size(); i++) {
            if (Integer.parseInt(input) == requestList.get(i).getId())
                requestBean = requestList.get(i);
        }
        if (requestBean == null)
            throw new NotExistingRequestException(input);

        /*
        while (true) {
            try {
                if (Integer.parseInt(input) == requestList.get(i).getId()) {
                    requestBean = requestList.get(i);
                    break;
                } else
                    i++;
            }
            catch (ArrayIndexOutOfBoundsException e) {
                throw new NotExistingRequestException(input);
            }
        }
         */

        boolean sent = (session.getUserBean() != null && requestBean.getStatus() == 0) || (session.getShelterBean() != null && requestBean.getStatus() == 1);
        boolean pending = (session.getShelterBean() != null && requestBean.getStatus() == 0) || (session.getUserBean() != null && requestBean.getStatus() == 1);
        boolean rejected = requestBean.getStatus() == 3;

        if (sent){
            CLIManageSendRequestController cliManageSendRequestController = new CLIManageSendRequestController(requestBean);
            cliManageSendRequestController.setPreviousPage(this);
            cliManageSendRequestController.start();
        }
        else if (pending) {
            CLIManagePendingRequestController cliManagePendingRequestController = new CLIManagePendingRequestController(requestBean);
        }
        else if (rejected) {
            CLIManageRejectedRequestController cliManageRejectedRequestController = new CLIManageRejectedRequestController(requestBean);
        }
        else {
            this.cliAppointmentsPageView.showConfirmedApp(
                    requestBean.getId(),
                    requestBean.getDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                    requestBean.getHour() + ":" + requestBean.getMinutes());
            start();
        }

    }

    private static void goToHomepage(Session session) {
        if (session.getUserBean() != null) {
            CLIUserHomepageView cliUserHomepageView = new CLIUserHomepageView();
            cliUserHomepageView.run();
        }
        //todo: fare parte shelter
    }

    public List<RequestBean> getRequestList() {
        return requestList;
    }

    public void setRequestList(List<RequestBean> requestList) {
        this.requestList = requestList;
    }

    @Override
    public void update(Object object) {
        this.requestList.add((RequestBean) object);
        CLIRequestItemController cliRequestItemController = new CLIRequestItemController();
        cliRequestItemController.setObserver(this);
        cliRequestItemController.setRequestData((RequestBean) object);
    }

    @Override
    public void update2(Object object1, Object object2) {
/*
        int listIndex = -1;
        for (int i = 0; i<((List<?>) object2).size() - 1; i++) {
            System.out.println(((RequestBean) (((List<?>) object2).get(i))).getId());
            if (((RequestBean) (((List<?>) object2).get(i))).getId() == ((RequestBean)object1).getId())
                listIndex = i;

        }
        if (((RequestBean) object1).getStatus() == 3)
            ((List<?>) object2).remove(listIndex);

        else if ((((RequestBean) object1).getStatus() == 0 && Session.getCurrentSession().getUserBean() != null) || (((RequestBean) object1).getStatus() == 1 && Session.getCurrentSession().getShelterBean() != null)) {
            ((RequestBean) (((List<?>) object2).get(listIndex))).setDate(((RequestBean) object1).getDate());
            ((RequestBean) (((List<?>) object2).get(listIndex))).setHour(((RequestBean) object1).getHour());
            ((RequestBean) (((List<?>) object2).get(listIndex))).setMinutes(((RequestBean) object1).getMinutes());
        }

 */
    }
}
