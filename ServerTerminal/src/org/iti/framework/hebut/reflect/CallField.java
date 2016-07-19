package org.iti.framework.hebut.reflect;

import java.lang.reflect.Field;

import org.iti.framework.hebut.entity.City;
import org.iti.framework.hebut.entity.Province;

public class CallField {

	public static void main(String[] args) throws ClassNotFoundException {
		
		Class<?> cityClass = Class.forName(City.class.getName());
		/*Field[] fields = cityClass.getFields();
		for(Field f : fields){
			System.out.println(f);
		}*/
		
		Field[] dfs = cityClass.getDeclaredFields();
		for(Field df : dfs){
			//System.out.println(df);
			System.out.println(df.getName());
			//System.out.println(df.getGenericType());
			if(df.getType().getSimpleName().equals("String")){
				System.out.println(true);
			}
			//System.out.println(df.getType().getSimpleName());
		}
	}
	
}
