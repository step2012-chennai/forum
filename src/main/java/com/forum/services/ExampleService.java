package com.forum.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class ExampleService {

    public ExampleService() {
    }

    public void doOperation(){
    }

    public boolean match(String username, String password) {
        Connection connection = com.forum.web.controller.DBConnection.getConnection();
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
