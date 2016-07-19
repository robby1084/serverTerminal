package org.iti.cxf.practice4.client;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.iti.cxf.practice4.server.ICXFMobileSMSService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringClient {
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();  
        factory.setServiceClass(ICXFMobileSMSService.class);  
        factory.setAddress("http://localhost:8080/MobileSMS/ws/mobileSMS");  
        ICXFMobileSMSService server = (ICXFMobileSMSService) factory.create();
        System.out.println(server.sayHello("陈俊达"));
        String state = server.singleShotSMS("18892273099",
				URLEncoder.encode("cxf", "utf-8"));
        System.out.println(state);
        
		/*ApplicationContext context = new ClassPathXmlApplicationContext(
				"CXFClient.xml");
		ICXFMobileSMSService server = (ICXFMobileSMSService) context
				.getBean("cxfClient");
		System.out.println(server.singleShotSMS("18892273099",
				URLEncoder.encode("CXF测试单发短信", "utf-8")));*/
	}
}
