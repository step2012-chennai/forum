--for userDetails test
insert into userDetails(username,password) values('sandeep','5f4dcc3b5aa765d61d8327deb882cf99');
insert into userDetails(username,password) values('gaurav','5f4dcc3b5aa765d61d8327deb882cf99');
insert into userDetails(username,password) values('anil','5f4dcc3b5aa765d61d8327deb882cf99');
insert into userDetails(username,password) values('prasad','5f4dcc3b5aa765d61d8327deb882cf99');
insert into userDetails(username,password) values('vikhyath','5f4dcc3b5aa765d61d8327deb882cf99');
insert into userDetails(username,password) values('ajit','5f4dcc3b5aa765d61d8327deb882cf99');
insert into userDetails(username,password) values('ravi','5f4dcc3b5aa765d61d8327deb882cf99');
insert into userDetails(username,password) values('prasanth','5f4dcc3b5aa765d61d8327deb882cf99');
insert into userDetails(username,password) values('bipilesh','5f4dcc3b5aa765d61d8327deb882cf99');
insert into userDetails(username,password) values('jaideep','5f4dcc3b5aa765d61d8327deb882cf99');
insert into userDetails(username,password) values('prakash','5f4dcc3b5aa765d61d8327deb882cf99');
insert into userDetails(username,password) values('suganthi','5f4dcc3b5aa765d61d8327deb882cf99');
insert into userDetails(username,password) values('geetha','5f4dcc3b5aa765d61d8327deb882cf99');
insert into userDetails(username,password) values('kalai','5f4dcc3b5aa765d61d8327deb882cf99');
insert into userDetails(username,password) values('user','5f4dcc3b5aa765d61d8327deb882cf99');
insert into userDetails(username,password) values('seeker','15b421d36a6cc2028ff1d0f8f29c65ab');
insert into userDetails(username,password) values('advisor','90855e629cbebcc7db5dfc4d79dafa06');

