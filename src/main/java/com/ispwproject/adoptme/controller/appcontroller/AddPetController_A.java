package com.ispwproject.adoptme.controller.appcontroller;

import com.ispwproject.adoptme.model.PetModel;
import com.ispwproject.adoptme.utils.bean.CatBean;
import com.ispwproject.adoptme.utils.bean.DogBean;
import com.ispwproject.adoptme.utils.bean.PetBean;
import com.ispwproject.adoptme.utils.dao.PetDAO;

public class AddPetController_A {

    public void addDog(DogBean dogBean, int shelterId) throws Exception {
        PetDAO petDAO = new PetDAO();

        petDAO.saveDog(dogBean, shelterId);
    }    public void addCat(CatBean catBean, int shelterId) throws Exception {
        PetDAO petDAO = new PetDAO();

        petDAO.saveCat(catBean, shelterId);
    }
}
