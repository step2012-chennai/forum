package com.forum.authentication;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private String failureRedirectUrl = "/loginfail";
    private RedirectStrategy redirectStrategy ;
    static String FORM_USERNAME_KEY = UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        redirectStrategy = new DefaultRedirectStrategy();
        saveException(request, exception);
        String username = request.getParameter(FORM_USERNAME_KEY);
        request.getSession().setAttribute("username", username);
        redirectStrategy.sendRedirect(request, response, failureRedirectUrl);
    }


}
