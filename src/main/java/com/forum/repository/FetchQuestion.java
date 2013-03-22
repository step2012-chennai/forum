package com.forum.repository;

import com.forum.services.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class FetchQuestion {
    DBConnection connection = new DBConnection("forum", 5432, "forum_user", "password");
    public Collection fetch() throws SQLException {
        ResultSet set = null;
        Collection collection=new ArrayList();
        Statement statement;
        try {
            connection.connect();
            statement = connection.createStatement();
            set = statement.executeQuery("select * from Questions ORDER BY q_id DESC limit=10");
            for(int i=0;set.next() && i<10;i++) {
                collection.add(set.getString("question"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.disConnect();
        }
        return collection;
    }
}
