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
		//此方法可以获得当前是get还是post的请求
		//在每个servlet当中进行设置编码，post请求设置起来比较简单，但是get请求要手动转码，很麻烦
		//String method=request.getMethod();//返回："get"/"post"
		String value=null;
		
		try {
			//设置对客户端请求进行重新编码的编码；
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
