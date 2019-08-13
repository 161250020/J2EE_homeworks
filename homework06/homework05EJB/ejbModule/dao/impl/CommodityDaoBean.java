package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import dao.CommodityDao;
import models.commodity;

@Stateless
public class CommodityDaoBean implements CommodityDao{
	@PersistenceContext
	protected EntityManager em;
	
	public List findAllCommodities() {
		// TODO Auto-generated method stub
		
//		Connection con=daoHelper.getConnection();
//		PreparedStatement stmt=null;
//		ResultSet result=null;
		List list=new ArrayList();
		try 
		{
			Query query=em.createQuery("from commodity c");
			list=query.getResultList();
			em.clear();
			
//			stmt=con.prepareStatement("select * from commodities ");
//			result=stmt.executeQuery();
//			while(result.next())
//			{
//				commodity com=new commodity();
//				com.setName(result.getString("name"));
//				com.setPrice(result.getInt("price"));
//				com.setStoredSum(result.getInt("storedSum"));
//				list.add(com);
//			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		finally
//		{
//			daoHelper.closeConnection(con);
//			daoHelper.closePreparedStatement(stmt);
//			daoHelper.closeResult(result);
//		}
		return list;
	}

	public void updateStoredSumByName(String name, int storedSum) {
		// TODO Auto-generated method stub
//		Connection con=daoHelper.getConnection();
//		PreparedStatement stmt=null;
		try 
		{
			commodity c=em.find(commodity.class, name);
			c.setStoredSum(storedSum);
			em.merge(c);
			
//			stmt=con.prepareStatement("update commodities set storedSum = ? "
//					+ "where name = ? ");
//			stmt.setInt(1, storedSum);
//			stmt.setString(2, name);
//			stmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		finally
//		{
//			daoHelper.closeConnection(con);
//			daoHelper.closePreparedStatement(stmt);
//		}		
	}

}
