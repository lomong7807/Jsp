package controller.user6;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.User6DTO;
import service.User6Service;

@WebServlet("/user6/list.do")
public class ListController extends HttpServlet{
	private static final long serialVersionUID = 7268620127242264841L;
	private User6Service service = new User6Service();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<User6DTO> users = service.selectUser6s();
		req.setAttribute("users", users);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/user6/list.jsp");
		dispatcher.forward(req, resp);
	}
}
