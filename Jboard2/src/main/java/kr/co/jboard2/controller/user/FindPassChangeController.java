package kr.co.jboard2.controller.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.jboard2.service.UserService;

@WebServlet("/user/findPassChange.do")
public class FindPassChangeController extends HttpServlet{
	private static final long serialVersionUID = 3184963205046179155L;
	private UserService service = UserService.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		String uid = (String)session.getAttribute("uid");
		
		// uid 값이 null이면 인증 받고 오게끔 다시 findPass로 리다이렉트
		if(uid == null) {
			resp.sendRedirect("/Jboard2/user/findPass.do");
		}else {
			RequestDispatcher dispatcher = req.getRequestDispatcher("/user/findPassChange.jsp");
			dispatcher.forward(req, resp);
		}
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 이미 유효성 검사로 확인됐기 때문에 여기서 pass1, 2가 같은지 확인 안해도 됨.
		String uid = req.getParameter("uid");
		String pass1 = req.getParameter("pass1");
		
		service.updateUserPass(uid, pass1);
		
		resp.sendRedirect("/Jboard2/user/login.do?success=300");
		
	}

}
