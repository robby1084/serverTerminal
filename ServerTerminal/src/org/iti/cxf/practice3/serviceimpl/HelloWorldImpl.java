package org.iti.cxf.practice3.serviceimpl;

import org.iti.cxf.practice3.service.HelloWorld;

public class HelloWorldImpl implements HelloWorld {

	public String sayHi(String name) {
        String msg = "Hello " + name + "!";
        return msg;
    }
}