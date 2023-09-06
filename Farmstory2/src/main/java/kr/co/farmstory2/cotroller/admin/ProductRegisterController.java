package kr.co.farmstory2.cotroller.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.co.farmstory2.dto.ProductDTO;
import kr.co.farmstory2.service.ArticleService;
import kr.co.farmstory2.service.ProductService;

@WebServlet("/admin/productRegister.do")
public class ProductRegisterController extends HttpServlet{
	private static final long serialVersionUID = 422330232588488266L;

	private ProductService service = ProductService.INSTANCE;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/productRegister.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		MultipartRequest mr = service.uploadFile(req);
		
		String productName 	= mr.getParameter("productName");
		String type		   	= mr.getParameter("type");
		String price 		= mr.getParameter("price");
		String delivery 	= mr.getParameter("delivery");
		String stock 		= mr.getParameter("stock");
		String thumb1 		= mr.getOriginalFileName("thumb1");
		String thumb2 		= mr.getOriginalFileName("thumb2");
		String thumb3 		= mr.getOriginalFileName("thumb3");
		String seller 		= mr.getParameter("seller");
		String etc 			= mr.getParameter("etc");
		// 파일을 저장할 경로
		String path         = service.getFilePath(req);
		
		System.out.println("productName : "+productName);
		System.out.println("type : "+type);
		System.out.println("price : "+price);
		System.out.println("delivery : "+delivery);
		System.out.println("stock : "+stock);
		System.out.println("thumb1 : "+thumb1);
		System.out.println("thumb2 : "+thumb2);
		System.out.println("thumb3 : "+thumb3);
		System.out.println("etc : "+etc);
		System.out.println("seller : "+seller);
		
		// path로 아래를 저장
		ProductDTO dto = new ProductDTO(path);
		dto.setpName(productName);
		dto.setType(type);
		dto.setPrice(price);
		dto.setDelivery(delivery);
		dto.setStock(stock);
		// forname에 path가 쓰임
		dto.setThumb1ForRename(thumb1);
		dto.setThumb2ForRename(thumb2);
		dto.setThumb3ForRename(thumb3);
		dto.setEtc(etc);
		dto.setSeller(seller);
		
		System.out.println(dto.getThumb1());
		System.out.println(dto.getThumb2());
		System.out.println(dto.getThumb3());
		
		service.insertProduct(dto);
		
		resp.sendRedirect("/Farmstory2/admin/productList.do?pg="+1);
	}
}
