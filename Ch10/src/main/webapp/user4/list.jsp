<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
	<html>
		<head>
		<meta charset="UTF-8">
		<title>user4::list</title>
	</head>
	<body>
		<h3>user4 목록</h3>
		<a href="/Ch10/">메인</a>
		<a href="/Ch10/user4/register.do">등록</a>
		
		<table border="1">
			<tr>
				<th>이름</th>
				<th>성별</th>
				<th>나이</th>
				<th>주소</th>
				<th>관리</th>
			</tr>
			<!-- JSTL을 사용해서 반복문 출력 -->
			<c:forEach var="user" items="${requestScope.users}">
				<tr>
					<td>${user.name}</td>
					<c:choose>
						<c:when test="${user.gender eq 1}"><td>남자</td></c:when>
						<c:otherwise><td>여자</td></c:otherwise>
					</c:choose>
					<td>${user.age}</td>
					<td>${user.addr}</td>
					<td>
						<a href="/Ch10/user4/modify.do?name=${user.getName()}">수정</a>
						<a href="/Ch10/user4/delete.do?name=${user.getName()}">삭제</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html> 