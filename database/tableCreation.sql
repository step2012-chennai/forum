DROP TABLE IF EXISTS answers;
DROP TABLE IF EXISTS questions;
create table questions(q_id SERIAL UNIQUE,question varchar,post_date timestamp,user_name varchar,question_tsvector tsvector,tag text);

create table answers(ans_id serial,q_id int references questions(q_id),answer varchar,post_date timestamp,user_name varchar);

DROP TABLE IF EXISTS userDetails;
create table userDetails(username varchar(50),name varchar(50),password varchar(50),dob varchar(15),email varchar(50),location varchar(200),gender varchar(10),PRIMARY KEY (username));

DROP TEXT SEARCH DICTIONARY if exists english_stem_nostop cascade;

CREATE TEXT SEARCH DICTIONARY english_stem_nostop (
    Template = snowball,
    Language = english
);

CREATE TEXT SEARCH CONFIGURATION public.english_nostop ( COPY = pg_catalog.english );

ALTER TEXT SEARCH CONFIGURATION public.english_nostop ALTER MAPPING FOR asciiword, asciihword, hword_asciipart, hword, hword_part, word WITH english_stem_nostop;

CREATE TRIGGER tsvectorupdate BEFORE INSERT OR UPDATE ON questions FOR EACH ROW EXECUTE PROCEDURE tsvector_update_trigger('question_tsvector', 'public.english_nostop', 'question');
