package kr.co.farmstory2.service;

import kr.co.farmstory2.dao.TermsDAO;
import kr.co.farmstory2.dto.TermsDTO;

public class TermsService {
	
	private TermsDAO dao = new TermsDAO();
	private static TermsService instance = new TermsService();
	public static TermsService getInstance() {
		return instance;
	}
	
	public TermsDTO selectTerms() {
		return dao.selectTerms();
	}
	
}
