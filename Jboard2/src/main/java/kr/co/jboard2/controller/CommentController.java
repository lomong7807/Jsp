package kr.co.jboard2.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;

import kr.co.jboard2.dto.ArticleDTO;
import kr.co.jboard2.dto.UserDTO;
import kr.co.jboard2.service.ArticleService;

@WebServlet("/comment.do")
public class CommentController extends HttpServlet{
	private static final long serialVersionUID = 7686345524627400840L;

	private ArticleService service = ArticleService.INSTANCE;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String kind = req.getParameter("kind");
		String no 	= req.getParameter("no");
		String parent = req.getParameter("parent");
		
		logger.debug("no : "+ no);
		
		int result = 0;
		
		switch(kind) {
			case "REMOVE":
				result = service.deleteComment(no);
				service.updateArticleForCommentMinus(parent);
				break;
		}
		
		
		
		// JSON 출력
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		resp.getWriter().print(json);
	}
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 현재 세션 가져오기
		HttpSession session = req.getSession();
		UserDTO sessUser = (UserDTO)session.getAttribute("sessUser");
		
		String parent = req.getParameter("parent");
		String writer = req.getParameter("writer");
		String content = req.getParameter("content");
		String regip = req.getRemoteAddr();
		
		logger.debug("parent : "+parent);
		logger.debug("writer : "+writer);
		logger.debug("content : "+content);
		logger.debug("regip : "+regip);
		
		System.out.println("parent : "+parent);
		System.out.println("writer : "+writer);
		System.out.println("content : "+content);
		System.out.println("regip : "+regip);
		
		
		ArticleDTO dto = new ArticleDTO();
		dto.setParent(parent);
		dto.setWriter(writer);
		dto.setContent(content);
		dto.setRegip(regip);
		
		// 댓글 입력
		int result = service.insertComment(dto);
		// 댓글 카운트 수정
		service.updateArticleForCommentPlus(parent);
		
		// json을 리턴하기 때문에 리다이렉트는 아님
		// 라이다이렉트(폼 전송)
		//resp.sendRedirect("/Jboard2/view.do?no="+parent);
		
		// Json 출력(AJAX 요청)
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		resp.getWriter().print(json);
	}
}
