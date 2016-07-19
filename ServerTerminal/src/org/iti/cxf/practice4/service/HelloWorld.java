package org.iti.cxf.practice4.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.iti.cxf.practice4.dto.Customer;

@WebService
public interface HelloWorld {

	@WebMethod
    @WebResult String sayHi(@WebParam String text);
	
	@WebMethod
    @WebResult Customer findCustomer(@WebParam String id);
}
