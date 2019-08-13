package listeners;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class contextListener
 *
 */
@WebListener
public class contextListener implements ServletContextListener, ServletContextAttributeListener {
	int countVisitors=0;
	int countUsers=0;
	String countVisitorsPath="F:\\学习资料\\大3\\第2学期\\J2EE与中间件\\作业\\小作业们\\作业3\\submit\\homework03\\WebContent\\visitorIn.txt";
	String countUsersPath="F:\\学习资料\\大3\\第2学期\\J2EE与中间件\\作业\\小作业们\\作业3\\submit\\homework03\\WebContent\\userIn.txt";
	
    /**
     * Default constructor. 
     */
    public contextListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextAttributeListener#attributeAdded(ServletContextAttributeEvent)
     */
    public void attributeAdded(ServletContextAttributeEvent scae)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextAttributeListener#attributeRemoved(ServletContextAttributeEvent)
     */
    public void attributeRemoved(ServletContextAttributeEvent scae)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextAttributeListener#attributeReplaced(ServletContextAttributeEvent)
     */
    public void attributeReplaced(ServletContextAttributeEvent scae)  { 
         // TODO Auto-generated method stub
    	
    	//当attribute的name为countVisitors或countUsers更新的时候，才运行
    	String attributeName=scae.getName();
    	if(attributeName.equals("countVisitors")
    			||attributeName.equals("countUsers")) {
        	writeCounter(scae, attributeName);
    	}
    }

    //修改两个count文件
    synchronized void writeCounter(ServletContextAttributeEvent scae, String attributeName) {
    	ServletContext servletContext= scae.getServletContext();
    	int counter = (int) servletContext.getAttribute(attributeName);
    	String counterFile="";
    	if(attributeName.equals("countVisitors")) {
    		counterFile="visitorIn.txt";
    	}
    	else {
    		counterFile="userIn.txt";
    	}
    	String counterFilePath="F:\\学习资料\\大3\\第2学期\\J2EE与中间件\\作业\\小作业们\\作业3\\submit\\homework03\\WebContent\\"+counterFile;
    	
    	try {
    		BufferedWriter writer = new BufferedWriter(new FileWriter(counterFilePath));
    		writer.write(Integer.toString(counter));
    		writer.close();
    		System.out.println("Writing "+counterFile);
    	}catch (Exception e) {
    		System.out.println(e.toString());
    	}
    }
    
	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    	
    	//一开始的时候将所有的在线的用户数和游客数量都初始化为0
    	System.out.println("initialize 2 txt files --- replace to 0");
    	try {
			BufferedWriter out1=new BufferedWriter(new FileWriter(countVisitorsPath));
			BufferedWriter out2=new BufferedWriter(new FileWriter(countUsersPath));
			out1.write("0");
			out2.write("0");
			out1.close();
			out2.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	//添加countVisitors和countUsers到context
    	ServletContext servletContext=sce.getServletContext();
    	servletContext.setAttribute("countVisitors", 0);
    	servletContext.setAttribute("countUsers", 0);
    	
    	System.out.println("---Application initialized---");
    }
	
}
