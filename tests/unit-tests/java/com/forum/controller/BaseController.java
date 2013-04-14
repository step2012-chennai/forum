package com.forum.controller;

import com.forum.authentication.IntegrationTestBase;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "file:./src/main/webapp/WEB-INF/dispatcher-servlet.xml"})

public class BaseController extends IntegrationTestBase {
    MockHttpServletRequest mockHttpServletRequest;
    MockHttpServletResponse mockHttpServletResponse;
    AnnotationMethodHandlerAdapter handlerAdapter;

    public BaseController() {
        mockHttpServletRequest = new MockHttpServletRequest();
        mockHttpServletResponse = new MockHttpServletResponse();
        handlerAdapter = new AnnotationMethodHandlerAdapter();
    }

    public Object createMock(Object controllerObject, String field, Class mockClass) {
        Object mock = Mockito.mock(mockClass);
        ReflectionTestUtils.setField(controllerObject, field, mock);
        return mock;
    }

}