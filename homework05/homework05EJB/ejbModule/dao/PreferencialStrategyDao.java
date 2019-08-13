package dao;

import java.util.List;

import javax.ejb.Remote;
@Remote
public interface PreferencialStrategyDao {
	
	//获得所有的优惠政策
	//原：noMoreThanStores当中
	public List findAllPreferencialStrategy();

}
