package com.forum.repository;

import com.forum.authentication.IntegrationTestBase;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserRepositoryTest extends IntegrationTestBase {
    @Autowired
    UserRepository userRepository;
    JdbcTemplate template;
    @Autowired
    private  DataSource dataSource;

    @Before
    public void setUp() throws Exception {
        template = new JdbcTemplate(dataSource);
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

//    @Test
//    public void shouldStoreTheUserDetailsInDataBase() throws ParseException {
//        userRepository.register("sachin", "Sachin", "password","24-04-1974","mumbai","male");
//        assertTrue(userRepository.isUserNameExists("sachin"));
//        template.execute("delete from userDetails where username='sachin'");
//    }
}