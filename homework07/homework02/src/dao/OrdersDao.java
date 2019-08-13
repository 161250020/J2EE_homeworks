package dao;

public interface OrdersDao extends BaseDao {

	//向orders当中插入订单的信息
	//原：payIndex当中
	public void insert(String Id, String orderId, String commodityName, int commodityNum);
	
}
