package kr.co.farmstory2.cotroller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;

import kr.co.farmstory2.dto.ArticleDTO;
import kr.co.farmstory2.service.ArticleService;

@WebServlet("/comment.do")
public class CommentController extends HttpServlet{

	private static final long serialVersionUID = -8380122377692415291L;
	private ArticleService service = ArticleService.INSTANCE;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int modifyResult = 0;
		
		String no = req.getParameter("no");
		String content = req.getParameter("content");

		System.out.println("modifyComment no : "+ no);
		System.out.println("modifyComment content : "+ content);
		
		modifyResult = service.updateComment(no, content); 
		
		//JSON 출력
		JsonObject json = new JsonObject();
		json.addProperty("result", modifyResult);
		resp.getWriter().print(json);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String parent = req.getParameter("parent");
		String writer = req.getParameter("writer");
		String content = req.getParameter("content");
		String regip = req.getRemoteAddr();
		
		if(writer == null) {
			int deleteResult = 0;
			int parentComment = 0;
			String kind = req.getParameter("kind");
			String no = req.getParameter("no");
			
			logger.debug("no : "+no);
			
			switch(kind) {
				case "REMOVE" :
					deleteResult = service.deleteComment(no);
					
					System.out.println("comment parent : "+parent);
					service.updateArticleForCommentMinus(parent);
					parentComment = service.selectArticleComment(parent);
			}
			
			//JSON 출력
			JsonObject json = new JsonObject();
			json.addProperty("result", deleteResult);
			json.addProperty("parentComment", parentComment);
			resp.getWriter().print(json);
		}else {
			System.out.println("comment parent : "+parent);
			System.out.println("comment writer : "+writer);
			System.out.println("comment content : "+content);
			System.out.println("comment regip : "+regip);
			
			ArticleDTO dto = new ArticleDTO();
			dto.setParent(parent);
			dto.setWriter(writer);
			dto.setContent(content);
			dto.setRegip(regip);
			
			//댓글 입력
			int[] insertResult = service.insertComment(dto);
			int result = insertResult[0];
			// 방금 입력받은 댓글의 no값
			int maxNo  = insertResult[1];
			
			// 댓글 카운트 수정
			service.updateArticleForCommentPlus(parent);
			
			int parentComment = service.selectArticleComment(parent);
			//Json 출력(AJAX 요청)
			JsonObject json = new JsonObject();
			json.addProperty("result", result);
			json.addProperty("maxNo", maxNo);
			json.addProperty("parent", parent);
			json.addProperty("parentComment", parentComment);
			resp.getWriter().print(json);
		}
		
	}
}
