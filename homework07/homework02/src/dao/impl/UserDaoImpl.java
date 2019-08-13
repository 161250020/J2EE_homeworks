package dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.UserDao;
import models.user;
import utils.HibernateUtil;

public class UserDaoImpl extends BaseDaoImpl implements UserDao{

	private static UserDaoImpl userDao=new UserDaoImpl();
	
	private UserDaoImpl() {
		
	}
	
	public static UserDaoImpl getInstance() {
		return userDao;
	}
	
	@Override
	public List findPasswordByUsername(String username) {
		// TODO Auto-generated method stub
		ArrayList list=new ArrayList();
		user u=(user) super.load(user.class, username);
		list.add(u.getPassword());
		
		return list;
	}

	@Override
	public List findSummoneyByUsername(String username) {
		// TODO Auto-generated method stub
		ArrayList list=new ArrayList();
		user u=(user) super.load(user.class, username);
		list.add(u.getSummoney());
		
		return list;
	}

	@Override
	public void updateSummoneyByUsername(String username, int summoney) {
		// TODO Auto-generated method stub
		Session session =HibernateUtil.getSession() ;
		Transaction tx=session.beginTransaction();
		try {
			session.createQuery("update user u set u.summoney=? where username=?")
					.setParameter(0, summoney).setParameter(1, username).executeUpdate();
			tx.commit();
		}catch (Exception e){
			if(tx!=null){
				tx.rollback();
			}
			e.printStackTrace();
		}finally {
			session.close();
		}
	}

}
