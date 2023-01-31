package com.ispwproject.adoptme.controller.graficcontroller.cli.requests;

import com.ispwproject.adoptme.engineering.bean.RequestBean;
import com.ispwproject.adoptme.engineering.observer.Observer;
import com.ispwproject.adoptme.engineering.session.Session;
import com.ispwproject.adoptme.view.cli.requests.CLIRequestItemView;

import java.time.format.DateTimeFormatter;

public class CLIRequestItemController implements Observer {
    private RequestBean request;
    private Observer observer;
    private CLIRequestItemView cliRequestItemView;

    public void setObserver(Observer observer) {
        this.observer = observer;
    }

    public void setRequestData(RequestBean requestBean) {
        this.request = requestBean;
        
        String status = null;
        switch (requestBean.getStatus()) {
            case 0 -> {
                if (Session.getCurrentSession().getShelterBean() != null)
                    status = "PENDING";
                else if (Session.getCurrentSession().getUserBean() != null)
                    status = "SENDED";
            }
            
            case 1 ->  {
                if (Session.getCurrentSession().getShelterBean() != null)
                    status = "SENDED";
                else if (Session.getCurrentSession().getUserBean() != null)
                    status = "PENDING";
            }
            case 2 -> status = "ACCEPTED";
            case 3 -> status = "REJECTED";
        }
        this.cliRequestItemView = new CLIRequestItemView();
        this.cliRequestItemView.showRequestInfo(
                requestBean.getId(),
                status,
                requestBean.getUserName(),
                requestBean.getPetName(),
                requestBean.getDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                requestBean.getHour() + ":" + requestBean.getMinutes()
        );
    }

    @Override
    public void update(Object object) {
        //ignore

    }

    @Override
    public void update2(Object object1, Object object2) {
        if (((RequestBean)object1).getStatus() == 3) {
            this.cliRequestItemView.showStatus("REJECTED");
        }
        else if (((RequestBean)object1).getStatus() == 2) {
            this.cliRequestItemView.showStatus("ACCEPTED");
       }
        else if ((((RequestBean)object1).getStatus() == 1 && Session.getCurrentSession().getShelterBean() != null) || ((RequestBean)object1).getStatus() == 0 && Session.getCurrentSession().getUserBean() != null) {
            this.cliRequestItemView.showStatus("SENDED");
        }
        else
            this.cliRequestItemView.showStatus("PENDING");

    }
}
