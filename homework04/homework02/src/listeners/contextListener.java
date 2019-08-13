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
	String countVisitorsPath="F:\\ѧϰ����\\��3\\��2ѧ��\\J2EE���м��\\��ҵ\\С��ҵ��\\��ҵ3\\submit\\homework03\\WebContent\\visitorIn.txt";
	String countUsersPath="F:\\ѧϰ����\\��3\\��2ѧ��\\J2EE���м��\\��ҵ\\С��ҵ��\\��ҵ3\\submit\\homework03\\WebContent\\userIn.txt";
	
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
    	
    	//��attribute��nameΪcountVisitors��countUsers���µ�ʱ�򣬲�����
    	String attributeName=scae.getName();
    	if(attributeName.equals("countVisitors")
    			||attributeName.equals("countUsers")) {
        	writeCounter(scae, attributeName);
    	}
    }

    //�޸�����count�ļ�
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
    	String counterFilePath="F:\\ѧϰ����\\��3\\��2ѧ��\\J2EE���м��\\��ҵ\\С��ҵ��\\��ҵ3\\submit\\homework03\\WebContent\\"+counterFile;
    	
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
    	
    	//һ��ʼ��ʱ�����е����ߵ��û������ο���������ʼ��Ϊ0
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
    	
    	//���countVisitors��countUsers��context
    	ServletContext servletContext=sce.getServletContext();
    	servletContext.setAttribute("countVisitors", 0);
    	servletContext.setAttribute("countUsers", 0);
    	
    	System.out.println("---Application initialized---");
    }
	
}
