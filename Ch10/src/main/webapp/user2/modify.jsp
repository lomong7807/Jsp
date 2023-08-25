<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
	<html>
		<head>
		<meta charset="UTF-8">
		<title>user2::modify</title>
	</head>
	<body>
		<h3>User2 수정</h3>
		<a href="/Ch10/">메인</a>
		<a href="/Ch10/user2/list.do">User2 목록</a>
		<form action="/Ch10/user2/modify.do" method="post">
			<table border="1">
				<tr>
					<td>아이디</td>
					<!-- 표현언어에서는 private이라도 직접 참조 가능해서 getUid로 가져오지 않아도 된다. -->
					 <!-- requestScope, pageScope 등등 생략 가능하다. 아래에선 모두 requestScope를 생략한것. -->
					<td><input type="text" name="uid" readonly value="${user.uid}"></td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" name="name" value="${user.name}"></td>
				</tr>
				<tr>
					<td>휴대폰</td>
					<td><input type="text" name="hp" value="${user.hp}"></td>
				</tr>
				<tr>
					<td>나이</td>
					<td><input type="text" name="age" value="${user.age}"></td>
				</tr>
				<tr>
					<td colspan="2" align="right"><input type="submit" value="수정"></td>
				</tr>
			</table>
		</form>
		
	</body>
</html>