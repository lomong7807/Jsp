package kr.co.jboard1.dao;

import java.sql.ResultSet;

import kr.co.jboard1.DTO.TermsDTO;
import kr.co.jboard1.DTO.UserDTO;
import kr.co.jboard1.db.DBHelper;
import kr.co.jboard1.db.SQL;

public class UserDAO extends DBHelper {
	
	private static UserDAO instance = new UserDAO();
	public static UserDAO getInstance() {
		return instance;
	}
	
	public void insertUser(UserDTO vo) {
		
		try{
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
			psmt.setString(10,vo.getRegip());
			psmt.executeUpdate();
			close();
			
		}catch(Exception e){
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
	public TermsDTO selectTerms() {
		TermsDTO vo = new TermsDTO();
		
		try{
			conn = getConnection();
			
			// 커넥션으로부터 Statement 객체를 생성하여 SQL문을 실행한다.
			stmt = conn.createStatement();
			// SQL문을 실행하여 데이터베이스의 Terms 테이블의 데이터를 가져온다.
			rs = stmt.executeQuery(SQL.SELECT_TERMS);
			
			if(rs.next()){
				vo.setTerms(rs.getString(1));
				vo.setPrivacy(rs.getString(2));
			}
			
			close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return vo;
	}
	
	
	
	public void selectUsers() {}
	public void updateUser() {}
	public void deleteUser() {}

}
