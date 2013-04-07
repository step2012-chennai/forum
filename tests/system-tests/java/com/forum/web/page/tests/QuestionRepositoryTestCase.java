package com.forum.web.page.tests;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.server.RemoteControlConfiguration;
import org.openqa.selenium.server.SeleniumServer;

import static org.junit.Assert.assertTrue;

public class QuestionRepositoryTestCase {
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
    }

    @After
    public void teatDown() {
        seleniumServer.stop();
    }

    @Test
    public void verifyTheTargetHitsCorrectUrl() throws InterruptedException {
        Thread.sleep(1000);
        assertTrue(selenium.isTextPresent("Recent Questions"));
    }

    @Test
    public void verifyTheURLOfTheFirstQuestionInActivityWall() throws InterruptedException {
        Thread.sleep(1000);
        selenium.click("css=p");
        Thread.sleep(1000);
        assertTrue(selenium.getLocation().equals("http://10.10.5.126:8080/forum/question_details?questionId=1"));
    }

    @Test
    public void verifyTheClickedQuestionFromTheActivityWallInQuestionDetailPage() throws InterruptedException {
        Thread.sleep(1000);
        String firstQuestion = selenium.getText("css=p");
        selenium.click("css=p");
        Thread.sleep(1000);
        assertTrue(selenium.isTextPresent(firstQuestion));
    }

    @Test
    public void verifyThatNoOfAdvicesIsPresentInQuestionDetailPage() throws InterruptedException {
        Thread.sleep(1000);
        selenium.click("css=p");
        Thread.sleep(1000);
        assertTrue(selenium.isTextPresent("No of Advice:"));
    }
}
