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

import dao.UserDao;
import models.user;

@Stateless
public class UserDaoBean implements UserDao{

	@PersistenceContext
	protected EntityManager em;
	
	public List findPasswordByUsername(String username) {
		// TODO Auto-generated method stub
//		Connection con=daoHelper.getConnection();
//		PreparedStatement stmt=null;
//		ResultSet result=null;
		List list=new ArrayList();
		try 
		{
			user u=em.find(user.class, username);
			list.add(u.getPassword());
			
//			stmt=con.prepareStatement("select password from users where username = ?");
//			stmt.setString(1, username);
//			result=stmt.executeQuery();
//			while(result.next())
//			{
//				list.add(result.getString("password"));
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

	public List findSummoneyByUsername(String username) {
		// TODO Auto-generated method stub
//		Connection con=daoHelper.getConnection();
//		PreparedStatement stmt=null;
//		ResultSet result=null;
		List list=new ArrayList();
		try 
		{
			user u=em.find(user.class, username);
			list.add(u.getSummoney());

//			stmt=con.prepareStatement("select summoney from users where username = ? ");
//			stmt.setString(1, username);
//			result=stmt.executeQuery();
//			while(result.next())
//			{
//				list.add(result.getInt("summoney"));
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

	public void updateSummoneyByUsername(String username, int summoney) {
		// TODO Auto-generated method stub
//		Connection con=daoHelper.getConnection();
//		PreparedStatement stmt=null;
		try 
		{
			user u=em.find(user.class, username);
			u.setSummoney(summoney);
			em.merge(u);
			
//			stmt=con.prepareStatement("update users set summoney = ? where username = ? ");
//			stmt.setInt(1, summoney);
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
