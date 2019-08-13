package dao;

import java.util.List;

public interface PreferencialStrategyDao extends BaseDao {
	
	//获得所有的优惠政策
	//原：noMoreThanStores当中
	public List findAllPreferencialStrategy();

}
