package dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import db.DBHelper;
import dto.User3DTO;

public class User3DAO extends DBHelper{

	public static User3DAO instance = new User3DAO();
	public static User3DAO getInstance() {
		return instance;
	}
	public User3DAO() {}
	
	// 로거생성
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void insertUser3(User3DTO dto) {
		
		try {
			logger.info("User3DAO insertUser3...1");
			
			conn = getConnection();
			psmt = conn.prepareStatement("INSERT INTO `User3` VALUES (?,?,?,?)");
			psmt.setString(1, dto.getUid());
			psmt.setString(2, dto.getName());
			psmt.setString(3, dto.getHp());
			psmt.setInt(4, dto.getAge());
			psmt.executeUpdate();
			close();
			
			logger.info("User3DAO insertUser3...2");
		} catch (Exception e) {
			logger.error("User3DAO insertUser3" + e.getMessage());
		}
	}
	public User3DTO selectUser3(String uid) {
		User3DTO user = new User3DTO();
		
		try {
			logger.info("User3DAO selectUser3...1");
			conn = getConnection();
			psmt = conn.prepareStatement("SELECT * FROM `User3` WHERE `uid`=?");
			psmt.setString(1, uid);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				user.setUid(rs.getString(1));
				user.setName(rs.getString(2));
				user.setHp(rs.getString(3));
				user.setAge(rs.getString(4));
			}
			
			close();
			logger.info("User3DAO selectUser3...2");
		} catch (Exception e) {
			logger.error("User3DAO selectUser3 error" + e.getMessage());
		}
		return user;
	}
	public List<User3DTO> selectUser3s() {
		
		List<User3DTO> users = new ArrayList<>();		
		
		try {
			logger.info("User3DAO selectUser3s...1");
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM `User3`");
			
			while(rs.next()) {
				User3DTO dto = new User3DTO();
				dto.setUid(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setHp(rs.getString(3));
				dto.setAge(rs.getInt(4));
				users.add(dto);
			}
			close();
			logger.info("User3DAO selectUser3s...2");
		} catch (Exception e) {
			logger.error("User3DAO selectUser3s error"+e.getMessage());
		}
		return users;
	}
	public void updateUser3(User3DTO dto) {
		
		try {
			logger.info("User3DAO updateUser3s...1");
			conn = getConnection();
			psmt = conn.prepareStatement("UPDATE `User3` SET `name`=?,`hp`=?,`age`=? WHERE `uid`=?");
			psmt.setString(1, dto.getName());
			psmt.setString(2, dto.getHp());
			psmt.setInt(3, dto.getAge());
			psmt.setString(4, dto.getUid());
			psmt.executeUpdate();
			close();
			
			logger.info("User3DAO updateUser3...2");
		} catch (Exception e) {
			logger.error("User3DAO updateUser3 error"+e.getMessage());
		}
	}
	public void deleteUser3(String uid) {
		
		try {
			logger.info("User3DAO deleteUser3...1");
			conn = getConnection();
			psmt = conn.prepareStatement("DELETE FROM `User3` WHERE `uid`=?");
			psmt.setString(1, uid);
			psmt.executeUpdate();
			close();
			
			logger.info("User3DAO deleteUser3...2");
		} catch (Exception e) {
			logger.error("User3DAO deleteUser3 error"+e.getMessage());
		}
	}
}
