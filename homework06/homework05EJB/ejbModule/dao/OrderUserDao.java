package dao;

import javax.ejb.Remote;

@Remote
public interface OrderUserDao {

	//��orderuser�в�����Ϣ
	//ԭ��payIndex����
	public void insert(String orderId, String username);
	
}
