package com.forum.web.tests;


import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.server.RemoteControlConfiguration;
import org.openqa.selenium.server.SeleniumServer;

import static org.junit.Assert.assertTrue;

public class HomePageTest {
    private Selenium selenium;
    private SeleniumServer seleniumServer;

    @Before
    public void setUp() throws Exception {
        RemoteControlConfiguration rc = new RemoteControlConfiguration();
        rc.setReuseBrowserSessions(true);
        seleniumServer = new SeleniumServer(rc);
        selenium = new DefaultSelenium("localhost", 4444, "*firefox", "http://10.10.5.126:8080/forum/");
        seleniumServer.start();
        selenium.start();
        selenium.open("/");
        Thread.sleep(2000);
    }

    @After
    public void teatDown() {
        seleniumServer.stop();
    }

    @Test
     public void verifyHitsCorrectUrl(){
        assertTrue(selenium.getLocation().equals("http://10.10.5.126:8080/forum"));
    }

    @Test
    public void verifyLoginOptionShouldRedirectToLoginPage() throws InterruptedException {
        selenium.click("homeLogin");
        Thread.sleep(2000);
        assertTrue(selenium.getLocation().equals("http://10.10.5.126:8080/forum/login"));
    }

//    @Test
//    public void verifyRegistrationOptionIsRedirectToRegistrationPage() throws InterruptedException {
//        selenium.click("homeRegistration");
//        Thread.sleep(2000);
//        assertTrue(selenium.getLocation().equals("http://10.10.5.126:8080/forum/registration"));
//    }

    @Test
    public void shouldHaveSectionToDisplayTopFiveSeekers(){
      assertTrue(selenium.isTextPresent("Top Five Seekers"));
    }

    @Test
    public void shouldHaveSectionToDisplayTopFiveAdvisors(){
        assertTrue(selenium.isTextPresent("Top Five Advisors"));
    }

    @Test
    public void shouldHaveRecentlyAdvisedQuestions(){
        assertTrue(selenium.isTextPresent("Recently Advised Question"));
    }
}
