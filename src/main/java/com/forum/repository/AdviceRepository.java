package com.forum.repository;

import com.forum.domain.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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

    public Advice save(Advice advice) {
        QuestionValidation validation = new QuestionValidation();
        String answer = validation.insertApostrophe(advice.getAdvice());
        jdbcTemplate.update("insert into answers(q_id,answer,post_date,user_name) values(?,?,?,?)",
                Integer.valueOf(advice.getId()), answer, new Timestamp(new Date().getTime()), advice.getUserName());

        return new Advice(advice.getId(), answer, new Timestamp(new Date().getTime()), advice.getUserName());
    }

    public List<Advice> getAdvices(int questionId) {
        SqlRowSet advice = jdbcTemplate.queryForRowSet("select q_id,answer,post_date,user_name from answers where q_id='" + questionId + "'ORDER BY ans_id DESC");
        List<Advice> advices = new ArrayList<Advice>();
        while (advice.next()) {
            advices.add(new Advice(advice.getString(1), advice.getString(2), advice.getTimestamp(3), advice.getString(4)));
        }
        return advices;
    }

    public List<Integer> getQuestionIdAnsweredBy(String userName) {
        SqlRowSet results = jdbcTemplate.queryForRowSet("select q_id from answers where user_name = '" + userName + "' order by post_date DESC");
        List<Integer> questionIds = new ArrayList<Integer>();
        while (results.next()) {
            questionIds.add(results.getInt(1));
        }
        return questionIds;
    }
}
