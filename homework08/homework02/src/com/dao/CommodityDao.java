package com.dao;

import java.util.List;

public interface CommodityDao {

	//��ȡ��������е���Ʒ
	//ԭ��chooseCommodities����
	public List findAllCommodities();
	
	//������Ʒ���Ƹı���Ʒ���
	//ԭ��payIndex����
	public void updateStoredSumByName(String name, int storedSum);
	
	
}
