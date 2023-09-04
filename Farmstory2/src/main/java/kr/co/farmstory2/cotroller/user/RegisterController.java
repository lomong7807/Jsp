package kr.co.farmstory2.cotroller.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.farmstory2.dto.UserDTO;
import kr.co.farmstory2.service.UserService;

@WebServlet("/user/register.do")
public class RegisterController extends HttpServlet{
	private static final long serialVersionUID = -4041203295576668075L;

	private UserService service = UserService.getInstance();
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/user/register.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		UserDTO dto = new UserDTO();
		
		String uid 		= req.getParameter("uid");
		String pass 	= req.getParameter("pass1");
		String name 	= req.getParameter("name");
		String nick 	= req.getParameter("nick");
		String email 	= req.getParameter("email");
		String hp 		= req.getParameter("hp");
		String zip 		= req.getParameter("zip");
		String addr1 	= req.getParameter("addr1");
		String addr2 	= req.getParameter("addr2");
		String regip = req.getRemoteAddr();
		
		logger.debug("uid : "+uid);
		logger.debug("pass : "+pass);
		logger.debug("name : "+name);
		logger.debug("nick : "+nick);
		logger.debug("email : "+email);
		logger.debug("hp : "+hp);
		logger.debug("zip : "+zip);
		logger.debug("addr1 : "+addr1);
		logger.debug("addr2 : "+addr2);
		logger.debug("regip : "+regip);
		
		dto.setUid(uid);
		dto.setPass(pass);
		dto.setName(name);
		dto.setNick(nick);
		dto.setEmail(email);
		dto.setHp(hp);
		dto.setZip(zip);
		dto.setAddr1(addr1);
		dto.setAddr2(addr2);
		dto.setRegip(regip);

		service.insertUser(dto);
		
		resp.sendRedirect("/Farmstory2/user/login.do?success=101");
		
	}
}
