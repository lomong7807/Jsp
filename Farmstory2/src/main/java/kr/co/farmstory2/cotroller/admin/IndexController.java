package kr.co.farmstory2.cotroller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.farmstory2.dto.OrderDTO;
import kr.co.farmstory2.dto.ProductDTO;
import kr.co.farmstory2.dto.UserDTO;
import kr.co.farmstory2.service.OrderService;
import kr.co.farmstory2.service.ProductService;
import kr.co.farmstory2.service.UserService;

@WebServlet(value= {"/admin", "/admin/index.do"})
public class IndexController extends HttpServlet{
	private static final long serialVersionUID = -6976791719294056251L;
	
	private ProductService pService = ProductService.INSTANCE;
	private OrderService oService = OrderService.INSTANCE;
	private UserService uService = UserService.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<ProductDTO> products = pService.selectLatestProducts(3);
		List<OrderDTO> orders = oService.selectLatestOrders(3);
		List<UserDTO> users = uService.selectLatestUsers(3);

		req.setAttribute("products", products);
		req.setAttribute("orders", orders);
		req.setAttribute("users", users);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/index.jsp");
		dispatcher.forward(req, resp);
	}
}
