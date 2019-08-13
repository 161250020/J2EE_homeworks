package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dao.PreferencialStrategiesDao;
import models.preferencialstrategies;

@Stateless
public class PreferencialStrategiesDaoBean implements PreferencialStrategiesDao{

	@PersistenceContext
	protected EntityManager em;
	
	public void insert(String orderId, String preferencialstrategyId) {
		// TODO Auto-generated method stub
//		Connection con=daoHelper.getConnection();
//		PreparedStatement stmt=null;
		
		try 
		{	
			preferencialstrategies pss=null;
			pss.setPreferencialstrategyId(preferencialstrategyId);
			pss.setOrderId(orderId);
			em.persist(pss);
			
//			stmt=con.prepareStatement("insert into preferencialstrategies(orderId, preferencialstrategyId) values (?, ?) ");
//			stmt.setString(1, orderId);
//			stmt.setString(2, preferencialstrategyId);
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
