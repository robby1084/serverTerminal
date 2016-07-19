package org.iti.framework.hebut.testColeection;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebService;

import org.iti.framework.hebut.jaxws.service.JaxWsServer;
import org.springframework.stereotype.Component;

@Component
//@WebService(serviceName="JaxWebService")
public class JaxWsTest {

	@Resource(name="jaxWsServer")
	private JaxWsServer jaxWsServer;
	
	@WebMethod
	public String loadUserNameByCode(String code){
		return jaxWsServer.loadUserNameByCode(code);
	}
}
