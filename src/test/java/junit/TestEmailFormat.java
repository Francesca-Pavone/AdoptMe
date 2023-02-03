package junit;

import com.ispwproject.adoptme.engineering.bean.LoginBean;
import com.ispwproject.adoptme.engineering.exception.EmailFormatException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestEmailFormat {
    /**
     * FRANCESCA PAVONE SALAFIA
     * Il seguente test va a testare la verifica sulla correttezza sintattica dell'email inserita per effettuare il login
     */
    @Test
    public void testEmailFormat(){
        int validFormat;
        try {
            new LoginBean("francesca", "123");
            validFormat = 1;
        } catch (EmailFormatException e) {
            validFormat = 0;
        }

        assertEquals(0, validFormat);
    }
}