insert into questions(question,post_date,user_name)   values
                ('<p>What is java ?</p>',CURRENT_TIMESTAMP(0),'Sandeep'),
                ('<p>How to connect with postgresql in java ?</p>',CURRENT_TIMESTAMP(0),'Bipilesh'),
                ('<p>Where is thought works in chennai ?</p>',CURRENT_TIMESTAMP(0),'Anil'),
                ('<p>How to write insert command in sql ?</p>',CURRENT_TIMESTAMP(0),'Bipilesh'),
                ('<p>How to use spring ?</p>',CURRENT_TIMESTAMP(0),'Sandeep'),
                ('<p>What is servlet ?</p>',CURRENT_TIMESTAMP(0),'Bipilesh'),
                ('<p>Can i use driver for connection to database ?</p>',CURRENT_TIMESTAMP(0),'Anil'),
                ('<p>Who is father of computer ?</p>',CURRENT_TIMESTAMP(0),'Anil'),
                ('<p>How to create android application ?</p>',CURRENT_TIMESTAMP(0),'Sandeep'),
                ('<p>How to use intellej for web application</p>',CURRENT_TIMESTAMP(0),'Bipilesh'),
                ('<p>Where is Marina Beach in India ?</p>',CURRENT_TIMESTAMP(0),'Sandeep'),
                ('<p>How is it one careless match can start a forest fire, but it takes a whole box to start a campfire?</p>',CURRENT_TIMESTAMP(0),'Sandeep'),
                ('<p>Whats your name in you office?</p>',CURRENT_TIMESTAMP(0),'Sandeep'),
                ('<p>Whats your pet animal name?</p>',CURRENT_TIMESTAMP(0),'Sandeep'),
                ('<p>Whats your pet name?</p>',CURRENT_TIMESTAMP(0),'Sandeep'),
                ('<p>Whats your nick name?</p>',CURRENT_TIMESTAMP(0),'Sandeep'),
                ('<p>Whats your father name?</p>',CURRENT_TIMESTAMP(0),'Sandeep'),
                ('<p>Whats your mother name?</p>',CURRENT_TIMESTAMP(0),'Sandeep'),
                ('<p>Whats your school name?</p>',CURRENT_TIMESTAMP(0),'Sandeep'),
                ('<p>Whats your native name?</p>',CURRENT_TIMESTAMP(0),'Ajith'),
                ('<p>Whats your favourite food?</p>',CURRENT_TIMESTAMP(0),'Sandeep'),
                ('<p>Whats your favourite play?</p>',CURRENT_TIMESTAMP(0),'Sandeep'),
                ('<p>Whats your surname?</p>',CURRENT_TIMESTAMP(0),'Sandeep'),
                ('<p>Whats your middle name?</p>',CURRENT_TIMESTAMP(0),'Sandeep'),
                ('<p>Whats your lastname?</p>',CURRENT_TIMESTAMP(0),'Sandeep'),
                ('<p>Whats your good name?</p>',CURRENT_TIMESTAMP(0),'Sandeep'),
                ('<p>Where are you from? / Where do you come from?</p>',CURRENT_TIMESTAMP(0),'Sandeep'),
                ('<p>Whats your surname / family name?</p>',CURRENT_TIMESTAMP(0),'Sandeep'),
                ('<p>Whats your first name?</p>',CURRENT_TIMESTAMP(0),'Ravi'),
                ('<p>Whats your address?</p>',CURRENT_TIMESTAMP(0),'Sandeep'),
                ('<p>Where do you live?</p>',CURRENT_TIMESTAMP(0),'Sandeep'),
                ('<p>Whats your (tele)phone number?</p>',CURRENT_TIMESTAMP(0),'Sandeep'),
                ('<p>How old are you?</p>',CURRENT_TIMESTAMP(0),'Sandeep'),
                ('<p>When / Where were you born?</p>',CURRENT_TIMESTAMP(0),'Sandeep'),
                ('<p>Are you married? / Whats your marital status?</p>',CURRENT_TIMESTAMP(0),'Sandeep'),
                ('<p>What do you do? / Whats your job?</p>',CURRENT_TIMESTAMP(0),'Sandeep'),
                ('<p>Where did you go?</p>',CURRENT_TIMESTAMP(0),'Sandeep'),
                ('<p>What did you do?</p>',CURRENT_TIMESTAMP(0),'Sandeep'),
                ('<p>Where were you?</p>',CURRENT_TIMESTAMP(0),'Sandeep'),
                ('<p>Have you got a car / job / house / etc.?</p>',CURRENT_TIMESTAMP(0),'Sandeep'),
                ('<p>Have you got any children / friends / books / etc.?</p>',CURRENT_TIMESTAMP(0),'Sandeep'),
                ('<p>Can you play tennis / golf / football / etc.?</p>',CURRENT_TIMESTAMP(0),'Sandeep');

-- for details of Question

