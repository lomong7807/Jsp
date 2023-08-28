package controller.user6;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.User6Service;

@WebServlet("/user6/delete.do")
public class DeleteController extends HttpServlet {

	private User6Service service = new User6Service();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String name = req.getParameter("name");
		service.deleteUser6(name);
		
		resp.sendRedirect("/Ch10/user6/list.do");
	}
}
