package dao.impl;

import dao.PreferencialStrategiesDao;
import models.preferencialstrategies;

public class PreferencialStrategiesDaoImpl extends BaseDaoImpl implements PreferencialStrategiesDao{

	private static PreferencialStrategiesDaoImpl preferencialStrategiesDao=new PreferencialStrategiesDaoImpl();
	
	private PreferencialStrategiesDaoImpl() {
		
	}
	
	public static PreferencialStrategiesDaoImpl getInstance() {
		return preferencialStrategiesDao;
	}
	
	@Override
	public void insert(String orderId, String preferencialstrategyId) {
		// TODO Auto-generated method stub
		preferencialstrategies pss=new preferencialstrategies();
		pss.setOrderId(orderId);
		pss.setPreferencialstrategyId(preferencialstrategyId);
		super.save(pss);
	}

}
