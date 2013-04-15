package com.forum.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Repository
public class PostQuestion {
    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;

    @Autowired
    public PostQuestion(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void insert(String tag, String question, String userName) {
        QuestionValidation validation = new QuestionValidation();
        question = validation.insertApostrophe(question);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String dateformat = dateFormat.format(date);
        String[] sr = tag.split(",");

        jdbcTemplate.execute("insert into Questions(question,post_date,user_name) values('" + question + "','" + dateformat + "','" + userName + "')");
        for (String t : sr) {
            System.out.println("]"+t+"[");
            System.out.println("|"+t+"|"+"check it fast "+!"".equals(t));
            if(getTagId(t) == null ){
                if(!"".equals(t))  {
                    System.out.println("me andar aa gaya");
                    SqlRowSet r = jdbcTemplate.queryForRowSet("select MAX(q_id) as latestQuestionID from Questions;");
                    r.next();
                    jdbcTemplate.execute("insert into tags(tag_name) values('" + t + "')");
                    jdbcTemplate.execute("insert into questions_tags(q_id,t_id) values('" +r.getString("latestQuestionID") + "',' " +  getTagId(t) + " ');");
                }
                else return;
            }
            else{
                SqlRowSet r = jdbcTemplate.queryForRowSet("select MAX(q_id) from Questions;");
                r.next();
                jdbcTemplate.execute("insert into questions_tags(q_id,t_id) values('" + r.getString(1) + "',' " + getTagId(t) + " ');");
            }
        }
    }

    public String getTagId(String tagName) {
        SqlRowSet a = jdbcTemplate.queryForRowSet("select * from tags where tag_name ='"+tagName +"'");
        return a.next() ? a.getString("t_id") : null;
    }
}