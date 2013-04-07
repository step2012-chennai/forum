package com.forum.web.page.tests;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.server.RemoteControlConfiguration;
import org.openqa.selenium.server.SeleniumServer;

import static org.junit.Assert.assertTrue;

public class LoginTestCase {
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
        selenium.open("login");
        Thread.sleep(1000);
    }

    @After
    public void teatDown(){
        seleniumServer.stop();
    }

    @Test
    public void verifyTheTargetHitsCorrectUrl() throws InterruptedException {
        assertTrue(selenium.getLocation().equals("http://10.10.5.126:8080/forum/login"));
        assertTrue(selenium.isTextPresent("Username:"));
    }

    @Test
    public void verifyThatAfterEnteringWrongUserNameErrorMessageShouldBeDisplayed() throws InterruptedException {
        selenium.type("j_username", "abcd");
        selenium.click("submit");
        Thread.sleep(1000);
        assertTrue(selenium.getLocation().equals("http://10.10.5.126:8080/forum/loginfail"));
        assertTrue(selenium.isTextPresent("Username/Password is incorrect"));
    }

    @Test
    public void verifyThatAfterEnteringWrongPasswordErrorMessageShouldBeDisplayed() throws InterruptedException {
        selenium.type("j_password", "abcd");
        selenium.click("submit");
        Thread.sleep(1000);
        assertTrue(selenium.getLocation().equals("http://10.10.5.126:8080/forum/loginfail"));
        assertTrue(selenium.isTextPresent("Username/Password is incorrect"));
    }

    @Test
    public void verifyThatAfterEnteringWrongUserNameAndPasswordErrorMessageShouldBeDisplayed() throws InterruptedException {
        selenium.type("j_username", "abcd");
        selenium.type("j_password", "abcd");
        selenium.click("submit");
        Thread.sleep(1000);
        assertTrue(selenium.getLocation().equals("http://10.10.5.126:8080/forum/loginfail"));
        assertTrue(selenium.isTextPresent("Username/Password is incorrect"));
    }

    @Test
    public void verifyThatAfterEnteringCorrectUserNameAndWrongPasswordErrorMessageShouldBeDisplayed() throws InterruptedException {
        selenium.type("j_username", "user");
        selenium.type("j_password", "abcd");
        selenium.click("submit");
        Thread.sleep(1000);
        assertTrue(selenium.isTextPresent("Username/Password is incorrect"));
    }

    @Test
    public void verifyThatAfterEnteringWrongUserNameAndCorrectPasswordErrorMessageShouldBeDisplayed() throws InterruptedException {
        selenium.type("j_username", "abcd");
        selenium.type("j_password", "password");
        selenium.click("submit");
        Thread.sleep(1000);
        assertTrue(selenium.isTextPresent("Username/Password is incorrect"));
    }

    @Test
    public void verifyThatAfterEnteringCorrectUserNameAndPasswordPageIsRedirectedToActivityWall() throws InterruptedException {
        selenium.type("j_username", "user");
        selenium.type("j_password", "password");
        selenium.click("submit");
        Thread.sleep(1000);
        assertTrue(selenium.getLocation().equals("http://10.10.5.126:8080/forum/activityWall"));
        assertTrue(selenium.isTextPresent("Recent Questions"));
    }
}
