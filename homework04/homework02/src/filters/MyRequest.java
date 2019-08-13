package filters;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class MyRequest extends HttpServletRequestWrapper{

	HttpServletRequest request=null;

	public MyRequest(HttpServletRequest req) {
		// TODO Auto-generated constructor stub
		super(req);
		this.request=req;
	}

	@Override
	public String getParameter(String name) {
		//�˷������Ի�õ�ǰ��get����post������
		//��ÿ��servlet���н������ñ��룬post�������������Ƚϼ򵥣�����get����Ҫ�ֶ�ת�룬���鷳
		//String method=request.getMethod();//���أ�"get"/"post"
		String value=null;
		
		try {
			//���öԿͻ�������������±���ı��룻
			request.setCharacterEncoding("utf-8");
			
			value=request.getParameter(name);
			
			System.out.println("getParameter: "+value);
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return value;
	}
	
	@Override
	public String[] getParameterValues(String name) {
		String[] values=null;
		
		try {
			
			request.setCharacterEncoding("utf-8");
			
			values=request.getParameterValues(name);
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return values;
	}
}
