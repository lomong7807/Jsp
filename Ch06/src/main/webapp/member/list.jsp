<%@page import="vo.MemberVO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="com.mysql.cj.protocol.Resultset"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");

	String uid = request.getParameter("uid");
	String name = request.getParameter("name");
	String hp = request.getParameter("hp");
	String rank = request.getParameter("rank");
	String dep = request.getParameter("dep");

	List<MemberVO> members = new ArrayList<>();
	
	try{
		Context initCtx = new InitialContext();
		Context ctx = (Context)initCtx.lookup("java:comp/env");
		
		DataSource ds = (DataSource)ctx.lookup("jdbc/userdb");
		Connection conn = ds.getConnection();

		Statement stmt = conn.createStatement();
		
		ResultSet rs = stmt.executeQuery("SELECT `uid`,`name`,`hp`,`pos`,`dep` FROM `member`");
		
		while(rs.next()){
			
			MemberVO vo = new MemberVO();
			vo.setUid(rs.getString(1));
			vo.setName(rs.getString(2));
			vo.setHp(rs.getString(3));
			vo.setRank(rs.getString(4));
			vo.setDep(rs.getInt(5));
			
			members.add(vo);
			
		}
		
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
	<h3>Member 목록</h3>
		<a href="/Ch06/1_JDBC.jsp">처음으로</a>
		<a href="/Ch06/member/register.jsp">member 등록</a>
	<table border="1">
		<tr>
			<th>아이디</th>
			<th>이름</th>
			<th>휴대폰</th>
			<th>직급</th>
			<th>부서</th>
			<th>관리</th>
		</tr>
		<%for(MemberVO vo : members) {%>
		<tr>
			<td><%= vo.getUid() %></td>
			<td><%= vo.getName() %></td>
			<td><%= vo.getHp() %></td>
			<td><%= vo.getRank() %></td>
			<td><%= vo.getDep() %></td>
			<td>
				<a href="/Ch06/member/modify.jsp?uid=<%=vo.getUid()%>">수정</a>
				<a href="/Ch06/member/delete.jsp?uid=<%=vo.getUid()%>">삭제</a>
			</td>
		</tr>
		<%} %>
	</table>
		
	</body>
</html>