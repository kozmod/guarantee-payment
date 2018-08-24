import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class BCryptPasswordEncoderTest {

    @Test
    public void encodeTest() throws UnsupportedEncodingException {
        final String passwordString = "123456";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String encodePassword = encoder.encode(passwordString);
        System.out.println(encodePassword);
    }
}
