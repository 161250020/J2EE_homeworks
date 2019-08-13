package dao;

public interface PreferencialStrategiesDao extends BaseDao {

	//向preferencialstrategies中插入信息
	//原：payIndex当中
	public void insert(String orderId, String preferencialstrategyId);
}
