package kr.co.farmstory2.service;

import java.util.List;

import kr.co.farmstory2.dao.FileDAO;
import kr.co.farmstory2.dto.FileDTO;

public enum FileService {

	INSTANCE;
	
	private FileDAO dao = new FileDAO();
	
	public void insertFile(FileDTO dto) {
		dao.insertFile(dto);
	}
	public FileDTO selectFile(String ano) {
		return dao.selectFile(ano);
	}
	public FileDTO selectFileFno(String fno) {
		return dao.selectFileFno(fno);
	}
	public List<FileDTO> selectFiles() {
		return dao.selectFiles();
	}
	public void updateFile(FileDTO dto) {
		dao.updateFile(dto);
	}
	public List<FileDTO> deleteFile(String ano) {
		return dao.deleteFile(ano);
	}
}
