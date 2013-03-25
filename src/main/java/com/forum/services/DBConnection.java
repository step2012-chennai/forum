package com.forum.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created with IntelliJ IDEA.
 * User: ravi
 * Date: 19/3/13
 * Time: 4:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class DBConnection {
    private String dataBaseName;
    private int portNumber;
    private String userName;
    private String password;
    private Connection connection = null;

    public DBConnection(String dataBaseName, int portNumber, String userName, String password) {
        this.dataBaseName = dataBaseName;
        this.portNumber=portNumber;
        this.userName = userName;
        this.password = password;
    }

    public void connect() throws SQLException {
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:"+portNumber+"/"+dataBaseName,userName,password);
    }

    public Statement createStatement() throws SQLException {
        return connection.createStatement();
    }

    public Connection getConnection() throws SQLException {
        return connection.getMetaData().getConnection();
    }

    public void disConnect() throws SQLException {
        if(connection == null) throw new SQLException("DataBase connection missing!");
        connection.close();
    }
}

