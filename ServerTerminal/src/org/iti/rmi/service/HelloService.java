package org.iti.rmi.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface HelloService extends Remote{

	public String sayHello(String name) throws RemoteException;
}
