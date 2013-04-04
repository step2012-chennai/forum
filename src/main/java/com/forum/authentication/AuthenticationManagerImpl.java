package com.forum.authentication;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;


public class AuthenticationManagerImpl implements AuthenticationProvider {

    public Authentication authenticate(Authentication authentication) {
        if(authentication.getCredentials().equals("password") && authentication.getPrincipal().equals("user")){
            ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), authorities);
            return authenticationToken;
        }
        throw new BadCredentialsException("Username / Password is incorrect");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;  //To change body of implemented methods use File | Settings | File Templates.
    }
}


