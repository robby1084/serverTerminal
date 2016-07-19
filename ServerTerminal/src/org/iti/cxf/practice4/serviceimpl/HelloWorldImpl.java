package org.iti.cxf.practice4.serviceimpl;

import java.util.Date;

import org.iti.cxf.practice4.dto.Customer;
import org.iti.cxf.practice4.service.HelloWorld;

public class HelloWorldImpl implements HelloWorld {

    public String sayHi(String name) {
        String msg = "Hello " + name + "!";
        return msg;
    }

	@Override
	public Customer findCustomer(String id) {
		Customer c = new Customer();
		c.setBirthday(new Date());
		c.setId("01");
		c.setName("陈俊达");
		if("01".equals(id)){
			return c;
		}
		return null;
	}
}