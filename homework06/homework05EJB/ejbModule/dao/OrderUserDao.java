package dao;

import javax.ejb.Remote;

@Remote
public interface OrderUserDao {

	//向orderuser中插入信息
	//原：payIndex当中
	public void insert(String orderId, String username);
	
}
