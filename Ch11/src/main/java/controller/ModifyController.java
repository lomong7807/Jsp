package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.MemberDTO;
import service.MemberService;
@WebServlet("/modify.do")
public class ModifyController extends HttpServlet{
	private static final long serialVersionUID = 1730412819184625859L;

	private MemberService service = MemberService.INSTANCE;
	
	@Override
	public void init() throws ServletException {
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String uid = req.getParameter("uid");
		
		MemberDTO member = service.selectMember(uid);
		
		req.setAttribute("member", member);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/modify.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String uid 	= req.getParameter("uid");
		String name = req.getParameter("name");
		String hp 	= req.getParameter("hp");
		String pos 	= req.getParameter("pos");
		String dep 	= req.getParameter("dep");
		
		MemberDTO dto = new MemberDTO();
		dto.setUid(uid);
		dto.setName(name);
		dto.setHp(hp);
		dto.setPos(pos);
		dto.setDep(dep);
		
		service.updateMember(dto);
		
		resp.sendRedirect("/Ch11/list.do");
		
	}
	
	
	
}
