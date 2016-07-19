package org.iti.cxf.practice1.serviceimpl;

import java.util.Date;

import org.iti.cxf.practice1.dto.User;
import org.iti.cxf.practice1.service.IComplexUserService;

public class ComplexUserService implements IComplexUserService{


	@Override
	public User getUserByName(String name) {
		User user = new User();
        user.setId(new Date().getTime());
        user.setName(name);
        user.setAddress("china");
        user.setEmail(name + "@hoo.com");
        return user;
	}

	@Override
	public void setUser(User user) {
		 System.out.println("############Server setUser###########");
	     System.out.println("setUser:" + user);
	}

}
