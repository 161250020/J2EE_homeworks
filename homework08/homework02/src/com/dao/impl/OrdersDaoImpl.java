package com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.dao.DaoHelper;
import com.dao.OrdersDao;
@Repository
public class OrdersDaoImpl implements OrdersDao {

	private static DaoHelper daoHelper = DaoHelperImpl.getBaseDaoInstance();

	@Override
	public void insert(String Id, String orderId, String commodityName, int commodityNum) {
		// TODO Auto-generated method stub
		Connection con = daoHelper.getConnection();
		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement(
					"insert into orders(orderId, commodityName, commodityNum, Id)" + " values (?, ?, ?, ?) ");
			stmt.setString(1, orderId);
			stmt.setString(2, commodityName);
			stmt.setInt(3, commodityNum);
			stmt.setString(4, Id);
			stmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			daoHelper.closeConnection(con);
			daoHelper.closePreparedStatement(stmt);
		}
	}

}
