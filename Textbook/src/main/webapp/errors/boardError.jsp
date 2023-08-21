<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" 
												isErrorPage="true"
												import="java.util.Date"%>
<!DOCTYPE html>
	<html>
		<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<%--
			날짜 : 2023/07/24
			이름 : 강윤수
		 --%>
	</head>
	<body>
		<center>
		<h3><%= exception.getClass().getName() %>발생!</h3>
		<% Date currentTime = new Date(); %>
		예외 발생 시간 : <%= currentTime.toString() %>
		<hr>
		<% StackTraceElement[] elements = exception.getStackTrace(); %>
		예외 위치 : <%= elements[0].toString() %>
		</center>
	</body>
</html>