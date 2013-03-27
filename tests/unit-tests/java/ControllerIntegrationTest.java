import com.forum.services.Login;
import com.forum.controller.LoginController;
import org.hamcrest.core.IsEqual;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.sql.DataSource;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class ControllerIntegrationTest {
    JdbcTemplate template;
    ApplicationContext context;
    Login login;

    @Before
    public void setUp() throws Exception {
        context = new ClassPathXmlApplicationContext("file:./config.xml");
        template = new JdbcTemplate((DataSource) context.getBean("dataSource"));
        template.execute("insert into Login values('step','step@123');");
        login = (Login) context.getBean("login");
    }

    @After
    public void tearDown() throws Exception {
        template.execute("delete from Login where username='step';");
    }

    @Test
    public void shouldRedirectToActivityWallPageWhenPasswordIsCorrect() {
        LoginController loginController = new LoginController();
        assertThat(((RedirectView) loginController.getInput("step", "step@123").getView()).getUrl(), IsEqual.equalTo("activityWall"));
    }

    @Test
    public void shouldRedirectToLoginPageWhenPasswordIsIncorrect() {
        LoginController loginController = new LoginController();
        assertThat(((RedirectView) loginController.getInput("stepfgd", "step@123").getView()).getUrl(), IsEqual.equalTo("login"));
    }

    @Test
    public void shouldAddErrorMessageToTheModel() {
        LoginController loginController = new LoginController();
        ModelAndView modelAndView = loginController.getInput("fdsuykh", "rsdfdg");
        assertTrue(modelAndView.getModelMap().containsKey("error"));
        assertTrue(modelAndView.getModelMap().get("error").equals("Password/Username is incorrect"));
    }
}