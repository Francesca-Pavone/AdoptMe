package junit;

import com.ispwproject.adoptme.engineering.bean.LoginBean;
import com.ispwproject.adoptme.engineering.exception.EmailFormatException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestEmailFormat {

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
