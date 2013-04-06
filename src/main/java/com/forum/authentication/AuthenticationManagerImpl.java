package com.forum.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
@Repository
public class AuthenticationManagerImpl implements AuthenticationProvider {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private Encryption encryption = new Encryption();

    public Authentication authenticate(Authentication authentication) {
        if (isAuthenticatedUser(authentication)) {
            ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            return new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), authorities);
        }
        throw new BadCredentialsException("Username/Password is incorrect");
    }

    private boolean isAuthenticatedUser(Authentication authentication) {
        String principal = authentication.getPrincipal().toString();
        String credentials = authentication.getCredentials().toString();
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet("select * from login where username ='" + principal + "' and password= '" + encryption.encryptUsingMd5(credentials) + "' ");
        return sqlRowSet.next();
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}

