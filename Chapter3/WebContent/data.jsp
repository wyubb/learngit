<%@ page language="java" contentType="text/html; charset=gbk"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.*,domain.*,service.imp.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>更新个人资料1</title>
</head>
<body>
<jsp:useBean id="infoService" class="service.imp.InfoService" scope="session"></jsp:useBean>	
		<div id="menu">
				<a href =	 "data.jsp">个人资料</a>		
				<a href = "main.jsp">公示信息浏览</a>
				<a href = "class.jsp">班级列表</a>
				<a href = "register.jsp">添加管理员</a>			
				<a href = "addinformation.jsp">发布信息</a>
				<a href = "addclasses.jsp">添加班级</a>
				<a href = "class_student_detail.jsp">所有学生信息</a>
				<a href = "login.jsp">退出</a>
				<h3>管理员：<%=((User)session.getAttribute("user")).getUserName() %></h3>				
		</div>
		<div>

		<form action="process_data.jsp" method="post">
		<table>
			<tr>
				<td>
					<label>真实姓名</label>
				</td>
				<td>
					<input type = "text" name = "realName" value = "<%=((User)session.getAttribute("user")).getRealName() %>"/>
				</td>		
			</tr>
			<tr>
				<td>
					<label>性别</label>
				</td>
				<td>
					<input type = "text" name = "gender" value = "<%=((User)session.getAttribute("user")).getGender() %>" />
				</td>
			</tr>
			<tr>
				<td>
					<label>年龄</label>
				</td>
				<td>
					<input type = "text" name = "age" value = "<%=((User)session.getAttribute("user")).getAge() %>" />
				</td>
			</tr>
			<tr>
				<td>
					<label>备注</label>
				</td>
				<td>
					<input type = "text" name = "personalSignature" value = "<%=((User)session.getAttribute("user")).getPersonalSignature()%>" />
				</td>
			</tr>
			<tr>
				<td>
					<label>密码</label>
				</td>
				<td>
					<input type = "password" name = "password" value = "<%=((User)session.getAttribute("user")).getPassword() %>"/>
				</td>		
			</tr>
			<tr>
				<td>
					<input type = "submit" value = "保存">
				</td>
			</tr>	
		</table>
		</form>
		</div>
		
</body>
</html>