import com.forum.services.Login;
import com.forum.web.controller.ActivityWallController;
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

public class ExampleControllerIntegration {
    JdbcTemplate template;
    ApplicationContext context;
    Login login;

    @Before
    public void setUp() throws Exception {
        context = new ClassPathXmlApplicationContext("file:./config.xml");
        template = new JdbcTemplate((DataSource) context.getBean("dataSource"));
        template.execute("insert into Login values('user','password');");
        login = (Login) context.getBean("login");
    }

    @After
    public void tearDown() throws Exception {
        template.execute("delete from Login;");
    }

    @Test
    public void shouldRedirectToActivityWallPageWhenPasswordIsCorrect(){
        ActivityWallController activityWallController = new ActivityWallController(login);
        assertThat(((RedirectView) activityWallController.getInput("user","password").getView()).getUrl(), IsEqual.equalTo("activityWall"));
    }

    @Test
    public void shouldRedirectToLoginPageWhenPasswordIsIncorrect(){
        ActivityWallController activityWallController = new ActivityWallController(login);
        assertThat(((RedirectView) activityWallController.getInput("userfgd","password").getView()).getUrl(), IsEqual.equalTo("login"));
    }

    @Test
    public void shouldAddErrorMessageToTheModel(){
        ActivityWallController activityWallController = new ActivityWallController(login);
        ModelAndView modelAndView = activityWallController.getInput("fdsuykh", "rsdfdg");
        assertTrue(modelAndView.getModelMap().containsKey("error"));
        assertTrue(modelAndView.getModelMap().get("error").equals("Password/Username is incorrect"));
    }
}