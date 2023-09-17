package kr.co.farmstory2.cotroller.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.farmstory2.dto.UserDTO;
import kr.co.farmstory2.service.UserService;

@WebServlet("/user/login.do")
public class LoginController extends HttpServlet{

	private static final long serialVersionUID = 2876393682649697373L;

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private UserService service = UserService.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String success = req.getParameter("success");
		String target = req.getParameter("target");
		String group = req.getParameter("group");
		String cate = req.getParameter("cate");
		String no = req.getParameter("no");
		String pNo = req.getParameter("pNo");
		
		req.setAttribute("success", success);		
		req.setAttribute("target", target);
		req.setAttribute("group", group);
		req.setAttribute("cate", cate);
		req.setAttribute("no", no);
		req.setAttribute("pNo", pNo);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/user/login.jsp");
		dispatcher.forward(req, resp);
	}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String uid = req.getParameter("uid");
		String pass = req.getParameter("pass");
		String target = req.getParameter("target");
		String group = req.getParameter("group");
		String cate = req.getParameter("cate");
		String no = req.getParameter("no");
		String pNo = req.getParameter("pNo");
		
		UserDTO user = service.selectUser(uid, pass);
		
		if(user != null){
			
			HttpSession session = req.getSession();
			session.setAttribute("sessUser", user);

			logger.info("user uid : "+ user.getUid());
			
			if(target.equals("view")) {
				resp.sendRedirect("/Farmstory2/board/view.do?group="+group+"&cate="+cate+"&no="+no);
			}else if(target.equals("write")) {
				resp.sendRedirect("/Farmstory2/board/write.do?group="+group+"&cate="+cate);
			}else if(target.equals("market")) {
				resp.sendRedirect("/Farmstory2/market/view.do?pNo="+pNo);
			}else {
				resp.sendRedirect("/Farmstory2");
			}
			
			
		}else{
			resp.sendRedirect("/Farmstory2/user/login.do?success=100");
		}
		
	}
}
