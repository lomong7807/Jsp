package kr.co.farmstory2.service;

import java.util.List;

import kr.co.farmstory2.dao.OrderDAO;
import kr.co.farmstory2.dto.OrderDTO;

public enum OrderService {

	INSTANCE;
	
	private OrderDAO dao = new OrderDAO(); 
	
	public void insertOrder(OrderDTO dto) {
		dao.insertOrder(dto);
	}
	public void selectOrder() {}
	public int selectCountOrdersTotal() {
		return dao.selectCountOrdersTotal();
	}
	public List<OrderDTO> selectOrders(int start) {
		return dao.selectOrders(start);
	}
	public List<OrderDTO> selectOrdersAdmin(int start) {
		return dao.selectOrdersAdmin(start);
	}
	public List<OrderDTO> selectLatestOrders(int size) {
		return dao.selectLatestOrders(size);
	}
	public void updateOrder() {}
	public void deleteOrder(String orderNo) {
	}
}
