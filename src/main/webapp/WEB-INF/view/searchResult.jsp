No result

<html>
  <head>
    <title>Searched Questions</title>
  </head>
  <body>
  <%@page import="java.util.*,java.sql.*,com.forum.repository.*,org.springframework.context.ApplicationContext,org.springframework.context.support.ClassPathXmlApplicationContext,org.springframework.jdbc.support.rowset.SqlRowSet"%>
  <%
  ApplicationContext context = new ClassPathXmlApplicationContext("file:./config.xml");
      BasicTextSearch basicTextSearch = (BasicTextSearch) context.getBean("search");
            List<SearchQuestion> list=new ArrayList<SearchQuestion>();
              list = basicTextSearch.search("this");
                for (SearchQuestion searchQuestion : list) {
                      out.println(searchQuestion.getQuestion());
                }
  %>
  </body>
</html>