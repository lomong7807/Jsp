package kr.co.farmstory2.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.farmstory2.db.DBHelper;
import kr.co.farmstory2.db.SQL;
import kr.co.farmstory2.dto.ArticleDTO;
import kr.co.farmstory2.dto.OrderDTO;

public class OrderDAO extends DBHelper{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// 기본 CRUD
		public void insertOrder(OrderDTO dto) {
			try {
				conn = getConnection();
				psmt = conn.prepareStatement(SQL.INSERT_ORDER);
				psmt.setInt(1, dto.getOrderProduct());
				psmt.setInt(2, dto.getOrderCount());
				psmt.setInt(3, dto.getOrderDelivery());
				psmt.setInt(4, dto.getOrderPrice());
				psmt.setInt(5, dto.getOrderTotal());
				psmt.setString(6, dto.getReceiver());
				psmt.setString(7, dto.getHp());
				psmt.setString(8, dto.getZip());
				psmt.setString(9, dto.getAddr1());
				psmt.setString(10, dto.getAddr2());
				psmt.setString(11, dto.getOrderEtc());
				psmt.setString(12, dto.getOrderUser());
				psmt.executeUpdate();
				
				close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		public void selectOrder() {}
		public int selectCountOrdersTotal() {
			int total = 0;
			
			try {
				conn = getConnection();
				psmt = conn.prepareStatement(SQL.SELECT_COUNT_ORDERS_ALL);
				rs = psmt.executeQuery();
				
				if(rs.next()) {
					total = rs.getInt(1);
				}
				
				close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return total;
		}
		public List<OrderDTO> selectOrders(int start) {
			
			List<OrderDTO> orders = new ArrayList<>();
			
			try {
				conn = getConnection();
				psmt = conn.prepareStatement(SQL.SELECT_ORDERS);
				psmt.setInt(1, start);
				rs = psmt.executeQuery();
				
				while(rs.next()) {
					OrderDTO order = new OrderDTO();
					order.setOrderNo(rs.getInt(1));
					order.setOrderProduct(rs.getInt(2));
					order.setOrderCount(rs.getInt(3));
					order.setOrderDelivery(rs.getInt(4));
					order.setOrderPrice(rs.getInt(5));
					order.setOrderTotal(rs.getInt(6));
					order.setReceiver(rs.getString(7));
					order.setHp(rs.getString(8));
					order.setZip(rs.getString(9));
					order.setAddr1(rs.getString(10));
					order.setAddr2(rs.getString(11));
					order.setOrderEtc(rs.getString(12));
					order.setOrderUser(rs.getString(13));
					order.setOrderDate(rs.getString(14));
					order.setpName(rs.getString(15));
					order.setThumb1(rs.getString(16));
					orders.add(order);
				}
				close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return orders;
		}
		public List<OrderDTO> selectOrdersAdmin(int start) {
			List<OrderDTO> orders = new ArrayList<>();
			
			try {
				conn = getConnection();
				psmt = conn.prepareStatement(SQL.SELECT_ORDERS);
				psmt.setInt(1, start);
				rs = psmt.executeQuery();
				
				while(rs.next()) {
					OrderDTO order = new OrderDTO();
					order.setOrderNo(rs.getInt(1));
					order.setOrderProduct(rs.getInt(2));
					order.setOrderCount(rs.getInt(3));
					order.setOrderDelivery(rs.getInt(4));
					order.setOrderPrice(rs.getInt(5));
					order.setOrderTotal(rs.getInt(6));
					order.setReceiver(rs.getString(7));
					order.setHp(rs.getString(8));
					order.setZip(rs.getString(9));
					order.setAddr1(rs.getString(10));
					order.setAddr2(rs.getString(11));
					order.setOrderEtc(rs.getString(12));
					order.setOrderUser(rs.getString(13));
					order.setOrderDate(rs.getString(14));
					order.setpName(rs.getString(15));
					order.setpName(rs.getString(16));
					order.setpPrice(rs.getString(17));
					order.setUid(rs.getString(18));
					order.setpNo(rs.getString(19));
					order.setName(rs.getString(20));
					orders.add(order);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return orders;
		}
		
		// 추가 
		public List<OrderDTO> selectLatestOrders(int size) {
			
			List<OrderDTO> latests = new ArrayList<>();
			
			try {
				conn = getConnection();
				psmt = conn.prepareStatement(SQL.SELECT_LATEST_ORDERS);
				psmt.setInt(1, size);
				
				rs = psmt.executeQuery();
				
				while(rs.next()) {
					OrderDTO dto = new OrderDTO();
					dto.setOrderNo(rs.getString(1));
					dto.setpName(rs.getString(2));
					dto.setpPrice(rs.getString(3));
					dto.setOrderCount(rs.getString(4));
					dto.setOrderDelivery(rs.getString(5));
					dto.setOrderTotal(rs.getString(6));
					dto.setNick(rs.getString(7));
					dto.setOrderDate(rs.getString(8));
					latests.add(dto);
				}
				close();
			}catch (Exception e) {
				logger.error("selectLatestOrders() error : "+e.getMessage());;
			}
			
			return latests;
		}
		public void updateOrder() {}
		public void deleteOrder(String orderNo) {
			
			try {
				conn = getConnection();
				psmt = conn.prepareStatement(SQL.DELETE_ORDERS);
				psmt.setString(1, orderNo);
				psmt.executeUpdate();
				
				close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
}
