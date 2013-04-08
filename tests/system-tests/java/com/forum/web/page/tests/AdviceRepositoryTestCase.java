package com.forum.web.page.tests;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.server.RemoteControlConfiguration;
import org.openqa.selenium.server.SeleniumServer;

import static org.junit.Assert.assertTrue;

public class AdviceRepositoryTestCase {
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
        Thread.sleep(3000);
    }

    @After
    public void teatDown(){
        seleniumServer.stop();
    }

    @Test
    public void verifyTheTargetHitsCorrectUrl() throws InterruptedException {
        assertTrue(selenium.getLocation().equals("http://10.10.5.126:8080/forum/activityWall"));
        Thread.sleep(1000);
        assertTrue(selenium.isTextPresent("Recent Questions"));
    }

    @Test
    public void verifyThatFirstLinkInActivityWallIsViewedAndClickOnPostAdvice() throws InterruptedException {
        selenium.click("css=p");
        Thread.sleep(1000);
        selenium.click("post-button");
        Thread.sleep(1000);
        assertTrue(selenium.isTextPresent("Post your Advice :"));
    }

    @Test
    public void verifyTheErrorMessageIfNoTextIsPresentInTextArea() throws InterruptedException {
        selenium.click("css=p");
        Thread.sleep(1000);
        selenium.click("post-button");
        Thread.sleep(1000);
        selenium.click("post");
        Thread.sleep(1000);
        assertTrue(selenium.isTextPresent("Advice length must be of at least 20 characters, and should not contain all spaces "));
    }
    @Test
    public void verifyTheErrorMessageIfTextIsLessThan20CharactersInTextArea() throws InterruptedException {
        selenium.click("css=p");
        Thread.sleep(1000);
        selenium.click("post-button");
        Thread.sleep(1000);
        selenium.runScript("tinymce.get('elm1').setContent('What?')");
        Thread.sleep(1000);
        selenium.click("post");
        Thread.sleep(1000);
        assertTrue(selenium.isTextPresent("Advice length must be of at least 20 characters, and should not contain all spaces "));
    }

    @Test
    public void verifyTheErrorMessageIfTextContainsAllSpacesInTextArea() throws InterruptedException {
        selenium.click("css=p");
        Thread.sleep(1000);
        selenium.click("post-button");
        Thread.sleep(1000);
        selenium.runScript("tinymce.get('elm1').setContent('                                                     ')");
        Thread.sleep(1000);
        selenium.click("post");
        Thread.sleep(1000);
        assertTrue(selenium.isTextPresent("Advice length must be of at least 20 characters, and should not contain all spaces "));
    }

    @Test
    public void verifyThatAdviceMoreThan20CharactersInTextAreaIsPostedInActivityWall() throws InterruptedException {
        selenium.click("css=p");
        Thread.sleep(1000);
        selenium.click("post-button");
        Thread.sleep(3000);
        selenium.runScript("tinymce.get('elm1').setContent('Is this Advice POSTED ??')");
        Thread.sleep(1000);
        selenium.click("post");
        Thread.sleep(3000);
        assertTrue(selenium.isTextPresent("Is this Advice POSTED ??"));
    }
}
