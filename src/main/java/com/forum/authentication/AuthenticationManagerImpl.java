package com.forum.authentication;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.sql.DataSource;
import java.util.ArrayList;

public class AuthenticationManagerImpl implements AuthenticationProvider {
    ApplicationContext context;
    private JdbcTemplate jdbcTemplate;

    private Encryption encryption=new Encryption();

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Authentication authenticate(Authentication authentication) {

        if (isAuthenticatedUser(authentication)) {
            ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), authorities);
            return authenticationToken;
        }
        throw new BadCredentialsException("Username/Password is incorrect");
    }

    private boolean isAuthenticatedUser(Authentication authentication) {
        String principal = authentication.getPrincipal().toString();
        String credentials = authentication.getCredentials().toString();
        context = new ClassPathXmlApplicationContext("file:./config.xml");
        jdbcTemplate = new JdbcTemplate((DataSource) context.getBean("dataSource"));
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet("select * from login where username ='" + principal + "' and password= '" +encryption.encryptUsingMd5(credentials)+ "' ");

        return sqlRowSet.next();
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;  //To change body of implemented methods use File | Settings | File Templates.
    }
}

