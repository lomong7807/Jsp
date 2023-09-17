package kr.co.jboard2.controller.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.jboard2.dto.TermsDTO;
import kr.co.jboard2.service.TermsService;
@WebServlet("/user/terms.do")
public class TermsController extends HttpServlet{
	private static final long serialVersionUID = -5526161827095080595L;

	private TermsService service = new TermsService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		TermsDTO terms = service.selectTerms();
		/* service.selectTerms() 를 담은 terms를 /user/terms.jsp에서 호출할 수 있게끔
		 * terms라는 이름의 request scope로 저장한다.
		*/
		req.setAttribute("dto", terms);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/user/terms.jsp");
		dispatcher.forward(req, resp);
	}
}
