package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dao.BaseDao;
import dao.OrdersDao;
import models.orders;

public class OrdersDaoImpl extends BaseDaoImpl implements OrdersDao{

	private static OrdersDaoImpl ordersDao=new OrdersDaoImpl();
	
	private OrdersDaoImpl() {
		
	}
	
	public static OrdersDaoImpl getInstance() {
		return ordersDao;
	}
	
	@Override
	public void insert(String Id, String orderId, String commodityName, int commodityNum) {
		// TODO Auto-generated method stub
		orders o=new orders();
		o.setId(Id);
		o.setOrderId(orderId);
		o.setCommodityName(commodityName);
		o.setCommodityNum(commodityNum);
		super.save(o);
	}

}
