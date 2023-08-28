package dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import db.DBHelper;
import dto.User6DTO;

public class User6DAO extends DBHelper{
	
	public static User6DAO instance = new User6DAO();
	public static User6DAO getInstance() {
		return instance;
	}
	
	public User6DAO() {}
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	

	public void insertUser6(User6DTO dto) {
		
		try {
			logger.error("User6DAO insertUser6...1");
			conn = getConnection();
			psmt = conn.prepareStatement("INSERT INTO `User6` VALUES (?,?,?,?)");
			psmt.setString(1, dto.getName());
			psmt.setInt(2, dto.getGender());
			psmt.setInt(3, dto.getAge());
			psmt.setString(4, dto.getAddr());
			psmt.executeUpdate();
			close();
			
			logger.error("User6DAO insertUser6...2");
		} catch (Exception e) {
			logger.error("User6DAO insertUser6 error : "+e.getMessage());
		}
	}
	public User6DTO selectUser6(String name) {
		User6DTO user = new User6DTO();
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement("SELECT * FROM `User6` WHERE `name`=?");
			psmt.setString(1, name);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				user.setName(rs.getString(1));
				user.setGender(rs.getString(2));
				user.setAge(rs.getString(3));
				user.setAddr(rs.getString(4));
			}
			close();
			
		} catch (Exception e) {
			logger.error("User6DAO selectUser6 error : "+ e.getMessage());
		}
		
		return user;
	}
	public List<User6DTO> selectUser6s() {
		
		List<User6DTO> users = new ArrayList<>();
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM `User6`");
			
			while(rs.next()) {
				User6DTO dto = new User6DTO();
				dto.setName(rs.getString(1));
				dto.setGender(rs.getInt(2));
				dto.setAge(rs.getInt(3));
				dto.setAddr(rs.getString(4));
				users.add(dto);
			}
			close();
		} catch (Exception e) {
			logger.error("User6DAO selectUser6s error : "+e.getMessage());
		}
		return users;
	}
	public void updateUser6(User6DTO dto) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement("UPDATE `User6` SET `gender`=?,`age`=?,`addr`=? WHERE `name`=?");
			psmt.setInt(1, dto.getGender());
			psmt.setInt(2, dto.getAge());
			psmt.setString(3, dto.getAddr());
			psmt.setString(4, dto.getName());
			psmt.executeUpdate();
			close();
			
		} catch (Exception e) {
			logger.error("User6DAO updateUser6 error : "+e.getMessage());
		}
		
	}
	public void deleteUser6(String name) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement("DELETE FROM `User6` WHERE `name`=?");
			psmt.setString(1, name);
			psmt.executeUpdate();
			close();
			
		} catch (Exception e) {
			logger.error("User6DAO deleteUser6 error : "+e.getMessage());
		}
	}
}
