<%@page import="vo.User5VO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");

	String uid = request.getParameter("uid");
	String name = request.getParameter("name");
	String date = request.getParameter("date");
	String gender = request.getParameter("gender");
	String age = request.getParameter("age");
	String addr = request.getParameter("addr");
	String hp = request.getParameter("hp");
	
	User5VO vo = new User5VO();
	
	try{
		Context initCtx = new InitialContext();
		Context ctx =  (Context)initCtx.lookup("java:comp/env");
		
		DataSource ds = (DataSource)ctx.lookup("jdbc/userdb");
		Connection conn = ds.getConnection();
		
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM `user5`");
		
		
		
		if(rs.next()){
			vo.setUid(rs.getString(1));
			vo.setName(rs.getString(2));
			vo.setDate(rs.getString(3));
			vo.setGender(rs.getInt(4));
			vo.setAge(rs.getInt(5));
			vo.setAddr(rs.getString(6));
			vo.setHp(rs.getString(7));
			
		}
		
		conn.close();
		rs.close();
		stmt.close();
		
		
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
		<h3>User5 수정</h3>
		
		<a href="/Ch06/1_JDBC.jsp">처음으로</a>
		<a href="/Ch06/user5/list.jsp">User5 목록</a>
		<form action="/Ch06/user5/registerProc.jsp" method="post">
			<table border="1">
				<tr>
					<td>아이디</td>
					<td><input type="text" name="uid" readonly value="<%= vo.getUid() %>"></td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" name="name" value="<%= vo.getName() %>"></td>
				</tr>
				<tr>
					<td>생년월일</td>
					<td><input type="date" name="date" value="<%= vo.getDate() %>"></td>
				</tr>
				<tr>
					<td>성별</td>
					<td>
					<input type="radio" name="gender" value="1" value="<%= vo.getGender() == 1 ? "checked" : "" %>">남자
					<input type="radio" name="gender" value="2" value="<%= vo.getGender() == 2 ? "checked" : "" %>">여자
					</td>
				</tr>
				<tr>
					<td>나이</td>
					<td><input type="text" name="age" value="<%= vo.getAge() %>"></td>
				</tr>
				<tr>
					<td>주소</td>
					<td><input type="text" name="addr" value="<%= vo.getAddr() %>"></td>
				</tr>
				<tr>
					<td>휴대폰</td>
					<td><input type="text" name="hp" value="<%= vo.getHp() %>"></td>
				</tr>
				<tr>
					<td colspan="2" align="right"><input type="submit" value="등록"></td>
				</tr>
			</table>
		</form>
	</body>
</html>