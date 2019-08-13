package factory;

import dao.*;
import dao.impl.*;

public class DaoFactory {

	public static CommodityDao getCommodityDao() {
		return CommodityDaoImpl.getInstance();
	}
	
	public static OrdersDao getOrdersDao() {
		return OrdersDaoImpl.getInstance();
	}
	
	public static OrderUserDao getOrderUserDao() {
		return OrderUserDaoImpl.getInstance();
	}
	
	public static PreferencialStrategiesDao getPreferencialStrategiesDao() {
		return PreferencialStrategiesDaoImpl.getInstance();
	}
	
	public static PreferencialStrategyDao getPreferencialStrategyDao() {
		return PreferencialStrategyDaoImpl.getInstance();
	}
	
	public static UserDao getUserDao() {
		return UserDaoImpl.getInstance();
	}
}
