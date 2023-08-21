<%@page import="vo.User1VO"%>
<%@page import="org.jdom2.output.Format"%>
<%@page import="org.jdom2.output.XMLOutputter"%>
<%@page import="org.jdom2.Element"%>
<%@page import="org.jdom2.Document"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@ page contentType="text/xml;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%
	List<User1VO> users = new ArrayList<>();

	try{
		Context initCtx = new InitialContext();
		Context ctx = (Context)initCtx.lookup("java:comp/env");
		
		DataSource ds = (DataSource)ctx.lookup("jdbc/userdb");
		Connection conn = ds.getConnection();
		
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM `user2`");
		
		while(rs.next()){
			User1VO vo = new User1VO();
			vo.setUid(rs.getString(1));
			vo.setName(rs.getString(2));
			vo.setHp(rs.getString(3));
			vo.setAge(rs.getInt(4));
			
			users.add(vo);
			
		}
		
		rs.close();
		stmt.close();
		conn.close();
		
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	// XML 생성 org.dom2에 있는
	Document doc = new Document();
	Element elUsers = new Element("users"); // user 태그를 생성했다는 뜻
	
	for(User1VO vo : users){
		
		Element elUser = new Element("user");
		Element elUid  = new Element("uid");
		Element elName = new Element("name");
		Element elHp   = new Element("hp");
		Element elAge  = new Element("age");
		
		elUid.setText(vo.getUid());
		elName.setText(vo.getName());
		elHp.setText(vo.getHp());
		elAge.setText(""+vo.getAge()); // int를 String 으로 변환
		
		elUser.addContent(elUid);
		elUser.addContent(elName);
		elUser.addContent(elHp);
		elUser.addContent(elAge);
		
		elUsers.addContent(elUser);
		// 위의 코드는 JDOM 라이브러리의 설명서를 봐야 작성할 수 있음
	}
	
	doc.setRootElement(elUsers);
	
	// out.print(jsonData);
	XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
	String xml = outputter.outputString(doc);

	// xml 출력
	out.print(xml);

%>