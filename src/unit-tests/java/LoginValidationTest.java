import com.forum.web.controller.LoginValidation;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LoginValidationTest {

    @Test
    public void shouldMatchTheUserNameAndPassWord(){
        LoginValidation validate = new LoginValidation("user", "password");
        assertTrue(validate.match());
    }

    @Test
    public void shouldNotMatchTheUserNameAndPassWord(){
        LoginValidation validate = new LoginValidation("user_name", "Password");
        assertFalse(validate.match());
    }

    @Test
    public void shouldNotMatchSameUsernameAndDifferentPassword(){
        LoginValidation validate = new LoginValidation("Prasath", "Password");
        assertFalse(validate.match());
    }

}
