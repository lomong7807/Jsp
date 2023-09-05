package kr.co.farmstory2.cotroller.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oreilly.servlet.MultipartRequest;

import kr.co.farmstory2.dto.ArticleDTO;
import kr.co.farmstory2.dto.FileDTO;
import kr.co.farmstory2.service.ArticleService;
import kr.co.farmstory2.service.FileService;

@WebServlet("/board/write.do")
public class WriteController extends HttpServlet{
	private static final long serialVersionUID = -7713153697815104384L;

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private ArticleService aService = ArticleService.INSTANCE;
	private FileService fService = FileService.INSTANCE;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String group = req.getParameter("group");
		String cate = req.getParameter("cate");
		
		req.setAttribute("group", group);
		req.setAttribute("cate", cate);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/board/write.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//파일 업로드
		MultipartRequest mr = aService.uploadFile(req);
		
		// 폼 데이터 수신
		String group = mr.getParameter("group");
		String cate = mr.getParameter("cate");
		String title = mr.getParameter("title");
		String content = mr.getParameter("content");
		String writer = mr.getParameter("writer");
		String oName = mr.getOriginalFileName("file");
		String regip = req.getRemoteAddr();
		
		logger.debug("title : " + title);
		logger.debug("content : " + content);
		logger.debug("writer : " + writer);
		logger.debug("oName : " + oName);
		logger.debug("regip : " + regip);
		
		System.out.println("write group : "+group);
		System.out.println("write cate : "+cate);
		System.out.println("write title : "+title);
		System.out.println("write content : "+content);
		System.out.println("write writer : "+writer);
		System.out.println("write file : "+oName);
		
		// DTO 생성
		ArticleDTO dto = new ArticleDTO();
		dto.setCate(cate);
		dto.setTitle(title);
		dto.setContent(content);
		dto.setFile(oName);
		dto.setWriter(writer);
		dto.setRegip(regip);
		
		// 글 Insert
		int ano = aService.insertArticle(dto);
		
		System.out.println("write ano :"+ano);
		
		// 파일명 수정 및 파일 Insert
		if(oName != null) {
			String sName = aService.renameToFile(req, oName);
			
			System.out.println("sName : "+sName);
			
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
		resp.sendRedirect("/Farmstory2/board/list.do?group="+group+"&cate="+cate);
	}
}
