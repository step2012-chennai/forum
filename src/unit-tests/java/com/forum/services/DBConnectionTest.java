package com.forum.services;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: ravi
 * Date: 19/3/13
 * Time: 4:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class DBConnectionTest {
    DBConnection dbConnection;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void shouldThrowExceptionWhenDataBaseIsNotPresent() throws SQLException {
        exception.expect(SQLException.class);
        dbConnection=new DBConnection("DataBaseName",5432,"forum_user","password");
        dbConnection.connect();
    }

    @Test
    public void shouldThrowExceptionWhenPortNumberIsInvalid() throws SQLException {
        exception.expect(SQLException.class);
        dbConnection=new DBConnection("forum",5332,"forum_user","password");
        dbConnection.connect();
    }

    @Test
    public void shouldThrowExceptionWhenUserNameIsInvalid() throws SQLException {
        exception.expect(SQLException.class);
        dbConnection=new DBConnection("forum",5432,"user_name","password");
        dbConnection.connect();
    }

    @Test
    public void shouldThrowExceptionWhenPasswordIsInvalid() throws SQLException {
        exception.expect(SQLException.class);
        dbConnection=new DBConnection("forum",5432,"forum_user","abcdefgh");
        dbConnection.connect();
    }

    @Test
    public void shouldConnectToDataBaseWhenAllFieldsAreValid() throws SQLException {
        dbConnection=new DBConnection("forum",5432,"forum_user","password");
        dbConnection.connect();
    }

    @Test
    public void shouldDisConnectWhenDatabaseAlreadyConnected() throws SQLException {
        exception.expect(SQLException.class);
        dbConnection=new DBConnection("forum",5432,"forum_user","password");
        dbConnection.connect();
        dbConnection.disConnect();
        dbConnection.getConnection();
    }

    @Test
    public void shouldThrowExceptionWhenConnectionIsNotEstablishedAndTryToConnect() throws SQLException {
        exception.expect(SQLException.class);
        exception.expectMessage("DataBase connection missing!");
        dbConnection=new DBConnection("forum",5432,"forum_user","password");
        dbConnection.disConnect();
    }
}
