<%@ page language="java" contentType="text/html; charset=gbk"%>
<%@page import="java.util.*,domain.*,service.imp.*,dao.imp.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>添加学生</title>
</head>
<body>
<div>		
		<div id="menu">
				<a href =	 "data.jsp">个人资料</a>		
				<a href = "main.jsp">公示信息浏览</a>
				<a href = "class.jsp">班级列表</a>
				<a href = "register.jsp">添加管理员</a>			
				<a href = "addinformation.jsp">发布信息</a>
				<a href = "addclasses.jsp">添加班级</a>
				<a href = "class_student_detail.jsp">所有学生信息</a>
				<a href = "">添加学生</a>
				<a href = "login.jsp">退出</a>
				<h3>管理员：<%=((User)session.getAttribute("user")).getUserName() %></h3>				
		</div>
<div>
		<form action="process_addstudent.jsp" method="post">
		<table>
		<tr>
				<td>
					<label>学号</label>
				</td>
				<td>
					<input type = "text" name = "student_id" "/>
				</td>		
			</tr>
			<tr>
				<td>
					<label>真实姓名</label>
				</td>
				<td>
					<input type = "text" name = "student_name" "/>
				</td>		
			</tr>
			<tr>
				<td>
					<label>性别</label>
				</td>
				<td>
					<input type = "text" name = "gender" " />
				</td>
			</tr>
			<tr>
				<td>
					<label>电话号码</label>
				</td>
				<td>
					<input type = "text" name = "student_tel" />
				</td>
			</tr>
			<tr>
				<td>
					<label>年级</label>
				</td>
				<td>
					<input type = "text" name = "grade"  />
				</td>
			</tr>
			<tr>
				<td>
					<label>班级</label>
				</td>
				<td>
					<input type = "text" name = "student_class"  />
				</td>
			</tr>
			<tr>
				<td>
					<label>密码</label>
				</td>
				<td>
					<input type = "password" name = "student_password" />
				</td>		
			</tr>
			<tr>
				<td>
					<input type = "submit" value = "保存">
				</td>
			</tr>	
		
		</div>
</body>
</html>