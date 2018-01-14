<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> 功能测试</title>
</head>
<body>
	hello
	<%-- <h1>${msg }</h1> --%>
	<c:forEach items="${xias }" var="xia">
		${xia.id }<br>
		${xia.latitude }<br>
		${xia.longitude }<br>
		${xia.type }<br>
		${xia.state }<br>
	</c:forEach>
</body>
</html>