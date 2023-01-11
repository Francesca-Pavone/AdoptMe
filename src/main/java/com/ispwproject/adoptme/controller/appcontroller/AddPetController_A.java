package com.ispwproject.adoptme.controller.appcontroller;

import com.ispwproject.adoptme.model.PetModel;
import com.ispwproject.adoptme.utils.bean.PetBean;
import com.ispwproject.adoptme.utils.dao.PetDAO;

public class AddPetController_A {

    public static void savePet(PetModel petModel) throws Exception {
        PetDAO petDAO = new PetDAO();

        petDAO.savePet(petModel);
    }
}
