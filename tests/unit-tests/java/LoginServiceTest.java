import com.forum.services.Login;
import org.junit.After;
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
        template.execute("insert into Login values('step','step@123');");
    }

    @After
    public void tearDown() throws Exception {
        template.execute("delete from Login where username='step';");
    }

    @Test
    public void shouldMatchTheUserNameAndPassWord() {
        assertTrue(service.match("step", "step@123"));
    }

    @Test
    public void shouldNotMatchTheUserNameAndPassWord() {
        assertFalse(service.match("stepsdfd", "step@123"));
    }

    @Test
    public void shouldNotMatchSameUsernameAndDifferentPassword() {
        assertFalse(service.match("step", "step@123fsf"));
    }

}
