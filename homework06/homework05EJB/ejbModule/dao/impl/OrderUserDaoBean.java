package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dao.OrderUserDao;
import models.orderuser;

@Stateless
public class OrderUserDaoBean implements OrderUserDao{
	
	@PersistenceContext
	protected EntityManager em;

	public void insert(String orderId, String username) {
		// TODO Auto-generated method stub
//		Connection con=daoHelper.getConnection();
//		PreparedStatement stmt=null;
		
		try 
		{	
			orderuser ou=null;
			ou.setOrderId(orderId);
			ou.setUsername(username);
			em.persist(ou);
			
//			stmt=con.prepareStatement("insert into orderuser(orderId, username) values (?, ?) ");
//			stmt.setString(1, orderId);
//			stmt.setString(2, username);
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
