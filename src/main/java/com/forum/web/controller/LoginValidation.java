package com.forum.web.controller;

import java.sql.*;

public class LoginValidation {

    private String username;
    private String password;

    public LoginValidation(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean match() {
        Connection connection = DBConnection.getConnection();
        Statement sql;

        try {
            sql = connection.createStatement();
            ResultSet set = sql.executeQuery("select * from Login");
            while (set.next()) {
                if(set.getString("username").equals(username) && set.getString("password").equals(password)){
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
