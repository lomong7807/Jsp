package kr.co.farmstory2.cotroller.market;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.farmstory2.dto.ProductDTO;
import kr.co.farmstory2.service.OrderService;

@WebServlet("/market/order.do")
public class OrderController extends HttpServlet{
	private static final long serialVersionUID = -8997035904559998619L;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private OrderService service = OrderService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ProductDTO dto = new ProductDTO();
		
		String thumb2 	= req.getParameter("thumb2");
		String pName 	= req.getParameter("pName");
		String pNo 		= req.getParameter("pNo");
		int delivery = Integer.parseInt(req.getParameter("delivery"));
		String price 	= req.getParameter("price");
		String count 	= req.getParameter("count");
		int total 	= Integer.parseInt(req.getParameter("total"));
		
		System.out.println("order thumb2 : "+thumb2);
		System.out.println("order pName : "+pName);
		System.out.println("order delivery : "+delivery);
		System.out.println("order price : "+price);
		System.out.println("order count : "+count);
		System.out.println("order total : "+total);
		
		dto.setThumb2(thumb2);
		dto.setpName(pName);
		dto.setpNo(pNo);
		dto.setDelivery(delivery);
		dto.setPrice(price);
		dto.setCount(count);
		dto.setTotal(total);
		dto.setTotalPrice(total, delivery);
		
		req.setAttribute("dto", dto);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/market/order.jsp");
		dispatcher.forward(req, resp);
	}
}
