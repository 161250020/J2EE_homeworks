package dao;

import java.util.List;

import dao.BaseDao;

public interface CommodityDao extends BaseDao {

	//��ȡ��������е���Ʒ
	//ԭ��chooseCommodities����
	public List findAllCommodities();
	
	//������Ʒ���Ƹı���Ʒ���
	//ԭ��payIndex����
	//update�Լ�delete��hql����ִ������Ҫʹ��executeUpdate()�����ģ�
	//ע�⣺update��set�����ݺ�where�����ݲ�Ҫ������ˣ�����д�����ʱ�������ˣ���
	public void updateStoredSumByName(String name, int storedSum);
	
	
}
