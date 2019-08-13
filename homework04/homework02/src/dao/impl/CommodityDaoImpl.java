package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.CommodityDao;
import dao.DaoHelper;
import models.commodity;

public class CommodityDaoImpl implements CommodityDao{

	private static CommodityDaoImpl commodityDao=new CommodityDaoImpl();
	private static DaoHelper daoHelper=DaoHelperImpl.getBaseDaoInstance();
	
	private CommodityDaoImpl() {
		
	}
	
	public static CommodityDaoImpl getInstance() {
		return commodityDao;
	}
	
	@Override
	public List findAllCommodities() {
		// TODO Auto-generated method stub
		
		Connection con=daoHelper.getConnection();
		PreparedStatement stmt=null;
		ResultSet result=null;
		ArrayList list=new ArrayList();
		try 
		{
			stmt=con.prepareStatement("select * from commodities ");
			result=stmt.executeQuery();
			while(result.next())
			{
				commodity com=new commodity();
				com.setName(result.getString("name"));
				com.setPrice(result.getInt("price"));
				com.setStoredSum(result.getInt("storedSum"));
				list.add(com);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			daoHelper.closeConnection(con);
			daoHelper.closePreparedStatement(stmt);
			daoHelper.closeResult(result);
		}
		
		return list;
	}

	@Override
	public void updateStoredSumByName(String name, int storedSum) {
		// TODO Auto-generated method stub
		Connection con=daoHelper.getConnection();
		PreparedStatement stmt=null;
		try 
		{
			stmt=con.prepareStatement("update commodities set storedSum = ? "
					+ "where name = ? ");
			stmt.setInt(1, storedSum);
			stmt.setString(2, name);
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
