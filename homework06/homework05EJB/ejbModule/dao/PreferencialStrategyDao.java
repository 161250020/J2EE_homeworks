package dao;

import java.util.List;

import javax.ejb.Remote;
@Remote
public interface PreferencialStrategyDao {
	
	//������е��Ż�����
	//ԭ��noMoreThanStores����
	public List findAllPreferencialStrategy();

}
