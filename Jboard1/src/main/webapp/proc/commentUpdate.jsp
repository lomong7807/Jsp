<%@page import="kr.co.jboard1.dao.ArticleDAO"%>
<%@page import="kr.co.jboard1.DTO.ArticleDTO"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String comment = request.getParameter("comment");
	String no = request.getParameter("no");
	String parent = request.getParameter("parent");
	
	ArticleDAO dao = new ArticleDAO();
	dao.updateComment(comment, no);
	
	ArticleDTO dto = new ArticleDTO();
	
	
	response.sendRedirect("/Jboard1/view.jsp?no="+parent);
%>