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
    public static final String PENDING = "PENDING";
    public static final String SENDED = "SENDED";
    private final List<RequestBean> requestList = new ArrayList<>();
    private CLIAppointmentsPageView cliAppointmentsPageView;


    public void start(){
        String ownerName = null;
        Session session = Session.getCurrentSession();
        if (session.getUserBean() != null)
            ownerName = session.getUserBean().getName();
        else if (session.getShelterBean() != null )
            ownerName = session.getShelterBean().getName();
        this.cliAppointmentsPageView = new CLIAppointmentsPageView(this);

        ShowRequestsController showRequestsController = new ShowRequestsController();
        showRequestsController.getRequestList(this);
        showAppointments(ownerName);

        this.cliAppointmentsPageView.showCommands();

    }

    public void showAppointments(String owner){
        this.cliAppointmentsPageView.showTitle(owner);
        for (RequestBean requestBean : this.requestList) {
            String status = null;
            switch (requestBean.getStatus()) {
                case 0 -> {
                    if (Session.getCurrentSession().getShelterBean() != null)
                        status = PENDING;
                    else if (Session.getCurrentSession().getUserBean() != null)
                        status = SENDED;
                }

                case 1 ->  {
                    if (Session.getCurrentSession().getShelterBean() != null)
                        status = SENDED;
                    else if (Session.getCurrentSession().getUserBean() != null)
                        status = PENDING;
                }
                case 2 -> status = "ACCEPTED";
                default -> status = "REJECTED";
            }
            this.cliAppointmentsPageView.showRequestInfo(requestBean.getId(), status, requestBean.getUserName(), requestBean.getPetName(), requestBean.getDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")), requestBean.getHour() + ":" + requestBean.getMinutes());

        }
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
        // verifico se all'interno della lista di RequestBean trovo un RequestBean con id uguale al valore inserito dall'utente
        RequestBean requestBean = null;
        for (RequestBean bean : requestList) {
            if (Integer.parseInt(input) == bean.getId())
                requestBean = bean;
        }
        if (requestBean == null)
            throw new NotExistingRequestException(input);


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
            cliManageRejectedRequestController.setPreviousPage(this);
            cliManageRejectedRequestController.start();

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

    @Override
    public void update(Object object) {
        /*
            a questo metodo viene passato un requestBean al momento in cui viene prelevato dal db
            poiché viene aggiornato lo stato della concreate subject RequestList (aggiungendo la nuova richiesta)
            e tale classe è osservata da questo controller grafico
        */
        this.requestList.add((RequestBean) object);
    }

    @Override
    public void update2(Object object1, Object object2) {
        /*
            questa chiamata viene causata dall'aggiornamento di stato della concreate subject RequestModel, poiché questa classe la osserva
            il primo parametro formale che mi viene passato è il requestBean aggiornato dopo la modifica, il secondo è il requestBean non aggiornato
            in questo modo posso capire da che stato proveniva la richiesta attuale
         */
        for (RequestBean requestBean : this.requestList) {
            if (((RequestBean) object1).getId() == requestBean.getId()){
                this.requestList.remove(requestBean);
                break;
            }
        }

        /*
            se l'utente corrente è uno:
                - Shelter: devono essere mostrate tutte le richieste tranne quelle che ha rifiutato
                - User: devono essere mostrate tutte le richieste, anche quelle che lo Shelter ha rifiutato
         */
        if (((RequestBean) object1).getStatus() != 3){
            this.requestList.add((RequestBean) object1);
        }
    }
}
