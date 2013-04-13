package com.forum.web.tests;


import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.server.RemoteControlConfiguration;
import org.openqa.selenium.server.SeleniumServer;

import static org.junit.Assert.assertTrue;

public class ViewPostedAdviceTest {
    private Selenium selenium;
    private SeleniumServer seleniumServer;
    private String recentQuestionId = "css=p";

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
    public void teatDown() {
        seleniumServer.stop();
    }

    @Test
    public void shouldHaveMyAnswerOptionOnTheActivityWall() {
        assertTrue(selenium.isTextPresent("My Answer"));
    }

    @Test
    public void shouldDisplayUsersPostedAdvices() {
        selenium.click("answer");
        selenium.waitForPageToLoad("3000");
        assertTrue(selenium.isTextPresent("My Answers"));
    }

    @Test
    public void shouldDisplayNumberOfPostedAdviceIfNoAdviceIsPostedForAQuestion() throws InterruptedException {
        postQuestion();
        selenium.click(recentQuestionId);
        Thread.sleep(2000);
        assertTrue(selenium.isTextPresent("No of Advice: 0"));
    }

    @Test
    public void shouldDisplayNumberOfPostedAdvicesIfAdviceIsPostedForAQuestion() throws InterruptedException {
        postQuestion();
        postAdviceForRecentPostedQuestion();
        selenium.click(recentQuestionId);
        Thread.sleep(2000);
        assertTrue(selenium.isTextPresent("No of Advice: 1"));
    }

    @Test
    public void shouldDisplayQuestionsForWhichUserHasPostedAdvice() throws InterruptedException {
        postQuestion();
        postAdviceForRecentPostedQuestion();
        selenium.click("answer");
        Thread.sleep(2000);
        assertTrue(selenium.isTextPresent("this is first question for testing"));
    }

    @Test
    public void shouldDisplayAdvicesOfAQuestion() throws InterruptedException {
        postQuestion();
        postAdviceForRecentPostedQuestion();
        selenium.click("answer");
        Thread.sleep(2000);
        selenium.click(recentQuestionId);
        Thread.sleep(2000);
        assertTrue(selenium.isTextPresent("advice for first question"));
    }

    private void postAdviceForRecentPostedQuestion() throws InterruptedException {
        selenium.click(recentQuestionId);
        Thread.sleep(2000);
        selenium.runScript("tinymce.get('elm1').setContent('advice for first question')");
        Thread.sleep(2000);
        selenium.click("post");
        Thread.sleep(2000);
    }

    private void postQuestion() throws InterruptedException {
        selenium.click("question");
        selenium.waitForPageToLoad("4000");
        selenium.runScript("tinymce.get('elm1').setContent('this is first question for testing')");
        Thread.sleep(2000);
        selenium.click("post");
        Thread.sleep(4000);
    }
}