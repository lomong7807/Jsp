package service;

import java.util.List;

import dao.User6DAO;
import dto.User6DTO;

public class User6Service {
	
	public User6DAO dao = new User6DAO();
	
	public void insertUser6(User6DTO dto) {
		dao.insertUser6(dto);
	}
	public User6DTO selectUser6(String name) {
		return dao.selectUser6(name);
	}
	public List<User6DTO> selectUser6s() {
		return dao.selectUser6s();
	}
	public void updateUser6(User6DTO dto) {
		dao.updateUser6(dto);
	}
	public void deleteUser6(String name) {
		dao.deleteUser6(name);
	}
}