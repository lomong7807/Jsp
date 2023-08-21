<%@page import="kr.farmstory1.dto.ArticleDTO"%>
<%@page import="kr.farmstory1.dao.ArticleDAO"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String pg = request.getParameter("pg");
	String group = request.getParameter("group");
	String cate = request.getParameter("cate");
	String comment = request.getParameter("comment");
	String no = request.getParameter("no");
	String parent = request.getParameter("parent");
	
	ArticleDAO dao = new ArticleDAO();
	dao.updateComment(comment, no);
	
	ArticleDTO dto = new ArticleDTO();
	
	
	response.sendRedirect("/Farmstory1/view.jsp?group="+group+"&cate="+cate+"&no="+parent+"&pg="+pg);
%>