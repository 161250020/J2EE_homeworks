package com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.dao.DaoHelper;
import com.dao.PreferencialStrategiesDao;
@Repository
public class PreferencialStrategiesDaoImpl implements PreferencialStrategiesDao {

	private static DaoHelper daoHelper = DaoHelperImpl.getBaseDaoInstance();

	@Override
	public void insert(String orderId, String preferencialstrategyId) {
		// TODO Auto-generated method stub
		Connection con = daoHelper.getConnection();
		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement(
					"insert into preferencialstrategies(orderId, preferencialstrategyId) values (?, ?) ");
			stmt.setString(1, orderId);
			stmt.setString(2, preferencialstrategyId);
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
