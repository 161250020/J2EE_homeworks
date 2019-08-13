package dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import dao.BaseDao;
import utils.HibernateUtil;


public class BaseDaoImpl implements BaseDao {
	
	public BaseDaoImpl() {
	}

	public void flush() {
		HibernateUtil.getSession().flush();
	}

	public void clear() {
		HibernateUtil.getSession().clear();
	}

	/** * 保存 * * @param bean * */
	public void save(Object bean) {
		try {
			System.out.println("ready to getsession");	
			Session session =HibernateUtil.getSession() ;
			Transaction tx=session.beginTransaction();
			session.merge(bean);
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}  
	}
	
	/** * 根据 id 查询信息 * * @param id * @return */
	public Object load(Class c, String id) {
		try {
			Session session = HibernateUtil.getSession();
			Transaction tx=session.beginTransaction();
			Object o=session.get(c, id);
			tx.commit();
			return o;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}

	/** * 更新 * * @param bean * */
	public void update(Object bean) {
		try {
			System.out.println("ready to getsession");	
			Session session =HibernateUtil.getSession() ;
			Transaction tx=session.beginTransaction();
			session.update(bean);
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}  
	}

	/** * 删除 * * @param bean * */
	public void delete(Object bean) {
		try {
			Session session =HibernateUtil.getSession() ;
			Transaction tx=session.beginTransaction();
			session.delete(bean);
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	/*   根据hql语句返回    */
	public List retByQuery(String hql) {
		// TODO Auto-generated method stub
		Session session =HibernateUtil.getSession() ;
		Transaction tx=session.beginTransaction();
		try {
			Query query=session.createQuery(hql);
			List ret=query.list();
			tx.commit();
			return ret;
		}catch (Exception e){
			if(tx!=null){
				tx.rollback();
			}
			e.printStackTrace();
			return null;
		}finally {
			session.close();
		}
	}
	
}
