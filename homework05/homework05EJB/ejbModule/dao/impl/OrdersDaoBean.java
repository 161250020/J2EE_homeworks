package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.ejb.Stateless;

import dao.DaoHelper;
import dao.OrdersDao;

@Stateless
public class OrdersDaoBean implements OrdersDao{

	private static DaoHelper daoHelper=DaoHelperImpl.getBaseDaoInstance();
	
	public void insert(String orderId, String commodityName, int commodityNum) {
		// TODO Auto-generated method stub
		Connection con=daoHelper.getConnection();
		PreparedStatement stmt=null;
		
		try 
		{	
			stmt=con.prepareStatement("insert into orders(orderId, commodityName, commodityNum)"
					+ " values (?, ?, ?) ");
			stmt.setString(1, orderId);
			stmt.setString(2, commodityName);
			stmt.setInt(3, commodityNum);
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
