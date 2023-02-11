package junit;

import com.ispwproject.adoptme.controller.appcontroller.QuestionnaireResultController;
import com.ispwproject.adoptme.engineering.bean.QuestionnaireResultBean;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
class TestQuestionnaireResultController {
    /**
     * FEDERICA CANTELMI
     * Il seguente test va a verificare che la generazione della stringa da utilizzare per la query tramite pattern
     * Decorator restituisca effettivamente la stringa corretta.
     */
    @Test
    void testGenerateQuery() {
        String query1 = "SELECT catId as id, name, imgSrc, dayOfBirth, monthOfBirth, yearOfBirth, gender, 1 AS type, shelter FROM Cats JOIN Compatibility ON catId = petId WHERE testFiv = 0 AND testFelv = 0 AND gender = '1' AND femaleCat = 1 AND maleDog = 1 AND sleepOutside = '1' AND firstExperience = 1 AND (year(now()) - yearOfBirth <= 1) AND (hoursAlone = 1 OR hoursAlone = 2);";

        QuestionnaireResultBean questionnaireResultBean = new QuestionnaireResultBean();
        questionnaireResultBean.setType(1);
        questionnaireResultBean.setGender(1);
        questionnaireResultBean.setAge("puppy");
        questionnaireResultBean.setSterilizePet(true);
        questionnaireResultBean.setSleepOutside(1);
        questionnaireResultBean.setMaleDog(true);
        questionnaireResultBean.setFemaleCat(true);
        questionnaireResultBean.setFirstPet(1);
        questionnaireResultBean.setHoursAlone(1);
        questionnaireResultBean.setDisabledPet(true);

        QuestionnaireResultController questionnaireResultController = new QuestionnaireResultController();
        String query2 = questionnaireResultController.generateQuery(questionnaireResultBean);

        assertEquals(query1, query2);
    }
}
