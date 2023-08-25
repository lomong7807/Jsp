package dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import db.DBHelper;
import dto.User4DTO;

public class User4DAO extends DBHelper{
	
	public static User4DAO instance = new User4DAO();
	public static User4DAO getInstance() {
		return instance;
	}
	
	public User4DAO() {}
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void insertUser4(User4DTO dto) {
		
		try {
			logger.error("User4DAO insertUser4...1");
			conn = getConnection();
			psmt = conn.prepareStatement("INSERT INTO `User4` (`name`,`gender`,`age`,`addr`) VALUES (?,?,?,?)");
			psmt.setString(1, dto.getName());
			psmt.setInt(2, dto.getGender());
			psmt.setInt(3, dto.getAge());
			psmt.setString(4, dto.getAddr());
			psmt.executeUpdate();
			close();
			
			logger.error("User4DAO insertUser4...2");
		} catch (Exception e) {
			logger.error("User4DAO insertUser4 error"+e.getMessage());
		}
		
	}
	public User4DTO selectUser4(String name) {
		User4DTO user = new User4DTO();
		
		try {
			logger.info("User4DAO selectUser4...1");
			conn = getConnection();
			psmt = conn.prepareStatement("SELECT `name`,`gender`,`age`,`addr` FROM `User4` WHERE `name`=?");
			psmt.setString(1, name);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				user.setName(rs.getString(1));
				user.setGender(rs.getString(2));
				user.setAge(rs.getString(3));
				user.setAddr(rs.getString(4));
			}
			
			close();
			logger.info("User4DAO selectUser4...2");
		} catch (Exception e) {
			logger.error("User4DAO selectUser4 error" + e.getMessage());
		}
		return user;
	}
	public List<User4DTO> selectUser4s() {
		
		List<User4DTO> users = new ArrayList<>();		
		
		try {
			logger.info("User4DAO selectUser4s...1");
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT `name`, `gender`, `age`, `addr` FROM `User4`");
			
			while(rs.next()) {
				User4DTO dto = new User4DTO();
				dto.setName(rs.getString(1));
				dto.setGender(rs.getInt(2));
				dto.setAge(rs.getInt(3));
				dto.setAddr(rs.getString(4));
				users.add(dto);
			}
			close();
			logger.info("User4DAO selectUser4s...2");
		} catch (Exception e) {
			logger.error("User4DAO selectUser4s error"+e.getMessage());
		}
		return users;
	}
	public void updateUser4(User4DTO dto) {
		
		try {
			logger.info("User4DAO updateUser4...1");
			conn = getConnection();
			psmt = conn.prepareStatement("UPDATE `User4` SET `gender`=?,`age`=?,`addr`=? WHERE `name`=?");
			psmt.setInt(1, dto.getGender());
			psmt.setInt(2, dto.getAge());
			psmt.setString(3, dto.getAddr());
			psmt.setString(4, dto.getName());
			psmt.executeUpdate();
			close();
			
			logger.info("User4DAO updateUser4...2");
		} catch (Exception e) {
			logger.error("User4DAO updateUser4 error"+e.getMessage());
		}
	}
	public void deleteUser4(String name) {
		
		try {
			logger.info("User4DAO deleteUser4...1");
			conn = getConnection();
			psmt = conn.prepareStatement("DELETE FROM `User4` WHERE `name`=?");
			psmt.setString(1, name);
			psmt.executeUpdate();
			close();
			
			logger.info("User4DAO deleteUser4...2");
		} catch (Exception e) {
			logger.error("User4DAO deleteUser4 error"+e.getMessage());
		}
	}

}
