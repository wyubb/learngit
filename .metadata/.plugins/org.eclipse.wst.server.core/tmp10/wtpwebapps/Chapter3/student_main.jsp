<%@ page language="java" contentType="text/html; charset=gbk"%>
<%@page import="java.util.*,domain.*,service.imp.*,dao.imp.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>学生界面-首页</title>
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
<jsp:useBean id="infoService" class="service.imp.InfoService" scope="session"></jsp:useBean>
<%
    //调用业务逻辑层方法，获取所有信息列表
	List<Information> list = infoService.getAllInfo();
%>
<div class="post">	
			<h1>公告栏</h>				
                    <%
                    //循环帖子列表，显示日志信息
					for(Information info:list)
					{						
					%>
					<div class="content">
 						<!-- 读者请注意此处的Java表达式运用 -->
						<p><a href="detail.jsp?Id=<%=info.getId()%>"><%=info.getTitle()%></a>&nbsp;&nbsp;<%=info.getPublishingtime()%></p>
					</div>			
					<%						
						}
					%>										
</div>	
</body>
</html>