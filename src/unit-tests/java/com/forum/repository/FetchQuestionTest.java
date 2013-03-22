package com.forum.repository;

import org.hamcrest.core.IsEqual;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertThat;

public class FetchQuestionTest {

    @Test
    public void shouldFetchTopTenQuestionsFromDatabase() throws SQLException {
        FetchQuestion question=new FetchQuestion();
        assertThat(question.fetch().size(),IsEqual.equalTo(10));
    }
}










//<tbody>
//<td>
//<a href="http://localhost:8080/app/test">
//
//<%@page import="java.sql.*,com.forum.repository.FetchQuestion"%>
//<%
//        FetchQuestion question=new FetchQuestion();
//Connection connection = null;
//Statement sql;
//try {
//        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/forum", "forum_user", "password");
//} catch (SQLException e) {
//        e.printStackTrace();
//}
//        try {
//        sql = connection.createStatement();
//ResultSet set = sql.executeQuery("select * from Questions");
//while (set.next()) {
//        out.println(set.getString("question"));
//out.println("<br><br>");
//}
//
//        } catch (SQLException e) {
//        e.printStackTrace();
//}
//        %></a>
//</tbody>
