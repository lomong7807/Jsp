package controller.user1;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.User1DTO;
import service.User1Service;

@WebServlet("/user1/list.do")
public class ListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private User1Service service = new User1Service();
	
	@Override
	public void init(ServletConfig config) throws ServletException {
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<User1DTO> users = service.selectUser1s();
		
		// 컨트롤러에 있는 리스트를 view에서 "참조"한다.
		// View에서 users 참조하기 위해 request Scope 저장
		request.setAttribute("users", users);
		
		RequestDispatcher dispatcher =request.getRequestDispatcher("/user1/list.jsp");
		dispatcher.forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
