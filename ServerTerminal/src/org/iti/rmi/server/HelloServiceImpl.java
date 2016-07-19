package org.iti.rmi.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.iti.rmi.service.HelloService;

public class HelloServiceImpl extends UnicastRemoteObject implements HelloService{

	protected HelloServiceImpl() throws RemoteException {
		super();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 7412205019915511945L;

	@Override
	public String sayHello(String name) throws RemoteException {
		return String.format("Hello %s", name);
	}

}
