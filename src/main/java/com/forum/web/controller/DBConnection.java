package com.forum.web.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getConnection(){
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/forum", "forum_user", "password");
        } catch (SQLException e) {
            System.out.println("error");
            e.printStackTrace();
        }

        return connection;
    }
}
