package org.iti.framework.hebut.action.abstracts;

import java.util.List;

import org.iti.common.util.JsonUtil;
import org.iti.framework.hebut.datatable.DataMap;

import com.google.gson.reflect.TypeToken;

/**
 * 所有需要是使用datatables的表单(真分页)均需要继承此抽象类
 */
public abstract class ActionForDataTables extends AbstractHebutHttpInterfaceAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2665882139997081450L;
	
	/**
	 * 查询结果的全部记录数量
	 */
	protected Long iTotalRecords;
	/**
	 * 查询结果的全部记录数量
	 */
	protected Long iTotalDisplayRecords;
	
	/**
	 * 来自于界面原封不动返回即可，用于保障系统安全
	 */
	protected Integer sEcho;
	/**
	 * 界面传来的信息
	 */
	protected List<DataMap> aoData;
	protected Long iDisplayLength;
	protected Long iDisplayStart;
	
	public Integer getsEcho() {
		return sEcho;
	}
	

	public Long getiDisplayLength() {
		return iDisplayLength;
	}

	public Integer getStart(List<DataMap> aoData){
		String length = null;
		for(int i=0;i<aoData.size();i++){
			if(aoData.get(i).getName().equals("iDisplayStart"))
				length=aoData.get(i).getValue();
		}
		return Integer.parseInt(length);
	}
	public Integer getLength(List<DataMap> aoData){
		String length = null;
		for(int i=0;i<aoData.size();i++){
			if(aoData.get(i).getName().equals("iDisplayLength"))
				length=aoData.get(i).getValue();
		}
		return Integer.parseInt(length);
	}
	

	public Integer getSEcho(List<DataMap> aoData){
		String sEcho = null;
		for(int i=0;i<aoData.size();i++){
			if(aoData.get(i).getName().equals("sEcho"))
				sEcho=aoData.get(i).getValue();
		}
		return Integer.parseInt(sEcho);
	}

	public void setAoData(String aoData) {
		this.aoData = JsonUtil.toList(aoData, new TypeToken<List<DataMap>>(){});
	}

	public abstract Long getiTotalRecords();

	public abstract Long getiTotalDisplayRecords();
	
	
}
