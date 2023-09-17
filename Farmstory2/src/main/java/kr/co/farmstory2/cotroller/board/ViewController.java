package kr.co.farmstory2.cotroller.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.farmstory2.dto.ArticleDTO;
import kr.co.farmstory2.dto.FileDTO;
import kr.co.farmstory2.service.ArticleService;
import kr.co.farmstory2.service.FileService;

@WebServlet("/board/view.do")
public class ViewController extends HttpServlet{

	private static final long serialVersionUID = 6260161866113034013L;
	
	private ArticleService aService = ArticleService.INSTANCE;
	private FileService fService = FileService.INSTANCE;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String group = req.getParameter("group");
		String cate = req.getParameter("cate");
		String no = req.getParameter("no");

		System.out.println("group : "+group);
		System.out.println("cate : "+cate);
		System.out.println("no : "+no);
		
		// 글 조회
		ArticleDTO article = aService.selectArticle(no);
		// 댓글 조회
		List<ArticleDTO> comments = aService.selectComments(no);
		// 파일 조회
		FileDTO file = fService.selectFile(no);

		System.out.println("title : "+article.getTitle());
		System.out.println("content : "+article.getContent());
		
		req.setAttribute("article", article);
		req.setAttribute("comments", comments);
		req.setAttribute("file", file);
		req.setAttribute("group", group);
		req.setAttribute("cate", cate);
		
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/board/view.jsp");
		dispatcher.forward(req, resp);
	}
}
