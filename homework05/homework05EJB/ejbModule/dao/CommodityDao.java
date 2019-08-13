package dao;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface CommodityDao {

	//��ȡ��������е���Ʒ
	//ԭ��chooseCommodities����
	public List findAllCommodities();
	
	//������Ʒ���Ƹı���Ʒ���
	//ԭ��payIndex����
	public void updateStoredSumByName(String name, int storedSum);
	
	
}
