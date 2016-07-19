package org.iti.framework.hebut.reflect;

public class ReflectTestClass {

	// 两个静态属性
	public static int sa = 100;
	public static int sb = 50;
	
	// 两个实例属性
	public int a;
	public int b;
	
	// 默认构造方法
	public ReflectTestClass(){
		this.a = 5;
		this.b = 10;
	}
	
	// 带参数的构造方法
	public ReflectTestClass(int a, int b){
		this.a = a;
		this.b = b;
	}
	
	// 静态方法，实现add功能
	public static int sadd(){
		return sa+sb;
	}
	
	public static int sadd(int a,int b){
		return a+b;
	}
	
	// 实例方法，实现add功能
	public int add(){
		return this.a + this.b;
	}
	
	public int add(int a,int b){
		return a+b;
	}
	
	public String toString(){
		return "a="+this.a+";b="+this.b;
	}
	
	// 私有方法
	private int sub(){
		return this.a-this.b;
	}
	
}
