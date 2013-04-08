package com.forum.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AdviceRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;


    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void insert(int question_id,String answer) {
        QuestionValidation validation = new QuestionValidation();
        answer = validation.insertApostrophe(answer);
        jdbcTemplate.execute("insert into answers(q_id,answer,post_date) values(" + question_id + ",'" + answer + "',CURRENT_TIMESTAMP(0))");
    }


    public List<Advice> getAdvices(int questionId) {
        SqlRowSet advice = jdbcTemplate.queryForRowSet("select q_id,answer,post_date,user_name from answers where q_id='" + questionId +"'ORDER BY ans_id DESC");
        List<Advice> advices = new ArrayList<Advice>();
        while (advice.next()) {
            advices.add(new Advice(advice.getString(1),advice.getString(2),advice.getString(3),advice.getString(4)));
        }
        return advices;
    }

    public List<String> getQuestionIdAnsweredBy(String userName) {
        SqlRowSet results=jdbcTemplate.queryForRowSet("select q_id from answers where user_name = '" + userName + "' order by post_date DESC");
        List<String> questionIds=new ArrayList<String>();
        while (results.next()){
            questionIds.add(results.getString(1));
        }
        return questionIds;
    }
}
