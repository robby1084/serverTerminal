package org.iti.framework.hebut.mainClass;

import org.iti.framework.hebut.testColeection.JaxWsTestMain;

public class JaxWsMain {

	public static void main(String[] args) {
		
		String name = JaxWsTestMain.loadUserNameByCode("102458");
		System.out.println(name);
	}
}
