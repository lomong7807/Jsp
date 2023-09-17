package kr.co.farmstory2.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.co.farmstory2.dao.ProductDAO;
import kr.co.farmstory2.dto.FileDTO;
import kr.co.farmstory2.dto.ProductDTO;

public enum ProductService {

	INSTANCE;
	private ProductDAO dao = new ProductDAO();
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void insertProduct(ProductDTO dto) {
		dao.insertProduct(dto);
	}
	public ProductDTO selectProduct(String pNo) {
		return dao.selectProduct(pNo);
	}
	
	public List<ProductDTO> selectLatestProducts(int size) {
		return dao.selectLatestProducts(size);
	}
	public List<ProductDTO> selectProducts(String type, int start) {
		return dao.selectProducts(type, start);
	}
	
	public void updateProduct(ProductDTO dto) {}
	public void deleteProduct(int pNo) {}

	public int selectCountProductsTotal(String type) {
		return dao.selectCountProductsTotal(type);
	}
	
	// 업로드 경로 구하기
		public String getFilePath(HttpServletRequest req) {
			// 파일 업로드 경로 구하기 
			ServletContext ctx = req.getServletContext();
			String path = ctx.getRealPath("/thumb");
			return path;
		}
		
		// 파일명 수정
		public String renameToFile(HttpServletRequest req, String oName) {
			
			String path = getFilePath(req);
			
			int i = oName.lastIndexOf(".");
			String ext = oName.substring(i);
			
			String uuid  = UUID.randomUUID().toString();
			String sName = uuid + ext;
			
			File f1 = new File(path+"/"+oName);
			File f2 = new File(path+"/"+sName);
			
			f1.renameTo(f2);
			
			return sName;
		}
		
		
		// 파일 업로드
		public MultipartRequest uploadFile(HttpServletRequest req) {
			// 파일 경로 구하기
			String path = getFilePath(req);
			
			// 최대 업로드 파일 크기
			int maxSize = 1024 * 1024 * 10;
			
			// 파일 업로드 및 Multipart 객체 생성
			MultipartRequest mr = null;
			
			try {
				mr = new MultipartRequest(req, 
										  path, 
										  maxSize, 
										  "UTF-8", 
										  new DefaultFileRenamePolicy());
			} catch (IOException e) {
				logger.error("uploadFile : " + e.getMessage());
			}
			
			return mr;
		}
		
		// 파일 다운로드
		public void downloadFile(HttpServletRequest req, HttpServletResponse resp, FileDTO dto) throws IOException {
			// response 파일 다운로드 헤더 수정
			resp.setContentType("application/octet-stream");
			resp.setHeader("Content-Disposition", "attachment; filename="+URLEncoder.encode(dto.getOfile(), "utf-8"));
			resp.setHeader("Content-Transfer-Encoding", "binary");
			resp.setHeader("Pragma", "no-cache");
			resp.setHeader("Cache-Control", "private");
			
			// response 파일 스트림 작업
			String path = getFilePath(req);
			
			File file = new File(path+"/"+dto.getSfile());
			
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
			BufferedOutputStream bos = new BufferedOutputStream(resp.getOutputStream());
					
			while(true){
				
				int data = bis.read();
				if(data == -1){
					break;
				}
				
				bos.write(data);
			}
			
			bos.close();
			bis.close();
		}
}
