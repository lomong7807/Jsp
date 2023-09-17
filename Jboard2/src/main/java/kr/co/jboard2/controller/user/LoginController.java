package kr.co.jboard2.controller.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import kr.co.jboard2.dto.UserDTO;
import kr.co.jboard2.service.UserService;
@WebServlet("/user/login.do")
public class LoginController extends HttpServlet{
	private static final long serialVersionUID = 2957314664120531756L;

	private UserService service = UserService.getInstance();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 아래의 dopost에서 로그인 실패시 가져온 success를 다시 login.jsp로 보내서 alert를 띄워준다.
		String success = req.getParameter("success");
		req.setAttribute("success", success);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/user/login.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String uid = req.getParameter("uid");
		String pass = req.getParameter("pass");
		
		UserDTO user = service.selectUser(uid, pass);
		
		if(user != null) {
			// 현재 세션 구하기
			HttpSession session = req.getSession();
			// sessUser라는 세션에 사용자 user로 세션을 설정
			session.setAttribute("sessUser", user);
			// 리다이렉트
			resp.sendRedirect("/Jboard2/list.do");
		}else {
			resp.sendRedirect("/Jboard2/user/login.do?success=100");
		}
		
	}
}
