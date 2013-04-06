package com.forum.web.page.tests;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.server.RemoteControlConfiguration;
import org.openqa.selenium.server.SeleniumServer;

import static org.junit.Assert.assertTrue;

public class PostQuestionTestCase {
    private Selenium selenium;
    private SeleniumServer seleniumServer;

    @Before
    public void setUp() throws Exception {
        RemoteControlConfiguration rc = new RemoteControlConfiguration();
        rc.setReuseBrowserSessions(true);
        seleniumServer = new SeleniumServer(rc);
        selenium = new DefaultSelenium("localhost", 4444, "*firefox", "http://localhost:8080/forum/");
        seleniumServer.start();
        selenium.start();
        selenium.open("postQuestion");
        selenium.type("j_username", "user");
        selenium.type("j_password", "password");
        selenium.click("submit");
    }
    @After
    public void teatDown(){
        seleniumServer.stop();
    }

    @Test
    public void verifyTheTargetHitsCorrectUrl() throws InterruptedException {
        Thread.sleep(1000);
        assertTrue(selenium.isTextPresent("Post your question "));
    }

    @Test
    public void verifyTheErrorMessageIfNoTextIsPresentInTextArea() throws InterruptedException {
        Thread.sleep(1000);
        selenium.click("post");
        Thread.sleep(1000);
        assertTrue(selenium.isTextPresent("Question length must be of at least 20 characters, and should not contain all spaces"));
    }

    @Test
    public void verifyTheErrorMessageIfTextIsLessThan20CharactersInTextArea() throws InterruptedException {
        Thread.sleep(1000);
        selenium.runScript("document.getElementById('elm1').style.display='block'");
        selenium.type("elm1", "what");
        Thread.sleep(1000);
        selenium.click("post");
        Thread.sleep(1000);
        selenium.runScript("document.getElementById('elm1').style.display='block'");
        Thread.sleep(1000);
        assertTrue(selenium.isTextPresent("Question length must be of at least 20 characters, and should not contain all spaces"));
    }
}
