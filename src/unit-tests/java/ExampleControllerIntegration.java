import com.forum.services.ExampleService;
import com.forum.web.controller.ActivityWallController;
import org.hamcrest.core.IsEqual;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class ExampleControllerIntegration {
    @Test
    public void shouldRedirectToActivityWallPageWhenPasswordIsCorrect(){
        ExampleService exampleService = new ExampleService();
        ActivityWallController activityWallController = new ActivityWallController(exampleService);
        assertThat(((RedirectView) activityWallController.getInput("user","password").getView()).getUrl(), IsEqual.equalTo("activityWall"));
    }

    @Test
    public void shouldRedirectToLoginPageWhenPasswordIsIncorrect(){
        ExampleService exampleService = new ExampleService();
        ActivityWallController activityWallController = new ActivityWallController(exampleService);
        assertThat(((RedirectView) activityWallController.getInput("userfgd","password").getView()).getUrl(), IsEqual.equalTo("login"));
    }

    @Test
    public void shouldAddErrorMessageToTheModel(){
        ExampleService exampleService = new ExampleService();
        ActivityWallController activityWallController = new ActivityWallController(exampleService);
        ModelAndView modelAndView = activityWallController.getInput("fdsuykh", "rsdfdg");
        assertTrue(modelAndView.getModelMap().containsKey("error"));
        assertTrue(modelAndView.getModelMap().get("error").equals("Password/Username is incorrect"));
    }
}