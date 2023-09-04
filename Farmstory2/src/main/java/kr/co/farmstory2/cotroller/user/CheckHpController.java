package kr.co.farmstory2.cotroller.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;

import kr.co.farmstory2.service.UserService;


@WebServlet("/user/checkHp.do")
public class CheckHpController extends HttpServlet{

	private static final long serialVersionUID = 5979834810465594198L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private UserService service = UserService.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String  hp = req.getParameter("hp");
		
		System.out.println("checkHp : "+hp);
		
		int result = service.selectCountHp(hp);
		
		// JSON 생성
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		// JSON 출력
		PrintWriter writer = resp.getWriter();
		writer.print(json.toString());
		
	}
}
