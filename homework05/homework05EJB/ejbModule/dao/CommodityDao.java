package dao;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface CommodityDao {

	//获取库存中所有的商品
	//原：chooseCommodities当中
	public List findAllCommodities();
	
	//根据商品名称改变商品库存
	//原：payIndex当中
	public void updateStoredSumByName(String name, int storedSum);
	
	
}
