package com.ispwproject.adoptme.controller.appcontroller;

import com.ispwproject.adoptme.model.*;
import com.ispwproject.adoptme.utils.bean.PetBean;
import com.ispwproject.adoptme.utils.bean.RequestBean;
import com.ispwproject.adoptme.utils.bean.ShelterBean;
import com.ispwproject.adoptme.utils.bean.UserBean;
import com.ispwproject.adoptme.utils.dao.RequestDAO;
import com.ispwproject.adoptme.utils.dao.ShelterDAO;

import java.time.LocalTime;

public class SendRequestController {

    public void createUserRequest(Object sender, PetBean petBean, RequestBean requestBean) throws Exception {
        PetModel petModel;
        ShelterModel shelterModel = ShelterDAO.retrieveShelterById(petBean.getShelterId());

        RequestModel requestModel = new RequestModel();
        requestModel.setStatus(0);
        requestModel.setDate(requestBean.getDate());

        LocalTime time = LocalTime.of(Integer.parseInt(requestBean.getHour()), Integer.parseInt(requestBean.getMinutes()));
        requestModel.setTime(time);

        if (petBean.getType() == 0){ //DOG
            petModel = new DogModel();
        }else { //CAT
            petModel = new CatModel();
        }
        petModel.setPetId(petBean.getPetId());
        petModel.setName(petBean.getName());
        petModel.setShelter(shelterModel);
        requestModel.setPet(petModel);

        if (sender instanceof ShelterBean) {
// todo
        } else if (sender instanceof UserBean) {
            requestModel.setUser(new UserModel((UserBean) sender));
        }

        RequestDAO.saveRequest(requestModel);
    }
}
