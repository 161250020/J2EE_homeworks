package dao;

import javax.ejb.Remote;

@Remote
public interface PreferencialStrategiesDao {

	//向preferencialstrategies中插入信息
	//原：payIndex当中
	public void insert(String orderId, String preferencialstrategyId);
}
