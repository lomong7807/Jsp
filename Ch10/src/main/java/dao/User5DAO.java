package dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import db.DBHelper;
import dto.User5DTO;

public class User5DAO extends DBHelper{
	
	public static User5DAO instance = new User5DAO();
	public static User5DAO getInstance() {
		return instance;
	}
	
	public User5DAO() {}
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	

	public void insertUser5(User5DTO dto) {
		
		try {
			logger.error("User5DAO insertUser5...1");
			conn = getConnection();
			psmt = conn.prepareStatement("INSERT INTO `User5` VALUES (?,?,?,?)");
			psmt.setString(1, dto.getName());
			psmt.setInt(2, dto.getGender());
			psmt.setInt(3, dto.getAge());
			psmt.setString(4, dto.getAddr());
			psmt.executeUpdate();
			close();
			
			logger.error("User5DAO insertUser5...2");
		} catch (Exception e) {
			logger.error("User5DAO insertUser5 error : "+e.getMessage());
		}
	}
	public User5DTO selectUser5(String name) {
		User5DTO user = new User5DTO();
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement("SELECT * FROM `User5` WHERE `name`=?");
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
			logger.error("User5DAO selectUser5 error : "+ e.getMessage());
		}
		
		return user;
	}
	public List<User5DTO> selectUser5s() {
		
		List<User5DTO> users = new ArrayList<>();
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM `User5`");
			
			while(rs.next()) {
				User5DTO dto = new User5DTO();
				dto.setName(rs.getString(1));
				dto.setGender(rs.getInt(2));
				dto.setAge(rs.getInt(3));
				dto.setAddr(rs.getString(4));
				users.add(dto);
			}
			close();
		} catch (Exception e) {
			logger.error("User5DAO selectUser5s error : "+e.getMessage());
		}
		return users;
	}
	public void updateUser5(User5DTO dto) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement("UPDATE `User5` SET `gender`=?,`age`=?,`address`=? WHERE `name`=?");
			psmt.setInt(1, dto.getGender());
			psmt.setInt(2, dto.getAge());
			psmt.setString(3, dto.getAddr());
			psmt.setString(4, dto.getName());
			psmt.executeUpdate();
			close();
			
		} catch (Exception e) {
			logger.error("User5DAO updateUser5 error : "+e.getMessage());
		}
		
	}
	public void deleteUser5(String name) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement("DELETE FROM `User5` WHERE `name`=?");
			psmt.setString(1, name);
			psmt.executeUpdate();
			close();
			
		} catch (Exception e) {
			logger.error("User5DAO deleteUser5 error : "+e.getMessage());
		}
	}
}
