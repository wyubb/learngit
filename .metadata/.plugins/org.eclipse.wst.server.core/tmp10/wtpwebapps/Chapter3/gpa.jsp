<%@ page language="java" contentType="text/html; charset=gbk"%>
<%@page import="java.util.*,domain.*,service.imp.*,dao.imp.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>绩点情况</title>
</head>
<body>

<div id="menu">
				<a href =	 "student_data.jsp">个人资料</a>
				<a href = "gpa.jsp"	>查看绩点情况</a>	
				<a href ="">综合测评</a>
				<a href = "student_main.jsp">公示信息浏览</a>
				<a href = "photo.jsp"	>上传图片</a>			
				<a href = "login.jsp">退出</a>		
</div>
<jsp:useBean id="studentService" class="service.imp.StudentService" scope="session"></jsp:useBean>
<%
    //调用业务逻辑层方法，获取所有信息列表
	List<Student> list = studentService.getClassStudent(((Student)session.getAttribute("student")).getStudent_class());
%>

<p>学号：<%=((Student)session.getAttribute("student")).getStudent_id() %></p>
<p>你的绩点：<%=((Student)session.getAttribute("student")).getGpa()%></p>
<p>你在班级的排名：<%=((Student)session.getAttribute("student")).getClass_gpa()%></p>
<p>你在专业的排名：</p>
</body>
</html>