package org.iti.framework.hebut.jaxws.service;

import java.io.Serializable;


public interface JaxWsServer extends Serializable{

	public String loadUserNameByCode(String code);
}
