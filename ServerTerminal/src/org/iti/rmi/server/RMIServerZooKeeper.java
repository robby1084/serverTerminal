package org.iti.rmi.server;

import java.rmi.RemoteException;

import org.iti.rmi.service.HelloService;

public class RMIServerZooKeeper {

	public static void main(String[] args) throws RemoteException, InterruptedException {
		
		String host = "localhost";
		int port_1 = 2099;
		int port_2 = 3099;
		
		ServiceProvider provider_1 = new ServiceProvider();
		
		HelloService helloService_1 = new HelloServiceImpl();
		provider_1.publish(helloService_1, host, port_1);
		
		ServiceProvider provider_2 = new ServiceProvider();
		
		HelloService helloService_2 = new HelloServiceImpl();
		provider_2.publish(helloService_2, host, port_2);
		
		/*if (args.length != 2) {
			System.out.println("please using command : java Server <rmi_host> <rmi_port>");
			System.exit(-1);
		}
		
		String host = args[0];
		int port = Integer.parseInt(args[1]);
		
		ServiceProvider provider = new ServiceProvider();
		
		HelloService helloService = new HelloServiceImpl();
		provider.publish(helloService, host, port);*/
		
		Thread.sleep(Long.MAX_VALUE);
		
		
	}
}
