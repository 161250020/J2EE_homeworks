package com.tags;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.action.business.CommodityListBean;
import com.models.commodity;

public class commodityInfoHandler extends SimpleTagSupport {
	public void doTag() throws JspException, IOException {
		CommodityListBean listComs=(CommodityListBean)getJspContext().findAttribute("listComs");
		JspWriter out = getJspContext().getOut();
		//打印列表当中的商品
		//每5个商品打印在一页上面
		ArrayList<commodity> storedCommodities=(ArrayList<commodity>) listComs.getCommodityList();
		int size=storedCommodities.size();
		int rest=size;//还没有打印的数量
		int index=0;//打印到的index
		int pageNum=0;//一共分几页
		while(rest>0) {//如果还有商品未打印
			pageNum=pageNum+1;
			String display="none";
			if(pageNum==1) {//如果是第一页，display为block，否则为none
				display="block";
			}
			if(rest>=5) {//如果剩余的商品>5---可以打印满一页，按照5打印
				out.println("            <table id=\"p"+pageNum+"\" style=\"border: 1px solid green;display: "+display+"\">\r\n" + 
						"                <tr>\r\n" + 
						"                    <th>商品名称</th>\r\n" + 
						"                    <th>单价（元）</th>\r\n" + 
						"                    <th>库存</th>\r\n" + 
						"                    <th>购买数量</th>\r\n" + 
						"                </tr>\r\n");
						
				for(int i=0;i<5;i++) {
					commodity com=storedCommodities.get(index);
					out.println(
							"                <tr>\r\n" + 
							"                    <td>\r\n" + 
							"                       "+com.getName()+"\r\n" + 
							"                    </td>\r\n" + 
							"                    <td>\r\n" + 
							"                        "+com.getPrice()+"\r\n" + 
							"                    </td>\r\n" + 
							"                    <td>\r\n" + 
							"                        "+com.getStoredSum()+"\r\n" + 
							"                    </td>\r\n" + 
							"                    <td>\r\n" + 
							"                        <input type=\"text\" name=\""+com.getName()+"\" value=\"\">\r\n" + 
							"                    </td>\r\n" + 
							"                </tr>\r\n");
					index=index+1;
				}
						
				out.println("</table>\r\n");
						
				rest=rest-5;
			}
			else {//如果剩余的商品<5---不可以打印满一页，按照rest打印
				out.println("            <table id=\"p"+pageNum+"\" style=\"border: 1px solid green;display: "+display+"\">\r\n" + 
						"                <tr>\r\n" + 
						"                    <th>商品名称</th>\r\n" + 
						"                    <th>单价（元）</th>\r\n" + 
						"                    <th>库存</th>\r\n" + 
						"                    <th>购买数量</th>\r\n" + 
						"                </tr>\r\n");
						
				for(int i=0;i<rest;i++) {
					commodity com=storedCommodities.get(index);
					out.println("                <tr>\r\n" + 
							"                    <td>\r\n" + 
							"                       "+com.getName()+"\r\n" + 
							"                    </td>\r\n" + 
							"                    <td>\r\n" + 
							"                        "+com.getPrice()+"\r\n" + 
							"                    </td>\r\n" + 
							"                    <td>\r\n" + 
							"                        "+com.getStoredSum()+"\r\n" + 
							"                    </td>\r\n" + 
							"                    <td>\r\n" + 
							"                        <input type=\"text\" name=\""+com.getName()+"\" value=\"\">\r\n" + 
							"                    </td>\r\n" + 
							"                </tr>\r\n");
					index=index+1;
				}
						
				out.println("</table>\r\n");
				rest=0;
			}
		}
				
		//添加页数的按钮
		for(int i=1;i<=pageNum;i++) {
			out.println("<a href=\"javascript:showpage("+i+");\">第"+i+"页</a>");
		}
				
		out.println("            <br>\r\n" + 
		"            <div align=\"center\">\r\n" + 
		"                <input type=\"submit\" name=\"submitCommodities\" value=\"选择商品\" style=\"font-family:楷体;font-size:18px;width:84px;height:30px;background-color: lightgreen;border-radius: 2px;border:0px;\">\r\n" + 
		"            </div>\r\n" + 
		"        </div>\r\n" + 
		"    </form>\r\n" + 
		"</div>\r\n");
	}
	
}
