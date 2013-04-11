package com.forum.repository;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserRepositoryTest {
    ApplicationContext context;
    UserRepository userRepository;
    JdbcTemplate template;

    @Before
    public void setUp() throws Exception {
        context = new ClassPathXmlApplicationContext("file:./config.xml");
        template = new JdbcTemplate((DataSource) context.getBean("dataSource"));
        userRepository = (UserRepository) context.getBean("userRepository");
    }


    @Test
    public void verifyTheProvidedUserNameIsAlreadyExists(){
        template.execute("insert into userDetails(username) values('sachin')");
        assertTrue(userRepository.isUserNameExists("sachin"));
        template.execute("delete from userDetails where username='sachin'");
    }

    @Test
    public void shouldReturnFalseWhenTheUserNameDoesntExists(){
        assertFalse(userRepository.isUserNameExists("veerappan"));
    }

    @Test
    public void shouldStoreTheUserDetailsInDataBase() throws ParseException {
        userRepository.register("sachin", "Sachin", "password", new SimpleDateFormat("dd-mm-yyyy", Locale.ENGLISH).parse("24-04-1974"),"mumbai","male");
        assertTrue(userRepository.isUserNameExists("sachin"));
        template.execute("delete from userDetails where username='sachin'");
    }
}
