<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>List</title>
	</head>
	<body>
	<a href="/Ch11/">메인</a>
	<a href="/Ch11/register.do">직원등록</a>
		<table border="1">
			<tr>
				<th>아이디</th>
				<th>이름</th>
				<th>휴대폰</th>
				<th>직급</th>
				<th>부서</th>
				<th>입사일</th>
				<th>관리</th>
			</tr>
			<!-- JSTL을 사용해서 반복문 출력 -->
			<c:forEach var="member" items="${requestScope.members}">
				<tr>
					<td>${member.getUid() }</td>
					<td>${member.getName() }</td>
					<td>${member.getHp() }</td>
					<td>${member.getPos() }</td>
					<td>${member.getDep() }</td>
					<td>${member.getRdate() }</td>
					<td>
						<a href="/Ch11/modify.do?uid=${member.getUid()}">수정</a>
						<a href="/Ch11/delete.do?uid=${member.getUid()}">삭제</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>