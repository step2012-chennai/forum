package com.forum.repository;

import com.forum.domain.Leader;
import com.forum.domain.Question;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ShowLeaders {
    private JdbcTemplate jdbcTemplate;
    private DataSource  dataSource;
    private static final int CHARACTER_LIMIT = 100;
    private static final int BEGIN_INDEX = 0;
    private static final String TRAILING_CHARACTERS = "...?</p>";

    @Autowired
    public ShowLeaders(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.dataSource = dataSource;
    }

    public List<Leader> showTopFiveSeekers(){
        List<Leader> seekers = new ArrayList<Leader>();
        SqlRowSet allSeekersRow = jdbcTemplate.queryForRowSet("SELECT user_name from questions where DATE(post_date)=current_date GROUP BY user_name ORDER BY COUNT(user_name) DESC limit 5;");
        while (allSeekersRow.next()) {
            seekers.add(new Leader(allSeekersRow.getString(1)));
        }

        if(seekers.size()<5){
            allSeekersRow = jdbcTemplate.queryForRowSet("SELECT user_name from questions where DATE(post_date)<current_date GROUP BY user_name ORDER BY COUNT(user_name) DESC limit 5;");
            while(seekers.size()!=5 && allSeekersRow.next()){
                seekers.add(new Leader(allSeekersRow.getString(1)));
            }
        }
        return seekers;
    }

    public List<Leader> showTopFiveAdvisers(){
        List<Leader> advices = new ArrayList<Leader>();
        SqlRowSet allAdvisersRow = jdbcTemplate.queryForRowSet("SELECT user_name from answers where DATE(post_date)=current_date GROUP BY user_name ORDER BY COUNT(user_name) DESC limit 5;");
        while (allAdvisersRow.next()) {
            advices.add(new Leader(allAdvisersRow.getString(1)));
        }

        if(advices.size()<5){
            allAdvisersRow = jdbcTemplate.queryForRowSet("SELECT user_name from answers where DATE(post_date)<current_date GROUP BY user_name ORDER BY COUNT(user_name) DESC limit 5;");
            while(advices.size()!=5 && allAdvisersRow.next()){
                advices.add(new Leader(allAdvisersRow.getString(1)));
            }
        }

        return advices;
    }

    public List<Question> showRecentlyAdvisedQuestions(){
        SqlRowSet advice = jdbcTemplate.queryForRowSet("select q.q_id,q.question,q.post_date,q.user_name from questions As q JOIN answers As a On q.q_id = a.q_id where DATE(a.post_date)=current_date GROUP BY q.q_id,q.question,q.post_date,q.user_name ORDER BY COUNT(a.q_id) DESC limit 5;");
        List<Question> advices = new ArrayList<Question>();
        while (advice.next()) {
            Question question=  new Question(advice.getString(1),truncateQuestionToCharacterLimit(advice.getString(2)),advice.getString(3),advice.getString(4));
            advices.add(question);
        }
        if(advices.size()<5){
            advice = jdbcTemplate.queryForRowSet("select q.q_id,q.question,q.post_date,q.user_name from questions As q JOIN answers As a On q.q_id = a.q_id where DATE(a.post_date)<current_date GROUP BY q.q_id,q.question,q.post_date,q.user_name ORDER BY COUNT(a.q_id) DESC limit 5;");
            while(advices.size()!=5 && advice.next()){
                Question question=  new Question(advice.getString(1),truncateQuestionToCharacterLimit(advice.getString(2)),advice.getString(3),advice.getString(4));
                advices.add(question);
            }
        }
        return advices;
    }

    String truncateQuestionToCharacterLimit(String question) {
        String questionToTruncate = Jsoup.parse(question).text();
        return (questionToTruncate.length() <= CHARACTER_LIMIT) ? question : questionToTruncate.concat("<p>").substring(BEGIN_INDEX, CHARACTER_LIMIT).concat(TRAILING_CHARACTERS);
    }
}
