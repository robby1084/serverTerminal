package org.iti.cxf.practice2.serviceimpl;

import javax.jws.WebService;
import org.iti.cxf.practice2.service.HelloWorld;

@WebService(serviceName="hello", endpointInterface="org.iti.cxf.practice2.service.HelloWorld")
public class HelloWorldImpl implements HelloWorld{

	 public HelloWorldImpl()
	    {
	        System.out.println("-- init HelloWorldImpl --");
	    }
	    
	    public String sayHi(String text)
	    {
	        System.out.println("in sayHi, text : " + text);
	        return "hi " + text + ".";
	    }
}





    
   


