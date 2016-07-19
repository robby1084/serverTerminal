package org.iti.rmi.service;

public interface Constant {

	String ZK_CONNECT_STRING = "10.1.40.88:2181";
	int ZK_SESSION_TIMEOUT = 5000;
	String ZK_REGISTRY_PATH = "/registry";
	String ZK_PROVIDER_PATH = ZK_REGISTRY_PATH + "/provider";
}
