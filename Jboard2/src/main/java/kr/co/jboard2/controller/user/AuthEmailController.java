package kr.co.jboard2.controller.user;

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

import kr.co.jboard2.service.UserService;
@WebServlet("/user/authEmail.do")
public class AuthEmailController extends HttpServlet{
	private static final long serialVersionUID = 9094836002104883300L;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private UserService service = UserService.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String type  = req.getParameter("type");
		String name  = req.getParameter("name");
		String email = req.getParameter("email");
		String uid = req.getParameter("uid");
		
		System.out.println("타입 :"+type);
		System.out.println("이름 :"+name);
		System.out.println("이메일 :"+email);
		System.out.println("아이디 :"+uid);
		
		// result = 중복된 메일이 있는지 확인
		int result = 0;
		// status = 메일 발송이 성공적으로 됐는지 확인
		int status = 0;
		
		if(type.equals("REGISTER")) {
			// 회원가입 할 때 이메일 인증
			result = service.selectCountEmail(email);
			
			if(result == 0) {
				status = service.sendCodeByEmail(email);
			}
		}else if(type.equals("FIND_ID")){
			// 아이디 찾을 때 이메일 인증
			result = service.selectCountNameAndEmail(name, email);
			
			if(result == 1) {
				status = service.sendCodeByEmail(email);
			}
		}else if(type.equals("FIND_PASS")){
			// 비밀번호 찾을 때 이메일 인증
			result = service.selectCountUidAndEmail(uid, email);
			
			if(result == 1) {
				status = service.sendCodeByEmail(email);
			}
		}else if(type.equals("MODIFY")){
			// 이메일을 수정할 때 이메일 인증
			result = service.selectCountEmail(email);
			
			if(result == 0) {
				status = service.sendCodeByEmail(email);
			}
		}
		
		// JSON 생성
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		json.addProperty("status", status);
		
		// JSON 출력
		PrintWriter writer = resp.getWriter();
		writer.print(json.toString());
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String code = req.getParameter("code");
		logger.info("service code : "+ code);
		
		int result = service.confirmCodeByEmail(code);
		logger.info("service result : " + result);
		// JSON 생성
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		// JSON 출력
		PrintWriter writer = resp.getWriter();
		writer.print(json.toString());
	}
}
