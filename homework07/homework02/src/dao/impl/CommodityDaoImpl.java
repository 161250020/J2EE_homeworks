package dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import dao.CommodityDao;
import utils.HibernateUtil;

public class CommodityDaoImpl extends BaseDaoImpl implements CommodityDao{

	private static CommodityDaoImpl commodityDao=new CommodityDaoImpl();
	
	private CommodityDaoImpl() {
		
	}
	
	public static CommodityDaoImpl getInstance() {
		return commodityDao;
	}
	
	@Override
	public List findAllCommodities() {
		// TODO Auto-generated method stub
		
		ArrayList list=new ArrayList();
		list=(ArrayList) super.retByQuery("from commodity");
		
		return list;
	}

	@Override
	public void updateStoredSumByName(String name, int storedSum) {
		// TODO Auto-generated method stub
		System.out.println("update commodity c set c.name="+name+" where storedSum="+storedSum);
		Session session =HibernateUtil.getSession() ;
		Transaction tx=session.beginTransaction();
		try {
			session.createQuery("update commodity c set c.storedSum=? where name=?")
					.setParameter(0, storedSum).setParameter(1, name).executeUpdate();
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
