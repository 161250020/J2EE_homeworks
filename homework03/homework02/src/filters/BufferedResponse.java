package filters;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class BufferedResponse extends HttpServletResponseWrapper{
	public static final short OT_NONE = 0, OT_WRITER = 1, OT_OUTPUT_STREAM = 2;
	private short outputType = OT_NONE;
	private PrintWriter writer = null;
	private BufServletOutputStream out = null;
	private String contentType;


	public BufferedResponse(HttpServletResponse response){ super(response); }

	/*
	 * 获取response对象，并将输出存放在自定义的流里面，
	 * 那么关于输出流（outputStream、writer）的操作都是需要重写的了
	 * 然后加入新增的获取输出数据的方法就ok了
	 */
	//以流的方式获取输出
	//返回一般为打印流，其底层是对ServletOutputStream引用
	public ServletOutputStream getOutputStream() throws IOException {
		//返回OutputStream之前检查outputType实例变量，增强了限制
		if (outputType == OT_WRITER) {
			throw new IllegalStateException();
		} else if (outputType == OT_OUTPUT_STREAM) {
			return out;
		} else {
			out = new BufServletOutputStream();
			outputType = OT_OUTPUT_STREAM;
			return (ServletOutputStream)out;
		}

	}
	//刷新流
	public void flushBuffer()throws IOException {
		if (outputType == OT_WRITER) {
			writer.flush();
		} else if (outputType == OT_OUTPUT_STREAM) {
			out.flush();
		}
	}
	//重置流
	public void reset() {
		resetBuffer();
		outputType = OT_NONE;
		super.reset();
	}
	
	public PrintWriter getWriter() throws IOException {
		//返回PrintWriter之前检查outputType实例变量，增强了限制
		System.out.println("getWriter() "+outputType);
		if (outputType == OT_OUTPUT_STREAM) {
			throw new IllegalStateException();
		} else if (outputType == OT_WRITER) {
			return writer;
		} else {
			writer = new PrintWriter( new OutputStreamWriter(getOutputStream(),getCharacterEncoding()));
			outputType = OT_WRITER;
			System.out.println("getWriter() "+outputType);
			return writer;
		}
	}

	public short getOutputType() {
		return outputType;
	}
	
	public String getContentType() {
		return contentType;
	}
	
	public void setContentType(String contentType) {
		this.contentType = contentType;
		super.setContentType(contentType);
	}
	
	public void resetBuffer() {
		if(null != out) {
			out.reset();
		}
	}
	
	public byte[] toByteArray() throws IOException {
		flushBuffer();
		if(null != out) {
			return(out.toByteArray());
		} else {
			return null;
		}
	}
}