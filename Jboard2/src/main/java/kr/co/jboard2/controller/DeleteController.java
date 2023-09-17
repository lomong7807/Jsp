package kr.co.jboard2.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.jboard2.dto.FileDTO;
import kr.co.jboard2.service.ArticleService;
import kr.co.jboard2.service.FileService;
@WebServlet("/delete.do")
public class DeleteController extends HttpServlet{
	private static final long serialVersionUID = 8056371827457019176L;

	private ArticleService aService = ArticleService.INSTANCE;
	private FileService fService = FileService.INSTANCE;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 글 번호 수신
		String no = req.getParameter("no");
		logger.debug("no : "+no);
		
		// 파일 삭제(DB)
		List<FileDTO> files = fService.deleteFile(no);
		
		// 글+댓글 삭제
		aService.deleteArticle(no);
		
		// 파일 삭제(Directory)
		if(files != null) {
			
			for(FileDTO fileName : files) {
				
				String path = aService.getFilePath(req);
				
				File file = new File(path+"/"+fileName.getSfile());
				
				if(file.exists()) {
					file.delete();
				}	
			}
		}
		
		resp.sendRedirect("/Jboard2/list.do");
	}
}
