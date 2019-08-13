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
		//��ӡ�б��е���Ʒ
		//ÿ5����Ʒ��ӡ��һҳ����
		ArrayList<commodity> storedCommodities=(ArrayList<commodity>) listComs.getCommodityList();
		int size=storedCommodities.size();
		int rest=size;//��û�д�ӡ������
		int index=0;//��ӡ����index
		int pageNum=0;//һ���ּ�ҳ
		while(rest>0) {//���������Ʒδ��ӡ
			pageNum=pageNum+1;
			String display="none";
			if(pageNum==1) {//����ǵ�һҳ��displayΪblock������Ϊnone
				display="block";
			}
			if(rest>=5) {//���ʣ�����Ʒ>5---���Դ�ӡ��һҳ������5��ӡ
				out.println("            <table id=\"p"+pageNum+"\" style=\"border: 1px solid green;display: "+display+"\">\r\n" + 
						"                <tr>\r\n" + 
						"                    <th>��Ʒ����</th>\r\n" + 
						"                    <th>���ۣ�Ԫ��</th>\r\n" + 
						"                    <th>���</th>\r\n" + 
						"                    <th>��������</th>\r\n" + 
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
			else {//���ʣ�����Ʒ<5---�����Դ�ӡ��һҳ������rest��ӡ
				out.println("            <table id=\"p"+pageNum+"\" style=\"border: 1px solid green;display: "+display+"\">\r\n" + 
						"                <tr>\r\n" + 
						"                    <th>��Ʒ����</th>\r\n" + 
						"                    <th>���ۣ�Ԫ��</th>\r\n" + 
						"                    <th>���</th>\r\n" + 
						"                    <th>��������</th>\r\n" + 
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
				
		//���ҳ���İ�ť
		for(int i=1;i<=pageNum;i++) {
			out.println("<a href=\"javascript:showpage("+i+");\">��"+i+"ҳ</a>");
		}
				
		out.println("            <br>\r\n" + 
		"            <div align=\"center\">\r\n" + 
		"                <input type=\"submit\" name=\"submitCommodities\" value=\"ѡ����Ʒ\" style=\"font-family:����;font-size:18px;width:84px;height:30px;background-color: lightgreen;border-radius: 2px;border:0px;\">\r\n" + 
		"            </div>\r\n" + 
		"        </div>\r\n" + 
		"    </form>\r\n" + 
		"</div>\r\n");
	}
	
}
