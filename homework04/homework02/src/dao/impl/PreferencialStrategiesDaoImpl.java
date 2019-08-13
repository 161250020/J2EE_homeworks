package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dao.DaoHelper;
import dao.PreferencialStrategiesDao;

public class PreferencialStrategiesDaoImpl implements PreferencialStrategiesDao{

	private static PreferencialStrategiesDaoImpl preferencialStrategiesDao=new PreferencialStrategiesDaoImpl();
	private static DaoHelper daoHelper=DaoHelperImpl.getBaseDaoInstance();
	
	private PreferencialStrategiesDaoImpl() {
		
	}
	
	public static PreferencialStrategiesDaoImpl getInstance() {
		return preferencialStrategiesDao;
	}
	
	@Override
	public void insert(String orderId, String preferencialstrategyId) {
		// TODO Auto-generated method stub
		Connection con=daoHelper.getConnection();
		PreparedStatement stmt=null;
		
		try 
		{	
			stmt=con.prepareStatement("insert into preferencialstrategies(orderId, preferencialstrategyId) values (?, ?) ");
			stmt.setString(1, orderId);
			stmt.setString(2, preferencialstrategyId);
			stmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			daoHelper.closeConnection(con);
			daoHelper.closePreparedStatement(stmt);
		}
	}

}
