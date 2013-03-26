import com.forum.services.LoginService;
import com.forum.web.controller.ExampleController;
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
    LoginService login;

    @Before
    public void setUp() throws Exception {
        context = new ClassPathXmlApplicationContext("file:./config.xml");
        template = new JdbcTemplate((DataSource) context.getBean("dataSource"));
        template.execute("insert into Login values('user','password');");
        login = (LoginService) context.getBean("login");
    }

    @After
    public void tearDown() throws Exception {
        template.execute("delete from Login;");
    }

    @Test
    public void shouldRedirectToActivityWallPageWhenPasswordIsCorrect(){
        LoginService loginService = new LoginService();
        ExampleController exampleController = new ExampleController(loginService);
        assertThat(((RedirectView)exampleController.getInput("user","password").getView()).getUrl(), IsEqual.equalTo("activityWall"));
    }

    @Test
    public void shouldRedirectToLoginPageWhenPasswordIsIncorrect(){
        LoginService loginService = new LoginService();
        ExampleController exampleController = new ExampleController(loginService);
        assertThat(((RedirectView)exampleController.getInput("userfgd","password").getView()).getUrl(), IsEqual.equalTo("login"));
    }

    @Test
    public void shouldAddErrorMessageToTheModel(){
        LoginService loginService = new LoginService();
        ExampleController exampleController = new ExampleController(loginService);
        ModelAndView modelAndView = exampleController.getInput("fdsuykh", "rsdfdg");
        assertTrue(modelAndView.getModelMap().containsKey("error"));
        assertTrue(modelAndView.getModelMap().get("error").equals("Password/Username is incorrect"));
    }
}