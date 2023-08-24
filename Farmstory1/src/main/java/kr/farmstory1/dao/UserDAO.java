package kr.farmstory1.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.farmstory1.db.DBHelper;
import kr.farmstory1.db.SQL;
import kr.farmstory1.dto.TermsDTO;
import kr.farmstory1.dto.UserDTO;

public class UserDAO extends DBHelper{
	
	private static UserDAO instance = new UserDAO();
	public static UserDAO getInstance() {
		return instance;
	}
	
	// User
	public void insertUser(UserDTO vo) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.INSERT_USER);
			
			psmt.setString(1, vo.getUid());
			psmt.setString(2, vo.getPass());
			psmt.setString(3, vo.getName());
			psmt.setString(4, vo.getNick());
			psmt.setString(5, vo.getEmail());
			psmt.setString(6, vo.getHp());
			psmt.setString(7, vo.getZip());
			psmt.setString(8, vo.getAddr1());
			psmt.setString(9, vo.getAddr2());
			psmt.setString(10, vo.getRegip());
			psmt.executeUpdate();
			close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public UserDTO selectUser(String uid, String pass) {
		UserDTO user = null;
		// 사용자 DB 조회
		try{
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_USER);
			psmt.setString(1, uid);
			psmt.setString(2, pass);
			
			rs = psmt.executeQuery();
			// (row)1 or 0 이라서 절대 2개 이상이 나올수가 없기때문에 if를 사용함
			if(rs.next()){
				user = new UserDTO();
				user.setUid(rs.getString(1));
				user.setPass(rs.getString(2));
				user.setName(rs.getString(3));
				user.setNick(rs.getString(4));
				user.setEmail(rs.getString(5));
				user.setHp(rs.getString(6));
				user.setRole(rs.getString(7));
				user.setZip(rs.getString(8));
				user.setAddr1(rs.getString(9));
				user.setAddr2(rs.getString(10));
				user.setRegip(rs.getString(11));
				user.setRegDate(rs.getString(12));
				user.setLeaveDate(rs.getString(13));
			}
			close();
		// 로그인시 아이디나 비밀번호가 잘못된걸 자세하게 알려주려면 여기 catch절에서 추가해야됨.
		}catch(Exception e){
			e.printStackTrace();
		}
		return user;
	}
	
	public List<UserDTO> selectUsers(int start){
		
		List<UserDTO> users = new ArrayList<>();
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_USERS);
			psmt.setInt(1, start);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				UserDTO dto = new UserDTO();
				dto.setUid(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setNick(rs.getString(3));
				dto.setEmail(rs.getString(4));
				dto.setHp(rs.getString(5));
				dto.setRole(rs.getString(6));
				dto.setZip(rs.getString(7));
				dto.setAddr1(rs.getString(8));
				dto.setAddr2(rs.getString(9));
				dto.setRegip(rs.getString(10));
				dto.setRegDate(rs.getString(11));
				users.add(dto);
			}
			
			close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}
	
	public int selectCountUsersTotal() {
		int result = 0;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_COUNT_USER);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int selectCountUid(String uid) {
		int result = 0;
		try{
			
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_COUNT_UID);
			psmt.setString(1, uid);
			rs = psmt.executeQuery();

			if(rs.next()){
				result = rs.getInt(1);
			}
					
			close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	public int selectCountNick(String nick) {
		int result = 0;
		
		try{
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_COUNT_NICK);
			psmt.setString(1, nick);
			
			ResultSet rs = psmt.executeQuery();
			
			if(rs.next()){
				result = rs.getInt(1);
			}
			
			close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	public int selectCountHp(String hp) {
		int result = 0;
		
		try{
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_COUNT_HP);
			psmt.setString(1, hp);
			
			ResultSet rs = psmt.executeQuery();
			
			if(rs.next()){
				result = rs.getInt(1);
			}
			
			close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	public int selectCountEmail(String email) {
		int result = 0;
		
		try{
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_COUNT_EMAIL);
			psmt.setString(1, email);
			
			ResultSet rs = psmt.executeQuery();
			
			if(rs.next()){
				result = rs.getInt(1);
			}
			
			close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}
	// terms
	public TermsDTO selectTerms() {
		TermsDTO vo = new TermsDTO();
		
		try {
			conn = getConnection();
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(SQL.SELECT_TERMS);
			
			if(rs.next()) {
				vo.setTerms(rs.getString(1));
				vo.setPrivacy(rs.getString(2));
			}
			
			close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}
}
