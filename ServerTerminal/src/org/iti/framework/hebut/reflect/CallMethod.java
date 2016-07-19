package org.iti.framework.hebut.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CallMethod {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Class reflectTestClass = Class.forName(ReflectTestClass.class.getName());
		
		/*** 创建实例 ***/
		// 使用Class对象的newInstance方法创建一个实例，这种方法默认构造方法创建对象
		ReflectTestClass objectA = (ReflectTestClass) reflectTestClass.newInstance();
		System.out.println("Class的newInstance()方法创建默认TestClass实例:"+objectA.toString());
		
		// 使用带参数构造方法创建实例。
		Constructor[] cons = reflectTestClass.getDeclaredConstructors();
		System.out.println("testClass有"+cons.length+"个构造方法");
		
		Constructor con = null;
		for(int i=0;i<cons.length;i++){
			con = cons[i];
			if(con.getParameterTypes().length==0){
				// 调用Constructor的newInstance()方法创建实例
				objectA = (ReflectTestClass) con.newInstance(null);
				System.out.println("Constructor的newInstance()方法创建默认实例:"+objectA.toString());
			}else {
				// 带参数的构造方法
				objectA = (ReflectTestClass) con.newInstance(new Object[]{new Integer(55), new Integer(88)});
				System.out.println("Constructor的newInstance()方法创建带参数的实例:"+objectA.toString());
			}
			
		}
		
		// 获取方法
		Method[] methods = reflectTestClass.getMethods();
		
		// 获取某个特点的无参数的方法
		Method saddMethod1 = reflectTestClass.getMethod("sadd", null);
		Method addMethod1 = reflectTestClass.getMethod("add", null);
		
		// 获取某个特定的有参数的方法
		Method saddMethod2 = reflectTestClass.getMethod("sadd", new Class[]{int.class,int.class});
		Method addMethod2 = reflectTestClass.getMethod("add", new Class[]{int.class,int.class});
		
		/*** 调用静态方法 ***/
		// 调用不带参数的静态方法
		int result = ((Integer) saddMethod1.invoke(null, null)).intValue();
		System.out.println("调用不带参数的静态方法sadd:"+result);
		// 调用带参数的静态方法
		result = ((Integer) saddMethod2.invoke(null, new Object[]{new Integer(30),new Integer(70)})).intValue();
		System.out.println("调用带参数30,70的静态方法sadd:"+result);
		
		/*** 调用实例方法 ***/
		objectA = (ReflectTestClass) reflectTestClass.newInstance();
		// 调用不带参数的实例方法
		result = ((Integer) addMethod1.invoke(objectA, null)).intValue();
		System.out.println("调用不带参数的实例方法add:"+result);
		result = ((Integer) addMethod2.invoke(objectA, new Object[]{new Integer(130), new Integer(170)})).intValue();
		System.out.println("调用带参数130,170的实例方法sadd:"+result);
	}
}
