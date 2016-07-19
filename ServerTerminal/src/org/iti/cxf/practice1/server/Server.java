package org.iti.cxf.practice1.server;

import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.iti.cxf.practice1.serviceimpl.ComplexUserService;

public class Server {

	public static void main(String[] args) throws InterruptedException {
		
		JaxWsServerFactoryBean factory = new JaxWsServerFactoryBean();
		factory.setServiceClass(ComplexUserService.class);
		factory.setAddress("http://localhost:9000/ws/HelloWorld");
		
		factory.create();
		System.out.println("Server start...");  
        Thread.sleep(60 * 1000);  
        System.out.println("Server exit...");  
        System.exit(0);
	}
}
