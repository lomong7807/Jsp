<%@page import="vo.User2VO"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");

	String name = request.getParameter("name");
	String gender = request.getParameter("gender");
	String age = request.getParameter("age");
	String addr = request.getParameter("addr");
	
	String host = "jdbc:mysql://127.0.0.1:3306/userdb";
	String user = "root";
	String pass = "1234";
	
	List<User2VO> users = new ArrayList<>();
	
	try{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection(host, user, pass);
		Statement stmt = conn.createStatement();
		
		ResultSet rs = stmt.executeQuery("SELECT `name`,`gender`,`age`,`addr` FROM `user4`");
		
		while(rs.next()){
			User2VO vo = new User2VO();
			vo.setName(rs.getString(1));
			vo.setGender(rs.getInt(2));
			vo.setAge(rs.getInt(3));
			vo.setAddr(rs.getString(4));
			
			users.add(vo);
		}
		
		rs.close();
		stmt.close();
		conn.close();
		
	}catch(Exception e){
		e.printStackTrace();
	}
%>
<!DOCTYPE html>
	<html>
		<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
			<h3>User4 목록</h3>
		<a href="/Ch06/1_JDBC.jsp">처음으로</a>
		<a href="/Ch06/user4/register.jsp">User4 등록</a>
		
		<table border="1">
			<tr>
				<th>이름</th>
				<th>성별</th>
				<th>나이</th>
				<th>주소</th>
				<th>관리</th>
			</tr>
			<% for(User2VO vo : users){ %>
			<tr>
				<td><%= vo.getName() %></td>
				<td><%= vo.getGender() %></td>
				<td><%= vo.getAge() %></td>
				<td><%= vo.getAddr() %></td>
				<td>
					<a href="/Ch06/user4/modify.jsp?name=<%=vo.getName()%>">수정</a>
					<a href="/Ch06/user4/delete.jsp?name=<%=vo.getName()%>">삭제</a>
				</td>
			</tr>
			<% } %>
		
		
		</table>
		
	</body>
</html>