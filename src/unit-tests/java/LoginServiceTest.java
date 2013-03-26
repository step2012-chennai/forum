import com.forum.services.Login;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LoginServiceTest {
    Login service;
    ApplicationContext context;
    JdbcTemplate template;

    @Before
    public void setup() {
        service = new Login();
        context = new ClassPathXmlApplicationContext("file:./config.xml");
        service = (Login) context.getBean("login");
        template = new JdbcTemplate((DataSource) context.getBean("dataSource"));

    }

    @Test
    public void shouldMatchTheUserNameAndPassWord() {
       template.execute("insert into Login values('user','password');");
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
