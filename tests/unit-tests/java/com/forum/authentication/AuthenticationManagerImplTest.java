package com.forum.authentication;

import org.junit.Before;
import org.junit.Test;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class AuthenticationManagerImplTest {

    private AuthenticationProvider authenticationManager;
    private Authentication authentication;

    @Before
    public void setUp() {
        authenticationManager = new AuthenticationManagerImpl();
    }

    @Test
    public void shouldReturnAuthenticatedObjectWhenUsernameAndPasswordIsCorrect() {
        ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        authentication = new UsernamePasswordAuthenticationToken("user", "password", authorities);
        Authentication actualAuthentication = authenticationManager.authenticate(authentication);
        assertThat(actualAuthentication, equalTo(authentication));
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
        authentication = new UsernamePasswordAuthenticationToken("user", "password", authorities);
        Authentication actualAuthentication = authenticationManager.authenticate(authentication);
        assertThat(actualAuthentication, equalTo(authentication));
    }
}