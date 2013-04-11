package com.forum.authentication;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.sql.DataSource;
import java.util.ArrayList;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class AuthenticationManagerImplTest {

    private AuthenticationProvider authenticationManager;
    private Authentication authentication;
    JdbcTemplate template;

    @Before
    public void setUp() {
        ApplicationContext context = new ClassPathXmlApplicationContext("file:./config.xml");
        authenticationManager = (AuthenticationManagerImpl) context.getBean("verify");
        template = new JdbcTemplate((DataSource) context.getBean("dataSource"));
        template.execute("insert into userDetails(username,password) values('temp','5f4dcc3b5aa765d61d8327deb882cf99');");
    }

    @After
    public void tearDown() throws Exception {
        template.execute("delete from userDetails where username='temp' OR username='SOMEUSER' ; ");
    }

    @Test(expected = BadCredentialsException.class)
    public void shouldThrowExceptionWhenUsernameAndPasswordIsWrong() {
        authentication = new UsernamePasswordAuthenticationToken("wrong", "wrong");
        authenticationManager.authenticate(authentication);
    }

    @Test
    public void shouldReturnAuthenticatedObjectWhenComparingWithDatabaseUsernamePassword() {
        ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        authentication = new UsernamePasswordAuthenticationToken("temp", "password", authorities);
        Authentication actualAuthentication = authenticationManager.authenticate(authentication);
        assertThat(actualAuthentication, equalTo(authentication));
    }

    @Test
    public void shouldCheckIfAuthenticationIsSuccessfulIfUserNameIsInDifferentCase() {
        template.execute("insert into userDetails(username,password) values('SOMEUSER','5f4dcc3b5aa765d61d8327deb882cf99');");
        ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        authentication = new UsernamePasswordAuthenticationToken("sOmeUser", "password", authorities);
        Authentication actualAuthentication = authenticationManager.authenticate(authentication);
        assertThat(actualAuthentication, equalTo(authentication));

    }

}