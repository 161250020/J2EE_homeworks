package dao;

public interface OrderUserDao extends BaseDao {

	//��orderuser�в�����Ϣ
	//ԭ��payIndex����
	public void insert(String orderId, String username);
	
}
