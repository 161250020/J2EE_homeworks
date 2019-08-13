package dao;

import javax.ejb.Remote;

@Remote
public interface OrdersDao {

	//向orders当中插入订单的信息
	//原：payIndex当中
	public void insert(String orderId, String commodityName, int commodityNum);
	
}
