package service;

import java.util.List;

import dao.User4DAO;
import dto.User4DTO;

public class User4Service {
public User4DAO dao = new User4DAO();
	
	public void insertUser4(User4DTO dto) {
		dao.insertUser4(dto);
	}
	public User4DTO selectUser4(String name) {
		return dao.selectUser4(name);
	}
	public List<User4DTO> selectUser4s() {
		return dao.selectUser4s();
	}
	public void updateUser4(User4DTO dto) {
		dao.updateUser4(dto);
	}
	public void deleteUser4(String name) {
		dao.deleteUser4(name);
	}
}
