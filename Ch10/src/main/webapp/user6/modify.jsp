<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
	<html>
		<head>
		<meta charset="UTF-8">
		<title>user6::modify</title>
	</head>
	<body>
		<h3>user6 수정</h3>
		<a href="/Ch10/">메인</a>
		<a href="/Ch10/user6/list.do">user6 목록</a>
		<form action="/Ch10/user6/modify.do" method="post">
			<table border="1">
				<tr>
					<td>이름</td>
					<td><input type="text" name="name" readonly value="${user.name}"></td>
				</tr>
				<tr>
					<td>성별</td>
					<td>
						<select name="gender">
							<c:choose>
								<c:when test="${user.gender eq '1'}"><option selected>남자</option></c:when>
								<c:otherwise><option>남자</option></c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${user.gender eq '2'}"><option selected>여자</option></c:when>
								<c:otherwise><option>여자</option></c:otherwise>
							</c:choose>
						</select>
					</td>
				</tr>
				<tr>
					<td>나이</td>
					<td><input type="text" name="age" value="${user.age}"></td>
				</tr>
				<tr>
					<td>주소</td>
					<td><input type="text" name="addr" value="${user.addr}"></td>
				</tr>
				<tr>
					<td colspan="2" align="right"><input type="submit" value="등록"></td>
				</tr>
			</table>
		</form>
		
	</body>
</html>