import com.forum.services.LoginService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LoginServiceTest {
    LoginService service;
    ApplicationContext context;
    JdbcTemplate template;

    @Before
    public void setup() {
        service = new LoginService();
        context = new ClassPathXmlApplicationContext("file:./config.xml");
        service = (LoginService) context.getBean("login");
        template = new JdbcTemplate((DataSource) context.getBean("dataSource"));
        template.execute("insert into Login values('user','password');");
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
