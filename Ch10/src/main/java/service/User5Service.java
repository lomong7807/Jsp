package service;

import java.util.List;

import dao.User5DAO;
import dto.User5DTO;

public class User5Service {
	
	public User5DAO dao = new User5DAO();
	
	public void insertUser5(User5DTO dto) {
		dao.insertUser5(dto);
	}
	public User5DTO selectUser5(String name) {
		return dao.selectUser5(name);
	}
	public List<User5DTO> selectUser5s() {
		return dao.selectUser5s();
	}
	public void updateUser5(User5DTO dto) {
		dao.updateUser5(dto);
	}
	public void deleteUser5(String name) {
		dao.deleteUser5(name);
	}
}
