package kr.co.farmstory2.cotroller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.farmstory2.dto.OrderDTO;
import kr.co.farmstory2.service.ArticleService;
import kr.co.farmstory2.service.OrderService;
import kr.co.farmstory2.service.ProductService;
@WebServlet("/admin/orderList.do")
public class OrderListController extends HttpServlet{
	private static final long serialVersionUID = -9218226880099579925L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private OrderService oService = OrderService.INSTANCE;
	private ProductService pService = ProductService.INSTANCE;
	private ArticleService aService = ArticleService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String pg = req.getParameter("pg");
		
		// 현재 페이지 번호
		int currentPage = aService.getCurrentPage(pg);
		
		// 전체 게시물 갯수
		int total = oService.selectCountOrdersTotal();
		
		// 마지막 페이지 번호
		int lastPageNum = aService.getLastPageNum(total);
		
		// 페이지 그룹 start, end 번호
		int[] result = aService.getPageGroupNum(currentPage, lastPageNum);
		
		// 페이지 시작번호
		int pageStartNum = aService.getPageStartNum(total, currentPage);
		
		// 시작 인덱스
		int start = aService.getStartNum(currentPage);
		
		List<OrderDTO> orders = oService.selectOrders(start);
		
		req.setAttribute("orders", orders);
		
		req.setAttribute("currentPage", currentPage);
		req.setAttribute("lastPageNum", lastPageNum);
		req.setAttribute("pageGroupStart", result[0]);
		req.setAttribute("pageGroupEnd", result[1]);
		req.setAttribute("pageStartNum", pageStartNum+1);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/orderList.jsp");
		dispatcher.forward(req, resp);
	}
}
