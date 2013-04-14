package com.forum.web.tests;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.server.RemoteControlConfiguration;
import org.openqa.selenium.server.SeleniumServer;

import static org.junit.Assert.assertTrue;

public class TagsTest {
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
    public void teatDown() {
        seleniumServer.stop();
    }

    @Test
    public void shouldAddTagToQuestion() throws InterruptedException {
        Thread.sleep(2000);
        selenium.runScript("tinymce.get('askAdviceTextarea').setContent('what is java how to learn java easily')");
        Thread.sleep(1000);
        selenium.type("tag", "java");
        selenium.click("post");
        Thread.sleep(1000);
        assertTrue(selenium.isTextPresent("java"));
    }

    @Test
    public void shouldAddedMultipleTagsWithCommaSeparater() throws InterruptedException {
        Thread.sleep(2000);
        selenium.runScript("tinymce.get('askAdviceTextarea').setContent('what is java how to learn java easily')");
        Thread.sleep(1000);
        selenium.type("tag", "java,c,vb");
        selenium.click("post");
        Thread.sleep(1000);
        assertTrue(selenium.isTextPresent("java,c,vb"));
    }

    @Test
    public void shouldGiveMessageWhenMultipleTagsWithOutCommaSeparater() throws InterruptedException {
        Thread.sleep(2000);
        selenium.runScript("tinymce.get('askAdviceTextarea').setContent('what is java how to learn java easily')");
        Thread.sleep(1000);
        selenium.type("tag", "java c vb");
        selenium.click("post");
        Thread.sleep(1000);
        assertTrue(selenium.isTextPresent("tag should be separated by comma and should not contains spaces"));
    }

    @Test
    public void shouldDisplayMessageWhenOnlyCommaSeparatorIsGiven() throws InterruptedException {
        Thread.sleep(2000);
        selenium.runScript("tinymce.get('askAdviceTextarea').setContent('This Question Contains Only Comma's as Tags" +
                "'s')");
        Thread.sleep(1000);
        selenium.type("tag", ",,,");
        selenium.click("post");
        Thread.sleep(1000);
        assertTrue(selenium.isTextPresent("tag should be separated by comma and should not contains spaces"));
    }

}