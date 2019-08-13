package dao.impl;

import java.util.ArrayList;
import java.util.List;
import dao.PreferencialStrategyDao;

public class PreferencialStrategyDaoImpl extends BaseDaoImpl implements PreferencialStrategyDao{

	private static PreferencialStrategyDaoImpl preferencialStrategyDao=new PreferencialStrategyDaoImpl();
	
	private PreferencialStrategyDaoImpl() {
		
	}
	
	public static PreferencialStrategyDaoImpl getInstance() {
		return preferencialStrategyDao;
	}
	
	@Override
	public List findAllPreferencialStrategy() {
		// TODO Auto-generated method stub
		ArrayList list=new ArrayList();
		list=(ArrayList) super.retByQuery("from preferencialstrategy");
		
		return list;
	}

}
