package dao;

import javax.ejb.Remote;

@Remote
public interface OrdersDao {

	//��orders���в��붩������Ϣ
	//ԭ��payIndex����
	public void insert(String orderId, String commodityName, int commodityNum);
	
}
