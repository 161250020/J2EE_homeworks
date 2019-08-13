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

import dao.PreferencialStrategyDao;
import models.preferencialstrategy;

@Stateless
public class PreferencialStrategyDaoBean implements PreferencialStrategyDao{

	@PersistenceContext
	protected EntityManager em;
	
	public List findAllPreferencialStrategy() {
		// TODO Auto-generated method stub
//		Connection con=daoHelper.getConnection();
//		PreparedStatement stmt=null;
//		ResultSet result=null;
		List list=new ArrayList();
		try 
		{
			Query query=em.createQuery("from preferencialstrategy ps");
			list=query.getResultList();
			em.clear();
			
//			stmt=con.prepareStatement("select * from preferencialstrategy ");
//			result=stmt.executeQuery();
//			while(result.next())
//			{
//				preferencialstrategy ps=new preferencialstrategy();
//				ps.setId(result.getString("id"));
//				ps.setReachMoney(result.getInt("reachMoney"));
//				ps.setCutMoney(result.getInt("cutMoney"));
//				ps.setComment(result.getString("comment"));
//				list.add(ps);
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

}
