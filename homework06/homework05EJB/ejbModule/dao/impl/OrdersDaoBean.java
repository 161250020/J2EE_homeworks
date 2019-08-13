package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dao.OrdersDao;
import models.orders;

@Stateless
public class OrdersDaoBean implements OrdersDao{
	
	@PersistenceContext
	protected EntityManager em;

	public void insert(String id, String orderId, String commodityName, int commodityNum) {
		// TODO Auto-generated method stub
//		Connection con=daoHelper.getConnection();
//		PreparedStatement stmt=null;
		
		try 
		{	
			orders o=null;
			o.setId(id);
			o.setOrderId(orderId);
			o.setCommodityName(commodityName);
			o.setCommodityNum(commodityNum);
			em.persist(o);
			
//			stmt=con.prepareStatement("insert into orders(orderId, commodityName, commodityNum)"
//					+ " values (?, ?, ?) ");
//			stmt.setString(1, orderId);
//			stmt.setString(2, commodityName);
//			stmt.setInt(3, commodityNum);
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
