package dao;

import javax.ejb.Remote;

@Remote
public interface PreferencialStrategiesDao {

	//��preferencialstrategies�в�����Ϣ
	//ԭ��payIndex����
	public void insert(String orderId, String preferencialstrategyId);
}
