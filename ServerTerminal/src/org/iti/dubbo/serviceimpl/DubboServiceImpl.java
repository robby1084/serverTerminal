package org.iti.dubbo.serviceimpl;

import org.iti.dubbo.service.DubboService;

public class DubboServiceImpl implements DubboService{

	@Override
	public String sayHello(String name) {

		return new StringBuilder("Hello Dubbo.This is server 2,Hello ").append(name).toString();
	}

}
