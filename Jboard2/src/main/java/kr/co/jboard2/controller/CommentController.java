package kr.co.jboard2.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.jboard2.dto.ArticleDTO;
import kr.co.jboard2.dto.UserDTO;
import kr.co.jboard2.service.ArticleService;

@WebServlet("/comment.do")
public class CommentController extends HttpServlet{
	private static final long serialVersionUID = 7686345524627400840L;

	private ArticleService service = ArticleService.INSTANCE;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 현재 세션 가져오기
		HttpSession session = req.getSession();
		UserDTO sessUser = (UserDTO)session.getAttribute("sessUser");
		
		String parent = req.getParameter("no");
		String writer = sessUser.getUid();
		String content = req.getParameter("commentContent");
		String regip = req.getRemoteAddr();
		
		System.out.println("parent : "+parent);
		System.out.println("writer : "+writer);
		System.out.println("content : "+content);
		System.out.println("regip : "+regip);
		
		
		ArticleDTO dto = new ArticleDTO();
		dto.setParent(parent);
		dto.setWriter(writer);
		dto.setContent(content);
		dto.setRegip(regip);
		
		service.insertComment(dto);
		
		//RequestDispatcher dispatcher = req.getRequestDispatcher("/view.jsp");
		//dispatcher.forward(req, resp);
		resp.sendRedirect("/Jboard2/view.do?no="+parent);
	}
}
