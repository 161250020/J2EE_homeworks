package dao;

import java.util.List;

import dao.BaseDao;

public interface CommodityDao extends BaseDao {

	//获取库存中所有的商品
	//原：chooseCommodities当中
	public List findAllCommodities();
	
	//根据商品名称改变商品库存
	//原：payIndex当中
	//update以及delete的hql语句的执行是需要使用executeUpdate()方法的！
	//注意：update的set的内容和where的内容不要搞混淆了，你在写代码的时候搞混淆了；！
	public void updateStoredSumByName(String name, int storedSum);
	
	
}
