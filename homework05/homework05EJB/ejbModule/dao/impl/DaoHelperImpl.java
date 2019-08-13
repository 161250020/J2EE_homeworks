package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import dao.DaoHelper;

public class DaoHelperImpl implements DaoHelper{

	private static DaoHelperImpl baseDao=new DaoHelperImpl();
	
	private InitialContext jndiContext = null;
	private Connection connection = null;
	private DataSource datasource = null;
	
	private DaoHelperImpl()
	{
		
	}
	
	public static DaoHelperImpl getBaseDaoInstance()
	{
		return baseDao;
	}
	
	public Connection getConnection() {
		// TODO Auto-generated method stub
		
		try {
			 final Hashtable properties = new Hashtable();
			properties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");

			try {
				jndiContext = new InitialContext(properties);
				datasource = (DataSource) jndiContext.lookup("java:/MySQLDS");
				if (datasource==null)
						System.out.println("datasource null");
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			System.out.println("got context");
			System.out.println("About to get ds---DaoHelper");
			connection = datasource.getConnection();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return connection;
	}

	public void closeConnection(Connection con) {
		// TODO Auto-generated method stub
		if(con!=null)
		{
			try 
			{
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void closePreparedStatement(PreparedStatement stmt) {
		// TODO Auto-generated method stub
		if(stmt!=null)
		{
			try
			{
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void closeResult(ResultSet result) {
		// TODO Auto-generated method stub
		if(result!=null)
		{
			try
			{
				result.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
