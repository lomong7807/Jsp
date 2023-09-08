package kr.co.farmstory2.cotroller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.farmstory2.dto.ArticleDTO;
import kr.co.farmstory2.service.ArticleService;

// 시작페이지 index.jsp가 없기 때문에 기본주소 ("")에 대한 맵핑 추가
@WebServlet(value= {"", "/index.do"})
public class IndexController extends HttpServlet{

	private static final long serialVersionUID = -7180183564375552823L;
	private ArticleService service = ArticleService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<ArticleDTO> latests1 = service.selectLatests("grow", 5);
		List<ArticleDTO> latests2 = service.selectLatests("school", 5);
		List<ArticleDTO> latests3 = service.selectLatests("story", 5);
		
		List<ArticleDTO> tabLatests1 = service.selectLatests("notice", 3);
		List<ArticleDTO> tabLatests2 = service.selectLatests("qna", 3);
		List<ArticleDTO> tabLatests3 = service.selectLatests("faq", 3);

		req.setAttribute("latests1", latests1);
		req.setAttribute("latests2", latests2);
		req.setAttribute("latests3", latests3);

		req.setAttribute("tabLatests1", tabLatests1);
		req.setAttribute("tabLatests2", tabLatests2);
		req.setAttribute("tabLatests3", tabLatests3);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
		dispatcher.forward(req, resp);
		
	}
	
}
