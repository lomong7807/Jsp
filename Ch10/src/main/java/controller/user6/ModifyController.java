package controller.user6;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.User6DTO;
import service.User6Service;
@WebServlet("/user6/modify.do")
public class ModifyController extends HttpServlet{

	private static final long serialVersionUID = -747611099748777739L;
	private User6Service service = new User6Service();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		
		User6DTO user = service.selectUser6(name);
		
		req.setAttribute("user", user);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/user6/modify.jsp");
		dispatcher.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String gender = req.getParameter("gender");
		if(gender.equals("남자")) {
			gender = "1";
		}else if(gender.equals("여자")) {
			gender = "2";
		}
		String age = req.getParameter("age");
		String addr = req.getParameter("addr");
		
		User6DTO dto = new User6DTO();
		dto.setName(name);
		dto.setGender(gender);
		dto.setAge(age);
		dto.setAddr(addr);
		
		service.updateUser6(dto);
		
		resp.sendRedirect("/Ch10/user6/list.do");
	}
}
