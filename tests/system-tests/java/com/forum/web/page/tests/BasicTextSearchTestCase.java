package com.forum.web.page.tests;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.server.RemoteControlConfiguration;
import org.openqa.selenium.server.SeleniumServer;

import static org.junit.Assert.assertTrue;

public class BasicTextSearchTestCase {
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
        selenium.open("activityWall");
        selenium.type("j_username", "user");
        selenium.type("j_password", "password");
        selenium.click("submit");
        Thread.sleep(1000);
        assertTrue(selenium.getLocation().equals("http://10.10.5.126:8080/forum/activityWall"));
        Thread.sleep(1000);
    }

    @After
    public void teatDown(){
        seleniumServer.stop();
    }

    @Test
    public void verifyTheTargetHitsCorrectUrl() throws InterruptedException {
        assertTrue(selenium.isTextPresent("Recent Questions"));
    }

    @Test
    public void verifyThatAbleToSearchRevelantTextInTextBox() throws InterruptedException {
        selenium.type("basicSearch","What is");
        Thread.sleep(1000);
        selenium.click("getQuestionsPerPage");
        Thread.sleep(1000);
        assertTrue(selenium.getLocation().equals("http://10.10.5.126:8080/forum/getQuestionsPerPage?basicSearch=what+is"));
        Thread.sleep(1000);
        assertTrue(selenium.isTextPresent("Recent Questions"));
    }

    @Test
    public void verifyThatAbleToSearchIrrevelantTextInTextBox() throws InterruptedException {
        selenium.type("basicSearch","rome");
        Thread.sleep(1000);
        selenium.click("getQuestionsPerPage");
        Thread.sleep(1000);
        assertTrue(selenium.getLocation().equals("http://10.10.5.126:8080/forum/getQuestionsPerPage?basicSearch=rome"));
        Thread.sleep(1000);
        assertTrue(selenium.isTextPresent("Recent Questions"));
    }

}
