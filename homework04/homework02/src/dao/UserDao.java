package dao;

import java.util.List;

public interface UserDao {
	
	//�����û�����ȡ����
	//ԭ��loginIndex����
	public List findPasswordByUsername(String username);
	
	//�����û�����ȡ��Ǯ
	//ԭ��payIndex����
	public List findSummoneyByUsername(String username);
	
	//�����û������½�Ǯ
	//ԭ��payIndex����
	public void updateSummoneyByUsername(String username, int summoney);
	
}
