package controller.user5;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.User5Service;

@WebServlet("/user5/delete.do")
public class DeleteController extends HttpServlet {

	private User5Service service = new User5Service();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String name = req.getParameter("name");
		service.deleteUser5(name);
		
		resp.sendRedirect("/Ch10/user5/list.do");
	}
}
