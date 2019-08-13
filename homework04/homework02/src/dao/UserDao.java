package dao;

import java.util.List;

public interface UserDao {
	
	//根据用户名获取密码
	//原：loginIndex当中
	public List findPasswordByUsername(String username);
	
	//根据用户名获取金钱
	//原：payIndex当中
	public List findSummoneyByUsername(String username);
	
	//根据用户名更新金钱
	//原：payIndex当中
	public void updateSummoneyByUsername(String username, int summoney);
	
}
