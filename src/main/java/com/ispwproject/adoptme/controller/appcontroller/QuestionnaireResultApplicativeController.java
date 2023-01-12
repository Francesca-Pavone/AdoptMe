package com.ispwproject.adoptme.controller.appcontroller;

import com.ispwproject.adoptme.utils.bean.QuestionnaireResultBean;
import com.ispwproject.adoptme.utils.dao.PetDAO;

import java.util.List;

public class QuestionnaireResultApplicativeController {
    public static class Tuple {
        private final int minAge;
        private final int maxAge;
        public Tuple(int minAge, int maxAge) {
            this.minAge = minAge;
            this.maxAge = maxAge;
        }
    }

    public void findPets(QuestionnaireResultBean questionnaireResultBean) throws Exception {
        //adapter
        //dal dao ricevi lista di PetModel che traduci in PetBean che restituisci con questo metodo al controller grafico del questionnaire result page
    }
}
