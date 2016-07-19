package org.iti.rmi.server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class RmiServerJNDI {

	public static void main(String[] args) throws RemoteException, MalformedURLException {
		
		int port = 2099;
		String url = "rmi://localhost:2099/org.iti.rmi.server.HelloServiceImpl";
		LocateRegistry.createRegistry(port);
		Naming.rebind(url, new HelloServiceImpl());
	}
}
