package utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import models.*;

public class HibernateUtil {
	private static SessionFactory sessionFactory;
	
	 public static SessionFactory getSessionFactory(){
		 try {
				Configuration config;
				ServiceRegistry serviceRegistry;
				config = new Configuration().configure();
				config.addAnnotatedClass(commodity.class);
				config.addAnnotatedClass(orders.class);
				config.addAnnotatedClass(orderuser.class);
				config.addAnnotatedClass(preferencialstrategies.class);
				config.addAnnotatedClass(preferencialstrategy.class);
				config.addAnnotatedClass(user.class);
				serviceRegistry =new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
				sessionFactory=config.buildSessionFactory(serviceRegistry);	
				return sessionFactory;
			}catch (Exception e) {
				e.printStackTrace();
				return null;
			}
	 }
	 
	 /** * gerCurrentSession 会自动关闭session，使用的是当前的session事务 * * @return */
	 public static Session getSession(){
		 return getSessionFactory().getCurrentSession();
	 }
}
