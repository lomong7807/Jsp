package kr.co.farmstory2.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.farmstory2.db.DBHelper;
import kr.co.farmstory2.db.SQL;
import kr.co.farmstory2.dto.OrderDTO;
import kr.co.farmstory2.dto.UserDTO;

public class UserDAO extends DBHelper{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void insertUser(UserDTO dto) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.INSERT_USER);
			
			psmt.setString(1, dto.getUid());
			psmt.setString(2, dto.getPass());
			psmt.setString(3, dto.getName());
			psmt.setString(4, dto.getNick());
			psmt.setString(5, dto.getEmail());
			psmt.setString(6, dto.getHp());
			psmt.setString(7, dto.getZip());
			psmt.setString(8, dto.getAddr1());
			psmt.setString(9, dto.getAddr2());
			psmt.setString(10, dto.getRegip());
			
			psmt.executeUpdate();
			close();
		} catch (Exception e) {
			logger.error("insertUser() error : "+e.getMessage());
		}
	}
	public UserDTO selectUser(String uid, String pass) {
		UserDTO dto = null;
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_USER);
			psmt.setString(1, uid);
			psmt.setString(2, pass);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto = new UserDTO();
				dto.setUid(rs.getString(1));
				dto.setPass(rs.getString(2));
				dto.setName(rs.getString(3));
				dto.setNick(rs.getString(4));
				dto.setEmail(rs.getString(5));
				dto.setHp(rs.getString(6));
				dto.setRole(rs.getString(7));
				dto.setZip(rs.getString(8));
				dto.setAddr1(rs.getString(9));
				dto.setAddr2(rs.getString(10));
				dto.setRegip(rs.getString(11));
				dto.setRegDate(rs.getString(12));
				dto.setLeaveDate(rs.getString(13));
			}
			close();
		} catch (Exception e) {
			logger.error("selectUser() error : "+e.getMessage());
		}
		return dto;
	}
	// 추가 
	public List<UserDTO> selectLatestUsers(int size) {
		
		List<UserDTO> latests = new ArrayList<>();
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_LATEST_USERS);
			psmt.setInt(1, size);
			
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
				latests.add(dto);
			}
			close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return latests;
	}
	public List<UserDTO> selectUsers() {
		return null;
	}
	public void updateUser(UserDTO dto) {
		
	}
	public void deleteUser(String uid) {
		
	}
	
	
	/************************************************** Count **************************************************/
	public int selectCountUid(String uid) {
		int result = 0;
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_COUNT_UID);
			psmt.setString(1, uid);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
			close();
		} catch (Exception e) {
			logger.error("selectCountUid() error : "+e.getMessage());
		}
		return result;
	}
	
	public int selectCountNick(String nick) {
		int result = 0;
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_COUNT_NICK);
			psmt.setString(1, nick);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
			close();
		} catch (Exception e) {
			logger.error("selectCountNick() error : "+e.getMessage());
		}
		return result;
	}
	
	public int selectCountHp(String hp) {
		int result = 0;
		logger.debug("selectCountHp() hp : "+hp);
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_COUNT_HP);
			psmt.setString(1, hp);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
			close();
		} catch (Exception e) {
			logger.error("selectCountHp() error : "+e.getMessage());
		}
		return result;
	}
	
	public int selectCountEmail(String email) {
		int result = 0;
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_COUNT_EMAIL);
			psmt.setString(1, email);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
			close();
		} catch (Exception e) {
			logger.error("selectCountEmail() error : "+e.getMessage());
		}
		return result;
	}
}
