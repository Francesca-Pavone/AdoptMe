package junit;

import com.ispwproject.adoptme.engineering.bean.LoginBean;
import com.ispwproject.adoptme.engineering.exception.EmailFormatException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestLoginBean {
    /**
     * FRANCESCA PAVONE SALAFIA
     * Il seguente test va a testare la verifica sulla correttezza sintattica dell'email inserita per effettuare il login
     */
    @Test
    void testSetEmail(){
        int validFormat;
        try {
            new LoginBean("francesca", "123");
            validFormat = 1;
        } catch (EmailFormatException e) {
            validFormat = 0;
        }

        //il test ha successo perché ho inserito come input un'email non valida e quindi mi aspetto
        //che venga sollevata l'eccezione e di conseguenza assegnato il valore 0 a "validFormat"
        assertEquals(0, validFormat);
    }
}
