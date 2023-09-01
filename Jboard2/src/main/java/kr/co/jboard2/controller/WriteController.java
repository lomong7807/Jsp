package kr.co.jboard2.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oreilly.servlet.MultipartRequest;

import kr.co.jboard2.dto.ArticleDTO;
import kr.co.jboard2.dto.FileDTO;
import kr.co.jboard2.dto.UserDTO;
import kr.co.jboard2.service.ArticleService;
import kr.co.jboard2.service.FileService;
@WebServlet("/write.do")
public class WriteController extends HttpServlet{
	private static final long serialVersionUID = 6212214188434444431L;

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private ArticleService aService = ArticleService.INSTANCE;
	private FileService fService 	= FileService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 현재 세션 가져오기
		HttpSession session = req.getSession();
		UserDTO sessUser = (UserDTO)session.getAttribute("sessUser");
		
		if(sessUser != null) {
			RequestDispatcher dispatcher = req.getRequestDispatcher("/write.jsp");
			dispatcher.forward(req, resp);
		}else {
			resp.sendRedirect("/Jboard2/user/login.do?success=101");
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 아래의 라이브러리 사용으로 파일 업로드가 된다.
		MultipartRequest mr = aService.uploadFile(req);
		
		// 폼 데이터 수신
		String title   = mr.getParameter("title");
		String content = mr.getParameter("content");
		String writer  = mr.getParameter("writer");
		// 첨부된 파일의 originalname
		String oName   = mr.getOriginalFileName("file");
		String regip   = req.getRemoteAddr();
		
		logger.debug("title : "+title);
		logger.debug("content : "+content);
		logger.debug("writer : "+writer);
		logger.debug("oName :"+oName);
		logger.debug("regip : "+regip);
		
		
		// DTO 생성
		ArticleDTO dto = new ArticleDTO();
		dto.setTitle(title);
		dto.setContent(content);
		dto.setFile(oName);
		dto.setWriter(writer);
		dto.setRegip(regip);
		
		// 글 Insert 후 글 번호 가져오기
		int ano = aService.insertArticle(dto);
		
		// 파일명 수정
		// 파일이 있다면
		if(oName != null) {
			String sName = aService.renameToFile(req,oName);
			
			// 파일 테이블 Insert
			// 원래의 파일 이름을 저장해둬야 한다.
			FileDTO fileDto = new FileDTO();
			
			fileDto.setAno(ano);
			fileDto.setOfile(oName);
			fileDto.setSfile(sName);
			// 파일 Insert
			fService.insertFile(fileDto);
		}
		// 리다이렉트
		resp.sendRedirect("/Jboard2/list.do");
	}

}
