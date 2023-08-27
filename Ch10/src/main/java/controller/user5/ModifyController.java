package controller.user5;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.User5DTO;
import service.User5Service;
@WebServlet("/user5/modify.do")
public class ModifyController extends HttpServlet{

	private static final long serialVersionUID = -747611099748777739L;
	private User5Service service = new User5Service();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		
		User5DTO user = service.selectUser5(name);
		
		req.setAttribute("user", user);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/user5/modify.jsp");
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
		
		User5DTO dto = new User5DTO();
		dto.setName(name);
		dto.setGender(gender);
		dto.setAge(age);
		dto.setAddr(addr);
		
		service.updateUser5(dto);
		
		resp.sendRedirect("/Ch10/user5/list.do");
	}
}
