<%@page import="kr.farmstory1.dao.UserDAO"%>
<%@page import="com.google.gson.JsonObject"%>
<%@page contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");

	String email = request.getParameter("email");
	
	int result = UserDAO.getInstance().selectCountEmail(email);
	
	JsonObject json = new JsonObject();
	json.addProperty("result", result);
	
	String jsonData = json.toString();
	out.print(jsonData);
	
%>