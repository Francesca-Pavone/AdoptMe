package com.ispwproject.adoptme.controller.appcontroller;

import com.ispwproject.adoptme.model.RequestModel;
import com.ispwproject.adoptme.model.ShelterModel;
import com.ispwproject.adoptme.model.UserModel;
import com.ispwproject.adoptme.utils.bean.RequestBean;
import com.ispwproject.adoptme.utils.bean.ShelterBean;
import com.ispwproject.adoptme.utils.bean.UserBean;
import com.ispwproject.adoptme.utils.dao.RequestDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RequestsController {

    private ShelterModel shelterModel;
    private UserModel userModel;
    private List<RequestModel> requestList = new ArrayList<>();

    public RequestsController(Object object) {
        if (object instanceof ShelterBean) {
            shelterModel = new ShelterModel((ShelterBean) object);
        }
        else if (object instanceof UserBean) {
            userModel = new UserModel((UserBean) object);
        }
    }

    public List<RequestBean> getRequestList(){

        try {
            if (shelterModel != null) {
                requestList = RequestDAO.retrieveReqByShelter(this.shelterModel);
            }
            else if (userModel != null) {
                requestList = RequestDAO.retrieveReqByUser(this.userModel);
            }

        } catch (SQLException se) {
            // Errore durante l'apertura della connessione
            se.printStackTrace();
        } catch (ClassNotFoundException driverEx) {
            // Errore nel loading del driver
            driverEx.printStackTrace();
        } catch (Exception e) {
            // Errore nel loading del driver o possibilmente nell'accesso al filesystem
            e.printStackTrace();
        }

        List<RequestBean> requestBeanList = new ArrayList<>();

        for (RequestModel requestModel : requestList) {
            RequestBean requestBean = new RequestBean(requestModel);
            requestBeanList.add(requestBean);
        }
        return requestBeanList;
    }
}
