package com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.dao.DaoHelper;
import com.dao.UserDao;
@Repository
public class UserDaoImpl implements UserDao {

	private static DaoHelper daoHelper = DaoHelperImpl.getBaseDaoInstance();

	@Override
	public List findPasswordByUsername(String username) {
		// TODO Auto-generated method stub
		Connection con = daoHelper.getConnection();
		PreparedStatement stmt = null;
		ResultSet result = null;
		ArrayList list = new ArrayList();
		try {
			stmt = con.prepareStatement("select password from users where username = ?");
			stmt.setString(1, username);
			result = stmt.executeQuery();
			while (result.next()) {
				list.add(result.getString("password"));
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

	@Override
	public List findSummoneyByUsername(String username) {
		// TODO Auto-generated method stub
		Connection con = daoHelper.getConnection();
		PreparedStatement stmt = null;
		ResultSet result = null;
		ArrayList list = new ArrayList();
		try {
			stmt = con.prepareStatement("select summoney from users where username = ? ");
			stmt.setString(1, username);
			result = stmt.executeQuery();
			while (result.next()) {
				list.add(result.getInt("summoney"));
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

	@Override
	public void updateSummoneyByUsername(String username, int summoney) {
		// TODO Auto-generated method stub
		Connection con = daoHelper.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("update users set summoney = ? where username = ? ");
			stmt.setInt(1, summoney);
			stmt.setString(2, username);
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
