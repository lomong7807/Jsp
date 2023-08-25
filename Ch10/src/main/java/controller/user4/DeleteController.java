package controller.user4;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.User4Service;

@WebServlet("/user4/delete.do")
public class DeleteController extends HttpServlet{
	private static final long serialVersionUID = 7995815928796591237L;
	private User4Service service = new User4Service();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String name = req.getParameter("name");
		service.deleteUser4(name);
		
		resp.sendRedirect("/Ch10/user4/list.do");
	}
}
