import com.forum.services.ExampleService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ExampleServiceTest {
    ExampleService service;

    @Before
    public void setup(){
        service = new ExampleService();
    }

    @Test
    public void shouldMatchTheUserNameAndPassWord(){
        ExampleService service = new ExampleService();
        assertTrue(service.match("user", "password"));
    }

    @Test
    public void shouldNotMatchTheUserNameAndPassWord(){
        ExampleService service = new ExampleService();
        assertFalse(service.match("usersdfd", "password"));
    }

    @Test
    public void shouldNotMatchSameUsernameAndDifferentPassword(){
        ExampleService service = new ExampleService();
        assertFalse(service.match("user", "passwordfsf"));
    }
}
