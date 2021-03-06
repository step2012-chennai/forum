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
    private static final int CHARACTER_LIMIT = 65;
    private static final int BEGIN_INDEX = 0;
    private static final String TRAILING_CHARACTERS = "...?</p>";

    @Autowired
    public ShowLeaders(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
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
        SqlRowSet advice = jdbcTemplate.queryForRowSet("select distinct q.q_id,q.question,q.post_date,q.user_name,array_to_string(array_agg(distinct t.tag_name), ' ') as tags,COUNT(a.q_id) as adviceCount from questions q JOIN answers As a On q.q_id = a.q_id LEFT JOIN questions_tags qt on q.q_id = qt.q_id LEFT JOIN tags t on t.t_id=qt.t_id where DATE(a.post_date)=current_date group by q.q_id,q.post_date,q.user_name,q.question ORDER BY adviceCount desc limit 5;");
        List<Question> advices = new ArrayList<Question>();
        while (advice.next()) {
            Question question=  new Question(advice.getString("q_id"), truncateQuestionToCharacterLimit(advice.getString("question")),advice.getString("post_date"),advice.getString("user_name"),advice.getString("tags"));
            advices.add(question);
        }
        if(advices.size()<5){
            advice = jdbcTemplate.queryForRowSet("select distinct q.q_id,q.question,q.post_date,q.user_name,array_to_string(array_agg(distinct t.tag_name), ' ') as tags,COUNT(a.q_id) as adviceCount from questions q JOIN answers As a On q.q_id = a.q_id LEFT JOIN questions_tags qt on q.q_id = qt.q_id LEFT JOIN tags t on t.t_id=qt.t_id where DATE(a.post_date)<current_date group by q.q_id,q.post_date,q.user_name,q.question ORDER BY adviceCount desc limit 5;");
            while(advices.size()!=5 && advice.next()){
                Question question=  new Question(advice.getString("q_id"), truncateQuestionToCharacterLimit(advice.getString("question")),advice.getString("post_date"),advice.getString("user_name"),advice.getString("tags"));
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
