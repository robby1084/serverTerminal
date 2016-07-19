package org.iti.framework.hebut.util;

import java.util.ArrayList;
import java.util.List;

public class CourseAddrUtil {

	public static final String HONGQIAO = "[一二三四五六七八九十国]%";
	
	public static final String BEICHEN = "[北辰]%";
	
	public static final String ALL = "[一二三四五六七八九十国北]%";
	
	public static String getCourseAddrIsHongqiao(){
		
		String Hongqiao = "[一二三四五六七八九十国]";
		return Hongqiao;
	}
	
	public static List<String> getCourseAddrIsBeichen(){
		
		List<String> list = new ArrayList<String>();
		list.add("北辰");
		return list;
	}
}
