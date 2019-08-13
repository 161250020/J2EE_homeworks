package factory;

import service.*;
import service.impl.*;

public class ServiceFactory {

	public static CommodityManageSevice getCommodityManageSevice() {
		return CommodityManageServiceImpl.getInstance();
	}
	
	public static OrdersManageService getOrdersManageService() {
		return OrdersManageServiceImpl.getInstance();
	}
	
	public static OrderUserManageService getOrderUserManageService() {
		return OrderUserManageServiceImpl.getInstance();
	}
	
	public static PreferencialStrategiesManageService getPreferencialStrategiesManageService() {
		return PreferencialStrategiesManageServiceImpl.getInstance();
	}
	
	public static PreferencialStrategyManageService getPreferencialStrategyManageService() {
		return PreferencialStrategyManageServiceImpl.getInstance();
	}
	
	public static UserManageService getUserManageService() {
		return UserManageServiceImpl.getInstance();
	}
}
