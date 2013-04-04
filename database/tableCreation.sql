DROP TABLE IF EXISTS questions;
create table questions(q_id SERIAL UNIQUE,question varchar,post_date varchar(20) );

DROP TABLE IF EXISTS Login;
create table Login(username varchar(50),password varchar(50),loginFailCount INT,sessionStartTime DATE,PRIMARY KEY (username));

DROP TABLE IF EXISTS answers;
create table answers(ans_id serial,q_id int,answer varchar,post_date varchar(20));


