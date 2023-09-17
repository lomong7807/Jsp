package kr.co.farmstory2.db;

public class SQL {

	//********************************************* Terms *********************************************//
	public static final String SELECT_TERMS = "SELECT * FROM `Terms`";
	
	//********************************************* User *********************************************//
	public static final String INSERT_USER = "INSERT INTO `User` SET"
											+ "`uid`=?,"
											+ "`pass`=SHA2(?, 256),"
											+ "`name`=?,"
											+ "`nick`=?,"
											+ "`email`=?,"
											+ "`hp`=?,"
											+ "`zip`=?,"
											+ "`addr1`=?,"
											+ "`addr2`=?,"
											+ "`regip`=?,"
											+ "`regDate`=NOW()";
	public static final String SELECT_USER 			= "SELECT * FROM `User` WHERE `uid`=? AND `pass`=SHA2(?, 256)";
	public static final String SELECT_USERS 		= "SELECT `uid`,`name`,`nick`,`email`,`hp`,`role`,`zip`,`addr1`,`addr2`,`regip`,`regDate` FROM `User` LIMIT ?, 10";
	public static final String SELECT_LATEST_USERS 		= "SELECT `uid`,`name`,`nick`,`email`,`hp`,`role`,`zip`,`addr1`,`addr2`,`regip`,`regDate` FROM `User` Order BY `regDate` DESC LIMIT ?";
	public static final String SELECT_COUNT_UID 	= "SELECT COUNT(*) FROM `User` WHERE `uid`=?";
	public static final String SELECT_COUNT_NICK 	= "SELECT COUNT(*) FROM `User` WHERE `nick`=?";
	public static final String SELECT_COUNT_EMAIL 	= "SELECT COUNT(*) FROM `User` WHERE `email`=?";
	public static final String SELECT_COUNT_HP 		= "SELECT COUNT(*) FROM `User` WHERE `hp`=?";
	public static final String SELECT_COUNT_USER 	= "SELECT COUNT(*) FROM `User`";
	
	//********************************************* Article *********************************************//
	public static final String INSERT_ARTICLE  = "INSERT INTO `Article` SET "
			+ "`cate`=?,"
			+ "`title`=?, "
			+ "`content`=?, "
			+ "`file`=?, "
			+ "`writer`=?, "
			+ "`regip`=?, "
			+ "`rdate`=NOW()";
	public static final String DELETE_ARTICLE = "DELETE FROM `Article` WHERE `no`=? OR `parent`=?";
	public static final String SELECT_ARTICLE  = "SELECT a.*, b.nick FROM `Article` AS a "
			+ "JOIN `User` AS b "
			+ "ON a.writer=b.uid "
			+ "WHERE `no`=?";
	public static final String SELECT_ARTICLES = "SELECT a.*, b.nick "
			+ "FROM `Article` AS a "
			+ "JOIN `User` AS b "
			+ "ON a.writer = b.uid "
			+ "WHERE `parent`=0 AND `cate`=?"
			+ "ORDER BY `no` DESC "
			+ "LIMIT ?, 10";
	public static final String SELECT_ARTICLE_COMMENT = "SELECT `comment` FROM `Article` WHERE `no`=?";
	public static final String UPDATE_ARTICLE = "UPDATE `Article` SET `title`=?, `content`=? WHERE `no`=?";
	public static final String SELECT_COUNT_TOTAL = "SELECT COUNT(*) FROM `Article` WHERE `parent`=0 AND `cate`=?";
	public static final String SELECT_MAX_NO  					= "SELECT MAX(`no`) FROM `Article`";
	public static final String SELECT_LATEST = "SELECT `no`,`title`,`rdate`  FROM `Article` WHERE `parent`=0 AND `cate`=? Order BY `no` DESC LIMIT ?";
	
	//********************************************* Comment *********************************************//
	public static final String INSERT_COMMENT= "INSERT INTO `Article` SET "
			+ "`parent`=?, "
			+ "`content`=?, "
			+ "`writer`=?, "
			+ "`regip`=?, "
			+ "`rdate`=NOW()";
	public static final String SELECT_COMMENTS = "SELECT a.*, b.nick "
			+ "FROM `Article` AS a "
			+ "JOIN `User` AS b "
			+ "ON a.writer = b.uid "
			+ "WHERE `parent`=? ";
	public static final String UPDATE_COMMENT = "UPDATE `Article` SET `content`=? WHERE `no`=?";
	public static final String DELETE_COMMENT = "DELETE FROM `Article` WHERE `no`=?";
	public static final String UPDATE_ARTICLE_FOR_COMMENT_MINUS = "UPDATE `Article` SET `comment`=`comment` - 1 WHERE `no`=?";
	public static final String UPDATE_ARTICLE_FOR_COMMENT_PLUS = "UPDATE `Article` SET `comment`= `comment` + 1 WHERE `no`=?";
	
