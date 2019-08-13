package dao.impl;

import dao.OrderUserDao;
import models.orderuser;

public class OrderUserDaoImpl extends BaseDaoImpl implements OrderUserDao{

	private static OrderUserDaoImpl orderUserDao=new OrderUserDaoImpl();
	
	private OrderUserDaoImpl() {
		
	}
	
	public static OrderUserDaoImpl getInstance() {
		return orderUserDao;
	}
	
	@Override
	public void insert(String orderId, String username) {
		// TODO Auto-generated method stub
		orderuser ou=new orderuser();
		ou.setOrderId(orderId);
		ou.setUsername(username);
		super.save(ou);
	}

}
