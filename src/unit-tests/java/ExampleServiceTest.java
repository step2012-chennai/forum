import com.forum.services.ExampleService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ExampleServiceTest {
    ExampleService service;
    ApplicationContext context;

    @Before
    public void setup() {
        service = new ExampleService();
        context = new ClassPathXmlApplicationContext("file:src/main/webapp/WEB-INF/config.xml");
        service = (ExampleService) context.getBean("login");
    }

    @Test
    public void shouldMatchTheUserNameAndPassWord() {
        assertTrue(service.match("user", "password"));
    }

    @Test
    public void shouldNotMatchTheUserNameAndPassWord() {
        assertFalse(service.match("usersdfd", "password"));
    }

    @Test
    public void shouldNotMatchSameUsernameAndDifferentPassword() {
        assertFalse(service.match("user", "passwordfsf"));
    }
}
