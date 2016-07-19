package org.iti.spring.schemas;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StudentXSDTest {

	public static void main(String[] args) {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("ApplicationContext.xml");    
        Student student1 = (Student) ctx.getBean("student1");    
        Student student2 = (Student) ctx.getBean("student2");    
        System.out.println("name: " +student1.getName()+" age :" + student1.getAge());    
        System.out.println("name: " +student2.getName()+" age :" + student2.getAge());
	}
}
