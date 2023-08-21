<%@page import="kr.farmstory1.dao.UserDAO"%>
<%@page import="kr.farmstory1.dto.UserDTO"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");

	String uid = request.getParameter("uid");
	String pass = request.getParameter("pass");
	String target = request.getParameter("target");
	String group = request.getParameter("group");
	String cate = request.getParameter("cate");
	String no = request.getParameter("no");

	UserDTO user = UserDAO.getInstance().selectUser(uid, pass);
	
	// 회원 여부 확인(세션처리)
	if(user != null){
			// 사용자 세션 저장
			session.setAttribute("sessUser", user);
			
			if(target.equals("write")){
				response.sendRedirect("/Farmstory1/board/write.jsp?group="+group+"&cate="+cate);	
			}else if(target.equals("view")){
				response.sendRedirect("/Farmstory1/board/view.jsp?group="+group+"&cate="+cate+"&no="+no);			
			}else{
			// 리다이렉트
			response.sendRedirect("/Farmstory1/");			
			}
	}else{
		// 리다이렉트
		response.sendRedirect("/Farmstory1/user/login.jsp?success=100");
	}
%>