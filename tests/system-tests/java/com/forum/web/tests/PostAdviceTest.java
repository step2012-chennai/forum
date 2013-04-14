package com.forum.web.tests;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.server.RemoteControlConfiguration;
import org.openqa.selenium.server.SeleniumServer;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PostAdviceTest {
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
    public void verifyThatFirstLinkInActivityWallIsViewedAndClicked() throws InterruptedException {
        selenium.click("css=p");
        selenium.waitForPageToLoad("6000");
        assertTrue(selenium.isTextPresent("Post your Advice In the Text Box:"));
    }

    @Test
    public void verifyTheErrorMessageIfNoTextIsPresentInTextArea() throws InterruptedException {
        selenium.click("css=p");
        selenium.waitForPageToLoad("6000");
        selenium.click("post");
        selenium.waitForPageToLoad("6000");
        assertTrue(selenium.isTextPresent("Advice length must be of at least 20 characters, and should not contain all spaces"));
    }

    @Test
    public void verifyThatWhenResetButtonIsClickedTheAdviceTextBoxIsEmpty() throws InterruptedException {
        selenium.click("css=p");
        selenium.waitForPageToLoad("6000");
        selenium.runScript("tinymce.get('askAdviceTextarea').setContent('click reset button')");
        Thread.sleep(3000);
        selenium.click("reset");
        Thread.sleep(1000);
        selenium.selectFrame("elm1_ifr");
        Thread.sleep(1000);
        assertFalse(selenium.isTextPresent("click reset button"));
        assertTrue(selenium.isTextPresent(""));
    }


    @Test
    public void verifyTheErrorMessageIfTextIsLessThan20CharactersInTextArea() throws InterruptedException {
        selenium.click("css=p");
        Thread.sleep(1000);
        selenium.runScript("tinymce.get('askAdviceTextarea').setContent('What?')");
        Thread.sleep(1000);
        selenium.click("post");
        Thread.sleep(1000);
        assertTrue(selenium.isTextPresent("Advice length must be of at least 20 characters, and should not contain all spaces "));
    }

    @Test
    public void verifyTheErrorMessageIfTextContainsAllSpacesInTextArea() throws InterruptedException {
        selenium.click("css=p");
        Thread.sleep(1000);
        selenium.runScript("tinymce.get('askAdviceTextarea').setContent('                                                     ')");
        Thread.sleep(1000);
        selenium.click("post");
        Thread.sleep(3000);
        assertTrue(selenium.isTextPresent("Advice length must be of at least 20 characters, and should not contain all spaces"));
    }

    @Test
    public void verifyThatAdviceMoreThan20CharactersInTextAreaIsPostedInViewDetailsOfQuestion() throws InterruptedException {
        selenium.click("css=p");
        Thread.sleep(5000);
        selenium.runScript("tinymce.get('askAdviceTextarea').setContent('Is this Advice POSTED ??')");
        Thread.sleep(5000);
        selenium.click("post");
        Thread.sleep(5000);
        assertTrue(selenium.isTextPresent("Is this Advice POSTED ??"));
    }
}
