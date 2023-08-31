package kr.co.jboard2.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.jboard2.dto.ArticleDTO;
import kr.co.jboard2.dto.FileDTO;
import kr.co.jboard2.dto.UserDTO;
import kr.co.jboard2.service.ArticleService;
import kr.co.jboard2.service.FileService;
@WebServlet("/view.do")
public class ViewController extends HttpServlet{
	private static final long serialVersionUID = -2120022050925682570L;
	
	private ArticleService aService = ArticleService.INSTANCE;
	private FileService fService = FileService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 현재 세션 가져오기
		HttpSession session = req.getSession();
		UserDTO sessUser = (UserDTO)session.getAttribute("sessUser");
		
		if(sessUser != null) {
			String no = req.getParameter("no");
			
			// 글 조회
			ArticleDTO article 		 = aService.selectArticle(no);
			List<ArticleDTO> comments= aService.selectComments(no);
			FileDTO file 	   		 = fService.selectFile(no);
			
			System.out.println(file.getFno());
			System.out.println(file.getAno());
			System.out.println(file.getOfile());
			System.out.println(file.getSfile());
			System.out.println(file.getDownload());
			System.out.println(file.getRdate());
			
			
			req.setAttribute("article", article);
			req.setAttribute("comments", comments);
			req.setAttribute("file", file);
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("/view.jsp");
			dispatcher.forward(req, resp);
		}else {
			resp.sendRedirect("/Jboard2/user/login.do?success=101");
		}
		
		
	}


}