insert into answers(q_id,answer,post_date,user_name) values((select MAX(q_id-1) from questions),'<p>answer for 11 question </p>. 1',CURRENT_TIMESTAMP(0),'Ravi');
insert into answers(q_id,answer,post_date,user_name) values((select MAX(q_id-2) from questions),'<p>answer for 11 question </p>. 2',CURRENT_TIMESTAMP(0),'Gaurav');
insert into answers(q_id,answer,post_date,user_name) values((select MAX(q_id-3) from questions),'<p>answer for 11 question </p>. 3',CURRENT_TIMESTAMP(0),'Ajit');
insert into answers(q_id,answer,post_date,user_name) values((select MAX(q_id-6) from questions),'<p>answer for 11 question </p>. 4',CURRENT_TIMESTAMP(0),'Prasath');
insert into answers(q_id,answer,post_date,user_name) values((select MAX(q_id-2) from questions),'<p>answer for 11 question</p>. 5',CURRENT_TIMESTAMP(0),'Gaurav');
insert into answers(q_id,answer,post_date,user_name) values((select MAX(q_id-4) from questions)-1,'<p>answer for 10 question</p>. 1',CURRENT_TIMESTAMP(0),'Ajit');
insert into answers(q_id,answer,post_date,user_name) values((select MAX(q_id) from questions)-1,'<p>answer for 10 question</p>. 2',CURRENT_TIMESTAMP(0),'Prasath');
insert into answers(q_id,answer,post_date,user_name) values((select MAX(q_id-5) from questions),'<p>answer for 10 question</p>. 3',CURRENT_TIMESTAMP(0),'Ajit');
insert into answers(q_id,answer,post_date,user_name) values((select MAX(q_id) from questions),'<p>answer for 10 question</p>. 4',CURRENT_TIMESTAMP(0),'Gaurav');
insert into answers(q_id,answer,post_date,user_name) values((select MAX(q_id) from questions),'<p>answer for 9 question</p>. 1',CURRENT_TIMESTAMP(0),'Ajit');
insert into answers(q_id,answer,post_date,user_name) values((select MAX(q_id) from questions),'<p>answer for 9 question</p>. 2',CURRENT_TIMESTAMP(0),'Ravi');
insert into answers(q_id,answer,post_date,user_name) values((select MAX(q_id) from questions),'<p>answer for 9 question</p>. 3',CURRENT_TIMESTAMP(0),'Prasath');
insert into answers(q_id,answer,post_date,user_name) values((select MAX(q_id) from questions),'<p>answer for 9 question</p>. 4',CURRENT_TIMESTAMP(0),'Gaurav');
insert into answers(q_id,answer,post_date,user_name) values((select MAX(q_id) from questions),'<p>answer for 9 question</p>. 5',CURRENT_TIMESTAMP(0),'Ravi');
insert into answers(q_id,answer,post_date,user_name) values((select MAX(q_id-1) from questions),'<p>answer for 8 question</p>. 1',CURRENT_TIMESTAMP(0),'Prasath');
insert into answers(q_id,answer,post_date,user_name) values((select MAX(q_id-3) from questions),'<p>answer for 8 question</p>. 2',CURRENT_TIMESTAMP(0),'Ajit');
insert into answers(q_id,answer,post_date,user_name) values((select MAX(q_id) from questions),'<p>answer for 7 question</p>. 1',CURRENT_TIMESTAMP(0),'Prasath');
insert into answers(q_id,answer,post_date,user_name) values((select MAX(q_id) from questions),'<p>answer for 7 question</p>. 2',CURRENT_TIMESTAMP(0),'Prakash');
insert into answers(q_id,answer,post_date,user_name) values((select MAX(q_id) from questions),'<p>answer for 7 question</p>. 3',CURRENT_TIMESTAMP(0),'Ravi');
insert into answers(q_id,answer,post_date,user_name) values((select MAX(q_id) from questions),'<p>answer for 7 question</p>. 4',CURRENT_TIMESTAMP(0),'Gaurav');
insert into answers(q_id,answer,post_date,user_name) values((select MAX(q_id) from questions),'<p>answer for 7 question</p>. 5',CURRENT_TIMESTAMP(0),'Ajit');
insert into answers(q_id,answer,post_date,user_name) values((select MAX(q_id) from questions),'<p>answer for 7 question</p>. 6',CURRENT_TIMESTAMP(0),'Prasath');
insert into answers(q_id,answer,post_date,user_name) values((select MAX(q_id) from questions),'<p>answer for 7 question</p>. 7',CURRENT_TIMESTAMP(0),'Ravi');
insert into answers(q_id,answer,post_date,user_name) values((select MAX(q_id) from questions),'<p>answer for 7 question</p>. 8',CURRENT_TIMESTAMP(0),'Prakash') ;
insert into answers(q_id,answer,post_date,user_name) values((select MAX(q_id) from questions),'<p>answer for 7 question</p>. 8',CURRENT_TIMESTAMP(0),'Gaurav');--

