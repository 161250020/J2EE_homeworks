package TEST;

import java.io.UnsupportedEncodingException;

public class test {
public static void main(String[] args) {
	//String value="%E9%80%80%E5%87%BA%E7%99%BB%E5%BD%95";
	String value="你好";
	//如果字符串的编码是"GB2312"
	/*try {
		if(value.equals(new String(value.getBytes("GB2312"),"GB2312"))) {
			System.out.println("GB2312："+value);
			value=new String(value.getBytes("GB2312"),"utf-8");
		}
		//如果字符串的编码是"iso-8859-1"
		else if(value.equals(new String(value.getBytes("iso-8859-1"),"iso-8859-1"))) {
			System.out.println("iso-8859-1: "+value);
			value=new String(value.getBytes("iso-8859-1"),"utf-8");
		}
		//如果字符串的编码是"GBK"
		else if(value.equals(new String(value.getBytes("GBK"),"GBK"))) {
			System.out.println("GBK: "+value);
			value=new String(value.getBytes("GBK"),"utf-8");
		}
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}*/
	try {
		value=new String(value.getBytes(),"GB2312");
		System.out.println("GB2312："+value);

		value=new String(value.getBytes(),"iso-8859-1");
		System.out.println("iso-8859-1: "+value);
		
		value=new String(value.getBytes(),"GBK");
		System.out.println("GBK: "+value);
		
		value=new String(value.getBytes(),"UTF-8");
		System.out.println("UTF-8: "+value);
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	//还有一种可能：原本的编码就是utf-8；
	System.out.println(value);
}
}
