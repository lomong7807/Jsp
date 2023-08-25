package dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import db.DBHelper;
import dto.MemberDTO;

public class MemberDAO extends DBHelper{

	public static MemberDAO instance = new MemberDAO();
	public static MemberDAO getInstance() {
		return instance;
	}
	public MemberDAO() {}
	
	// 로거생성
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void insertMember(MemberDTO dto) {
		
		try {
			logger.info("MemberDAO insertMember...1");
			conn = getConnection();
			psmt = conn.prepareStatement("INSERT INTO `member` VALUES (?,?,?,?,?,NOW())");
			psmt.setString(1, dto.getUid());
			psmt.setString(2, dto.getName());
			psmt.setString(3, dto.getHp());
			psmt.setString(4, dto.getPos());
			psmt.setInt(5, dto.getDep());
			psmt.executeUpdate();
			close();
			logger.info("MemberDAO insertMember...2");
			
		}catch (Exception e) {
			logger.error("MemberDAO insertMember error : "+ e.getMessage());
		}
	}
	public MemberDTO selectMember(String uid) {
		
		MemberDTO member = new MemberDTO();
		
		try {
			logger.info("MemberDAO selectMember...1");
			conn = getConnection();
			psmt = conn.prepareStatement("SELECT * FROM `member` WHERE `uid`=?");
			psmt.setString(1, uid);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				member.setUid(rs.getString(1));
				member.setName(rs.getString(2));
				member.setHp(rs.getString(3));
				member.setPos(rs.getString(4));
				member.setDep(rs.getString(5));
				member.setRdate(rs.getString(6));
			}
			
			close();
			
			logger.info("MemberDAO selectMember...2");
			
		} catch (Exception e) {
			logger.error("MemberDAO selectMember error" + e.getMessage());
		}
		return member;
	}
	
	
	public List<MemberDTO> selectMembers() {
		
		List<MemberDTO> members = new ArrayList<>();
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM `member`");
			
			while(rs.next()) {
				MemberDTO dto = new MemberDTO();
				dto.setUid(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setHp(rs.getString(3));
				dto.setPos(rs.getString(4));
				dto.setDep(rs.getString(5));
				dto.setRdate(rs.getString(6));
				members.add(dto);
			}
			close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return members;
	}
	public void updateMember(MemberDTO dto) {
		
		try {
			logger.info("MemberDAO updateMember...1");
			conn = getConnection();
			psmt = conn.prepareStatement("UPDATE `member` SET `name`=?,`hp`=?,`pos`=?,`dep`=? WHERE `uid`=?");
			
			psmt.setString(1, dto.getName());
			psmt.setString(2, dto.getHp());
			psmt.setString(3, dto.getPos());
			psmt.setInt(4, dto.getDep());
			psmt.setString(5, dto.getUid());
			psmt.executeUpdate();
			close();
			
			logger.info("MemberDAO updateMember...2");
		} catch (Exception e) {
			logger.error("MemberDAO updateMember error" + e.getMessage());
		}
	}
	public void deleteMember(String uid) {
		
		try {
			logger.info("MemberDAO deleteMember...1");
			conn = getConnection();
			psmt = conn.prepareStatement("DELETE FROM `member` WHERE `uid`=?");
			psmt.setString(1, uid);
			psmt.executeUpdate();
			
			close();
			logger.info("MemberDAO deleteMember...2");
		} catch (Exception e) {
			logger.error("MemberDAO deleteMember error" + e.getMessage());
		}
	}
}
