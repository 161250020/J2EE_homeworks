package dao;

public interface OrdersDao extends BaseDao {

	//��orders���в��붩������Ϣ
	//ԭ��payIndex����
	public void insert(String Id, String orderId, String commodityName, int commodityNum);
	
}
