package com.action.business;

import java.io.Serializable;
import java.util.List;

import com.models.commodity;

public class CommodityListBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List CommodityList;

	public List getCommodityList() {
		return CommodityList;
	}

	public void setCommodityList(List commodityList) {
		this.CommodityList = commodityList;
	}

	public void setCommodityList(int index, commodity com) {
		CommodityList.set(index, com);
	}

	public commodity getCommodityList(int index) {
		return (commodity) CommodityList.get(index);
	}

}
