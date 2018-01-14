<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>一个用户</title>
</head>
<body>
	<img src="<%=request.getContextPath() %>${user.image }" style="width: 200px;height:200px"/><br>
	${user.image }<br>
	${user.id} <br>
	${user.name }<br>
	${user.pwd }<br>
	${user.nickname }<br>
	${user.email }<br>
	${user.nickname }<br>
	${user.lastLoginTime }<br>
	${user.registerTime }<br>
	${user.balance }<br>
	${user.points }<br>
</body>
</html>