package dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import db.DBHelper;
import dto.User2DTO;

public class User2DAO extends DBHelper{
	
	public static User2DAO instance = new User2DAO();
	public static User2DAO getInstance() {
		return instance;
	}
	public User2DAO() {}
	
	// 로거생성
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void insertUser2(User2DTO dto) {
		
		try {
			logger.info("User2DAO insertUser2...1");
			
			conn = getConnection();
			psmt = conn.prepareStatement("INSERT INTO `User2` VALUES (?,?,?,?)");
			psmt.setString(1, dto.getUid());
			psmt.setString(2, dto.getName());
			psmt.setString(3, dto.getHp());
			psmt.setInt(4, dto.getAge());
			psmt.executeUpdate();
			close();
			
			logger.info("User2DAO insertUser2...2");
		} catch (Exception e) {
			logger.error("User2DAO insertUser2" + e.getMessage());
		}
	}
	public User2DTO selectUser2(String uid) {
		User2DTO user = new User2DTO();
		
		try {
			logger.info("User2DAO selectUser2...1");
			conn = getConnection();
			psmt = conn.prepareStatement("SELECT * FROM `User2` WHERE `uid`=?");
			psmt.setString(1, uid);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				user.setUid(rs.getString(1));
				user.setName(rs.getString(2));
				user.setHp(rs.getString(3));
				user.setAge(rs.getString(4));
			}
			
			close();
			logger.info("User2DAO selectUser2...2");
		} catch (Exception e) {
			logger.error("User2DAO selectUser2 error" + e.getMessage());
		}
		return user;
	}
	public List<User2DTO> selectUser2s() {
		
		List<User2DTO> users = new ArrayList<>();		
		
		try {
			logger.info("User2DAO selectUser2s...1");
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM `User2`");
			
			while(rs.next()) {
				User2DTO dto = new User2DTO();
				dto.setUid(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setHp(rs.getString(3));
				dto.setAge(rs.getInt(4));
				users.add(dto);
			}
			close();
			logger.info("User2DAO selectUser2s...2");
		} catch (Exception e) {
			logger.error("User2DAO selectUser2s error"+e.getMessage());
		}
		return users;
	}
	public void updateUser2(User2DTO dto) {
		
		try {
			logger.info("User2DAO updateUser2...1");
			conn = getConnection();
			psmt = conn.prepareStatement("UPDATE `User2` SET `name`=?,`hp`=?,`age`=? WHERE `uid`=?");
			psmt.setString(1, dto.getName());
			psmt.setString(2, dto.getHp());
			psmt.setInt(3, dto.getAge());
			psmt.setString(4, dto.getUid());
			psmt.executeUpdate();
			close();
			
			logger.info("User2DAO updateUser2...2");
		} catch (Exception e) {
			logger.error("User2DAO updateUser2 error"+e.getMessage());
		}
	}
	public void deleteUser2(String uid) {
		
		try {
			logger.info("User2DAO deleteUser2...1");
			conn = getConnection();
			psmt = conn.prepareStatement("DELETE FROM `User2` WHERE `uid`=?");
			psmt.setString(1, uid);
			psmt.executeUpdate();
			close();
			
			logger.info("User2DAO deleteUser2...2");
		} catch (Exception e) {
			logger.error("User2DAO deleteUser2 error"+e.getMessage());
		}
	}

}
