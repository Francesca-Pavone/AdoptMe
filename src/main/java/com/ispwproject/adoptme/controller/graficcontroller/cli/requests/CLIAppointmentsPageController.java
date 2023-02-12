package com.ispwproject.adoptme.controller.graficcontroller.cli.requests;

import com.ispwproject.adoptme.controller.appcontroller.ShowRequestsController;
import com.ispwproject.adoptme.controller.graficcontroller.cli.CLIGraficController;
import com.ispwproject.adoptme.controller.graficcontroller.cli.CLIShelterHomepageController;
import com.ispwproject.adoptme.controller.graficcontroller.cli.CLIUserHomepageController;
import com.ispwproject.adoptme.engineering.bean.RequestBean;
import com.ispwproject.adoptme.engineering.exception.DateFormatException;
import com.ispwproject.adoptme.engineering.exception.NotFoundException;
import com.ispwproject.adoptme.engineering.exception.TimeFormatException;
import com.ispwproject.adoptme.engineering.observer.Observer;
import com.ispwproject.adoptme.engineering.session.Session;
import com.ispwproject.adoptme.engineering.utils.ShowExceptionSupport;
import com.ispwproject.adoptme.view.cli.requests.CLIAppointmentsPageView;

import java.util.ArrayList;
import java.util.List;

public class CLIAppointmentsPageController implements CLIGraficController, Observer {
    public static final String PENDING = "PENDING";
    public static final String SENDED = "SENDED";
    private final List<RequestBean> requestList = new ArrayList<>();
    private final CLIAppointmentsPageView view;

    public CLIAppointmentsPageController() {
        this.view = new CLIAppointmentsPageView(this);
    }

    @Override
    public void start(){
        String ownerName = null;
        Session session = Session.getCurrentSession();
        if (session.getUserBean() != null)
            ownerName = session.getUserBean().getName();
        else if (session.getShelterBean() != null )
            ownerName = session.getShelterBean().getShelterBeanName();

        ShowRequestsController showRequestsController = new ShowRequestsController();
        try {
            showRequestsController.getRequestList(this);
        } catch (NotFoundException | DateFormatException | TimeFormatException e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
        }
        showAppointments(ownerName);
    }

    public void showAppointments(String owner){
        this.view.showTitle(owner);
        for (RequestBean requestBean : this.requestList) {
            String status = null;
            switch (requestBean.getStatus()) {
                case 0 -> {
                    if (Session.getCurrentSession().getShelterBean() != null)
                        status = PENDING;
                    else if (Session.getCurrentSession().getUserBean() != null)
                        status = SENDED;
                    this.view.showRequestInfo(requestBean.getId(), status, requestBean.getUserName(), requestBean.getPetName(), requestBean.getDate(), requestBean.getTime());
                }

                case 1 ->  {
                    if (Session.getCurrentSession().getShelterBean() != null)
                        status = SENDED;
                    else if (Session.getCurrentSession().getUserBean() != null)
                        status = PENDING;
                    this.view.showRequestInfo(requestBean.getId(), status, requestBean.getUserName(), requestBean.getPetName(), requestBean.getDate(), requestBean.getTime());

                }
                case 2 -> this.view.showRequestInfo(requestBean.getId(), "ACCEPTED", requestBean.getUserName(), requestBean.getPetName(), requestBean.getDate(), requestBean.getTime());

                default -> {
                    if (Session.getCurrentSession().getUserBean() != null)
                        this.view.showRequestInfo(requestBean.getId(), "REJECTED", requestBean.getUserName(), requestBean.getPetName(), requestBean.getDate(), requestBean.getTime());
                }
            }
        }
        this.view.showCommands();

    }
    public void executeCommand(String input) throws NotFoundException {
        if (input.equals("0"))
            goToHomepage();
        else
            goToRequest(input);
    }

    private void goToRequest(String input) throws NotFoundException {
        // verifico se all'interno della lista di RequestBean trovo un RequestBean con id uguale al valore inserito dall'utente
        RequestBean requestBean = null;
        for (RequestBean bean : requestList) {
            if (Integer.parseInt(input) == bean.getId())
                requestBean = bean;
        }
        if (requestBean == null)
            throw new NotFoundException("The request with id '" + input + "' does not exist");

        CLIManageRequestController cliManageRequestController = new CLIManageRequestController(requestBean);
        cliManageRequestController.setPreviousPage(this);
        cliManageRequestController.start();

        if (Session.getCurrentSession().getUserBean() != null)
            this.showAppointments(Session.getCurrentSession().getUserBean().getName());
        else
            this.showAppointments(Session.getCurrentSession().getShelterBean().getShelterBeanName());
    }

    private static void goToHomepage() {
        if (Session.getCurrentSession().getUserBean() != null) {
            CLIUserHomepageController cliUserHomepageController = new CLIUserHomepageController();
            cliUserHomepageController.start();
        }
        if (Session.getCurrentSession().getShelterBean() != null) {
            CLIShelterHomepageController cliShelterHomepageController= new CLIShelterHomepageController();
            cliShelterHomepageController.start();
        }
    }

    @Override
    public void update(Object object) {
        /*
            a questo metodo viene passato un requestBean al momento in cui viene prelevato dal db
            poiché viene aggiornato lo stato della concreate subject RequestModel (aggiungendo la nuova richiesta)
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
