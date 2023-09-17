package kr.co.farmstory2.cotroller.introduction;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/introduction/hello.do")
public class HelloController extends HttpServlet{
	private static final long serialVersionUID = 343564521853083705L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/introduction/hello.jsp");
		dispatcher.forward(req, resp);
	}
}
