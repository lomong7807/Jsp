package kr.co.jboard2.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.co.jboard2.dao.ArticleDAO;
import kr.co.jboard2.dto.ArticleDTO;

public enum ArticleService {
	
	// enum으로 싱글톤 설정하면 INSTANCE를 이렇게 가져올 수 있다.
	// enum을 하면 다른 클래스 상속을 못받음
	INSTANCE;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private ArticleDAO dao = new ArticleDAO();
	
	private ArticleService() {};
	
	public int insertArticle(ArticleDTO dto) {
		return dao.insertArticle(dto);
	}
	public ArticleDTO selectArticle(String no) {
		return dao.selectArticle(no);
	}
	public List<ArticleDTO> selectArticles(int start) {
		return dao.selectArticles(start);
	}
	public int selectCountTotal() {
		return dao.selectCountTotal();
	}
	public void updateArticle(ArticleDTO dto) {
		dao.updateArticle(dto);
	}
	public void deleteArticle(String no) {
		dao.deleteArticle(no);
	}
	
	/************************************* Comment *************************************/
	public void insertComment(ArticleDTO dto) {
		dao.insertComment(dto);
	}
	public ArticleDTO selectComment(String no) {
		return dao.selectComment(no);
	}
	public List<ArticleDTO> selectComments(String parent) {
		return dao.selectComments(parent);
	}
	public void updateComment(ArticleDTO dto) {}
	public void deleteComment(String no) {}
	
	
	/************************************* File *************************************/
	// 업로드 경로 구하기
	public String getFilePath(HttpServletRequest req) {
		// 파일이 저장되는 경로
		ServletContext ctx = req.getServletContext();
		String path = ctx.getRealPath("/upload");
		
		return path;
	}
	
	// 파일명 수정
	public String renameToFile(HttpServletRequest req, String oName) {
		
		String path = getFilePath(req);
		
		// 파일의 확장자만 따로 빼둔다.
		int i = oName.lastIndexOf(".");
		String ext = oName.substring(i);
		// 랜덤의 중복되지 않는 이름 생성하여 저장될 파일 이름 설정
		String uuid = UUID.randomUUID().toString();
		String sName = uuid + ext;
		
		// 저장되어있는 파일의 객체 생성
		File f1 = new File(path+"/"+oName);
		// f2는 현재 없기 때문에 가상의 파일 객체
		File f2 = new File(path+"/"+sName);
		
		// f1의 파일명을 f2로 수정
		f1.renameTo(f2);
		
		return sName;
	}
	
	// 파일 업로드
	public MultipartRequest uploadFile(HttpServletRequest req) {
		// 파일 경로 구하기
		String path = getFilePath(req);
		
		// 최대 업로드 파일 크기 (다음은 10메가임)
		int maxSize = 1024 * 1024 * 10;
		
		// req, 파일저장 경로, 파일의 사이즈, 인코딩 값, 파일이름 수정 객체
		// 아래의 라이브러리 사용으로 파일 업로드가 된다.
		MultipartRequest mr = null;
		try {
			mr = new MultipartRequest(req,
									  path,
									  maxSize,
									  "UTF-8",
									  new DefaultFileRenamePolicy());
		} catch (IOException e) {
			logger.error("uploadFile() error : "+e.getMessage());
		}
		return mr;
	}
	// 파일 다운로드
	public void downloadFile() {
			
	}
	/************************************* Page *************************************/
	// 페이지 마지막 번호
	public int getLastPageNum(int total) {
		int lastPageNum = 0;
		
		if(total % 10 == 0) {
			lastPageNum = total / 10;
		}else {
			lastPageNum = total / 10 + 1;
		}
		return lastPageNum;
	}
	// 페이지 그룹
	public int[] getPageGroupNum(int currentPage, int lastPageNum) {
		int currentPageGroup = (int)Math.ceil(currentPage / 10.0);
		int pageGroupStart = (currentPageGroup - 1) * 10 + 1;
		int pageGroupEnd = currentPageGroup * 10;
		
		if(pageGroupEnd > lastPageNum){
			pageGroupEnd = lastPageNum;
		}
		
		int[] result = {pageGroupStart, pageGroupEnd};
		
		return result;
	}
	
	// 페이지 시작번호
	public int getPageStartNum(int total, int currentPage) {
		int start = (currentPage - 1) * 10;
		return total - start;
	}
	
	// 현재 페이지 번호
	public int getCurrentPage(String pg) {
		// 현재 페이지의 기본 값은 1이다.
		int currentPage = 1;
		// 하지만 pg가 null이 아니라 어떠한 숫자가 들어있다면 해당 숫자가 현재 페이지다.
		if(pg != null) {
			currentPage = Integer.parseInt(pg);
		}
		return currentPage;
	}
	
	// Limit 시작번호
	public int getStartNum(int currentPage) {
		return (currentPage - 1) * 10;
	}
}
