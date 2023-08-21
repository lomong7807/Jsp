<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
	<html>
		<head>
		<meta charset="UTF-8">
		<title>3_Loop</title>
		<!-- 
			날짜 : 2023/07/24
			이름 : 강윤수
			내용 : JSP 스크립트릿 반복문 실습하기
		 -->
	</head>
	<body>
		<h4>3.반복문</h4>
		
		<h3>for</h3>
		<%
			for(int i = 1; i <= 3; i++){
				out.println("<p>i : " + i + "</p>");
			}
		
		%>
		
		<% for(int i = 1; i <= 3; i++){ %>
			<p>i : <%= i  %></p>
		<% } %>
		
		<h3>while</h3>
		<%
			int j = 1;
		
			while(j <= 3){
		%>
			<p>j : <%= j %></p>
		<%
				j++;
			}
		%>
		<h3>구구단</h3>
		<table border="1">
			<tr>
				<th>2단</th>
				<th>3단</th>
				<th>4단</th>
				<th>5단</th>
				<th>6단</th>
				<th>7단</th>
				<th>8단</th>
				<th>9단</th>
			</tr>
			<tr>
				<td>2 X 1 = 2</td>
				<td>3 X 1 = 3</td>
				<td>4 X 1 = 4</td>
				<td>5 X 1 = 5</td>
				<td>6 X 1 = 6</td>
				<td>7 X 1 = 7</td>
				<td>8 X 1 = 8</td>
				<td>9 X 1 = 9</td>
			</tr>
			<% for(int i = 1; i <= 9; i++){ %>
			<tr>
				<% for(int k = 2; k <= 9; k++){ %>
					<td><%= k%> x <%= i %> = <%= k*i %></td>
				<% } %>
			</tr>
			<% } %>
		</table>
		
	</body>
</html>