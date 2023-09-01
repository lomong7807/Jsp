package kr.co.jboard2.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.jboard2.db.DBHelper;
import kr.co.jboard2.db.SQL;
import kr.co.jboard2.dto.FileDTO;

public class FileDAO extends DBHelper{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void insertFile(FileDTO dto) {
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.INSERT_FILE);
			psmt.setInt(1, dto.getAno());
			psmt.setString(2, dto.getOfile());
			psmt.setString(3, dto.getSfile());
			psmt.executeUpdate();
			close();
		} catch (Exception e) {
			logger.error("insertFile() error : "+e.getMessage());
		}
	}
	
	public FileDTO selectFile(String ano) {
		FileDTO file = new FileDTO();
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_FILE_ANO);
			psmt.setString(1, ano);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				file.setFno(rs.getInt(1));
				file.setAno(rs.getInt(2));
				file.setOfile(rs.getString(3));
				file.setSfile(rs.getString(4));
				file.setDownload(rs.getInt(5));
				file.setRdate(rs.getString(6));
			}
			close();
		} catch (Exception e) {
			logger.error("selectFile(ANO) error : "+e.getMessage());
		}
		
		return file;
	}
	public FileDTO selectFileFno(String fno) {
		FileDTO file = new FileDTO();
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_FILE_FNO);
			psmt.setString(1, fno);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				file.setFno(rs.getInt(1));
				file.setAno(rs.getInt(2));
				file.setOfile(rs.getString(3));
				file.setSfile(rs.getString(4));
				file.setDownload(rs.getInt(5));
				file.setRdate(rs.getString(6));
			}
			close();
		} catch (Exception e) {
			logger.error("selectFile(FNO) error : "+e.getMessage());
		}
		return file;
	}
	
	public List<FileDTO> selectFiles() {
		return null;
	}
	public void updateFile(FileDTO dto) {
		
	}
	public List<FileDTO> deleteFile(String ano) {
		List<FileDTO> file = new ArrayList<>();
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			psmt1 = conn.prepareStatement(SQL.SELECT_FILE_ANO);
			psmt1.setString(1, ano);
			rs = psmt1.executeQuery();
			while(rs.next()) {
				FileDTO dto = new FileDTO();
				dto.setFno(rs.getInt(1));
				dto.setAno(rs.getInt(2));
				dto.setOfile(rs.getString(3));
				dto.setSfile(rs.getString(4));
				dto.setDownload(rs.getInt(5));
				dto.setRdate(rs.getString(6));
				file.add(dto);
			}
			
			psmt = conn.prepareStatement(SQL.DELETE_FILE);
			psmt.setString(1, ano);
			psmt.executeUpdate();
			
			conn.commit();
			close();
		} catch (Exception e) {
			logger.error("deleteFile() error : "+e.getMessage());
		}
		return file;
	}
}
