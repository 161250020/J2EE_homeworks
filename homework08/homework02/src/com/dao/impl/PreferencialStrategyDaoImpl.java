package com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.dao.DaoHelper;
import com.dao.PreferencialStrategyDao;
import com.models.preferencialstrategy;
@Repository
public class PreferencialStrategyDaoImpl implements PreferencialStrategyDao {

	private static DaoHelper daoHelper = DaoHelperImpl.getBaseDaoInstance();

	@Override
	public List findAllPreferencialStrategy() {
		// TODO Auto-generated method stub
		Connection con = daoHelper.getConnection();
		PreparedStatement stmt = null;
		ResultSet result = null;
		ArrayList list = new ArrayList();
		try {
			stmt = con.prepareStatement("select * from preferencialstrategy ");
			result = stmt.executeQuery();
			while (result.next()) {
				preferencialstrategy ps = new preferencialstrategy();
				ps.setId(result.getString("id"));
				ps.setReachMoney(result.getInt("reachMoney"));
				ps.setCutMoney(result.getInt("cutMoney"));
				ps.setComment(result.getString("comment"));
				list.add(ps);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			daoHelper.closeConnection(con);
			daoHelper.closePreparedStatement(stmt);
			daoHelper.closeResult(result);
		}

		return list;
	}

}