	//********************************************* Product *********************************************//
	public final static String INSERT_PRODUCT = "INSERT INTO `Product` SET "
												+ "`type`=?, "
												+ "`pName`=?, "
												+ "`price`=?, "
												+ "`delivery`=?, "
												+ "`stock`=?, "
												+ "`thumb1`=?, "
												+ "`thumb2`=?, "
												+ "`thumb3`=?, "
												+ "`seller`=?, "
												+ "`etc`=?, "
												+ "`rdate`=NOW()";
	public final static String SELECT_PRODUCT = "SELECT * FROM `Product` WHERE `pNo`=?";
	public final static String SELECT_LATEST_PRODUCTS = "SELECT * FROM `Product` ORDER BY `pNo` DESC LIMIT ?";
	public final static String SELECT_PRODUCTS_ALL = "SELECT * FROM `Product` WHERE `stock` > 0 LIMIT ?, 10";
	public final static String SELECT_PRODUCTS_TYPE = "SELECT * FROM `Product` WHERE `stock` > 0 AND `type`=? LIMIT ?, 10";
	public final static String SELECT_COUNT_PRODUCTS_ALL = "SELECT COUNT(*) FROM `Product` WHERE `stock` > 0";
	public final static String SELECT_COUNT_PRODUCTS_TYPE = "SELECT COUNT(*) FROM `Product` WHERE `stock` > 0 AND `type`=?";
	
	
	//********************************************* Order *********************************************//
	public final static String INSERT_ORDER = "INSERT INTO `Order` SET "
			+ "`orderProduct`=?, "
			+ "`orderCount`=?, "
			+ "`orderDelivery`=?, "
			+ "`orderPrice`=?, "
			+ "`orderTotal`=?, "
			+ "`receiver`=?, "
			+ "`hp`=?, "
			+ "`zip`=?, "
			+ "`addr1`=?, "
			+ "`addr2`=?, "
			+ "`orderEtc`=?, "
			+ "`orderUser`=?, "
			+ "`orderDate`=NOW() ";
	public final static String SELECT_ORDERS = "SELECT "
			+ "a.*,"
			+ "b.pName,"
			+ "b.thumb1 "
			+ "FROM `Order` AS a "
			+ "JOIN `Product` AS b "
			+ "ON a.orderProduct=b.pNo "
			+ "LIMIT ?, 10";
	public final static String SELECT_ORDERS_ADMIN = "SELECT "
			+ "b.*, "
			+ "a.pName,"
			+ "a.price,"
			+ "c.nick,"
			+ "c.uid, "
			+ "a.pNo, "
			+ "c.name "
			+ "FROM `Product` AS a "
			+ "JOIN `Order` AS b ON a.pNo=b.orderProduct "
			+ "JOIN `User` AS c ON b.orderUser=c.uid "
			+ "LIMIT ?, 10";
	public final static String SELECT_COUNT_ORDERS_ALL = "SELECT COUNT(*) FROM `Order`";
	public final static String SELECT_ORDERS_USER = "SELECT * FROM `Order` WHERE `OrderUser`=?";
	public final static String DELETE_ORDERS = "DELETE FROM `Order` WHERE `orderNo`=?";
	public static final String SELECT_LATEST_ORDERS = "SELECT "
			+ "b.orderNo,"
			+ "a.pName,"
			+ "a.price,"
			+ "b.orderCount,"
			+ "b.orderDelivery,"
			+ "b.orderTotal,"
			+ "c.nick,"
			+ "b.orderDate "
			+ "FROM `Product` AS a "
			+ "JOIN `Order` AS b ON a.pNo=b.orderProduct "
			+ "JOIN `User` AS c ON b.orderUser=c.uid "
			+ "ORDER BY `orderNo` DESC LIMIT ?";
	//********************************************* File *********************************************//
	public static final String INSERT_FILE 						= "INSERT INTO `file` SET "
			+ "`ano`=?,"
			+ "`oName`=?,"
			+ "`sName`=?, "
			+ "`rdate`=NOW()";

	public static final String SELECT_FILE_ANO					= "SELECT b.* FROM `Article` AS a "
				+ "JOIN `file` AS b "
				+ "ON a.`no`=b.ano "
				+ "WHERE b.ano=?";
	
	public static final String SELECT_FILE_FNO					= "SELECT * FROM `file` WHERE `fno`=?";
	
	public static final String DELETE_FILE						= "DELETE FROM `file` WHERE `ano`=?";
	
}
