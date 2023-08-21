<%@page import="vo.User2VO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");

	String name = request.getParameter("name");
	
	String host = "jdbc:mysql://127.0.0.1:3306/userdb";
	String user = "root";
	String pass = "1234";
	
	User2VO vo = new User2VO();
	
	try{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection(host, user, pass);
		PreparedStatement psmt = conn.prepareStatement("SELECT `name`,`gender`,`age`,`addr` FROM `user4` WHERE `name`=?");
		psmt.setString(1, name);
		
		ResultSet rs = psmt.executeQuery();
		
		if(rs.next()){
			vo.setName(rs.getString(1));
			vo.setGender(rs.getInt(2));
			vo.setAge(rs.getInt(3));
			vo.setAddr(rs.getString(4));
		}
		
		rs.close();
		conn.close();
		psmt.close();
		
	}catch(Exception e){
		e.printStackTrace();
	}
%>
<!DOCTYPE html>
	<html>
		<head>
		<meta charset="UTF-8">
		<title>user4::modify</title>
	</head>
	<body>
		<h3>User4 수정</h3>
		<a href="/Ch06/1_JDBC.jsp">처음으로</a>
		<a href="/Ch06/user4/list.jsp">User4 목록</a>
		<form action="/Ch06/user4/modifyProc.jsp" method="post">
			<table border="1">
				<tr>
					<td>아이디</td>
					<td><input type="text" name="name" readonly value="<%= vo.getName()%>"></td>
				</tr>
				<tr>
					<td>성별</td>
					<td>
						<input type="radio" name="gender" value="1"<%= vo.getGender() == 1 ? "checked" : "" %>>남자
						<input type="radio" name="gender" value="2"<%= vo.getGender() == 2 ? "checked" : "" %>>여자
					</td>
				</tr>
				<tr>
					<td>나이</td>
					<td><input type="text" name="age" value="<%= vo.getAge()%>"></td>
				</tr>
				<tr>
					<td>주소</td>
					<td><input type="text" name="addr" value="<%= vo.getAddr()%>"></td>
				</tr>
				<tr>
					<td colspan="2" align="right"><input type="submit" value="수정"></td>
				</tr>
			</table>
		</form>
	</body>
</html>