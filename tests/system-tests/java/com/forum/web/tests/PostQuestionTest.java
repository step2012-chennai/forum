package com.forum.web.tests;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.server.RemoteControlConfiguration;
import org.openqa.selenium.server.SeleniumServer;

import static org.junit.Assert.assertTrue;

public class PostQuestionTest {
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
        selenium.open("postQuestion");
        selenium.type("j_username", "user");
        selenium.type("j_password", "password");
        selenium.click("submit");
        Thread.sleep(3000);
    }

    @After
    public void teatDown(){
        seleniumServer.stop();
    }

    @Test
    public void verifyTheTargetHitsCorrectUrl() throws InterruptedException {
        assertTrue(selenium.getLocation().equals("http://10.10.5.126:8080/forum/postQuestion"));
        Thread.sleep(2000);
        assertTrue(selenium.isTextPresent("Post your question :"));
    }

    @Test
    public void verifyTheErrorMessageIfNoTextIsPresentInTextArea() throws InterruptedException {
        selenium.click("post");
        Thread.sleep(1000);
        assertTrue(selenium.isTextPresent("Question length must be of at least 20 characters, and should not contain all spaces"));
    }

    @Test
    public void verifyTheErrorMessageIfTextIsLessThan20CharactersInTextArea() throws InterruptedException {
        selenium.runScript("tinymce.get('askAdviceTextarea').setContent('What?')");
        Thread.sleep(1000);
        selenium.click("post");
        Thread.sleep(1000);
        assertTrue(selenium.isTextPresent("Question length must be of at least 20 characters, and should not contain all spaces"));
    }

    @Test
    public void verifyTheErrorMessageIfTextContainsAllSpacesInTextArea() throws InterruptedException {
        selenium.runScript("tinymce.get('askAdviceTextarea').setContent('                                                     ')");
        Thread.sleep(1000);
        selenium.click("post");
        Thread.sleep(1000);
        assertTrue(selenium.isTextPresent("Question length must be of at least 20 characters, and should not contain all spaces"));
    }

    @Test
    public void verifyThatQuestionMoreThan20CharactersInTextAreaIsPostedInActivityWall() throws InterruptedException {
        selenium.runScript("tinymce.get('askAdviceTextarea').setContent('Is this Question POSTED ??')");
        Thread.sleep(1000);
        selenium.click("post");
        Thread.sleep(1000);
        assertTrue(selenium.getLocation().equals("http://10.10.5.126:8080/forum/activityWall?pageNumber=1"));
        Thread.sleep(1000);
        assertTrue(selenium.isTextPresent("Is this Question POSTED ??"));
    }
}
