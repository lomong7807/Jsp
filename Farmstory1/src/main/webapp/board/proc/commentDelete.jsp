<%@page import="kr.farmstory1.dao.ArticleDAO"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String group = request.getParameter("group");
	String cate = request.getParameter("cate");
	String no = request.getParameter("no");
	String parent = request.getParameter("parent");

	ArticleDAO dao = new ArticleDAO();
	
	// 댓글 삭제
	dao.deleteComment(no);

	// 댓글 카운트 수정 Minus
	dao.updateArticleForCommentMinus(parent);
	
	response.sendRedirect("/Farmstory1/board/view.jsp?group="+group+"&cate="+cate+"&no="+parent);
%>