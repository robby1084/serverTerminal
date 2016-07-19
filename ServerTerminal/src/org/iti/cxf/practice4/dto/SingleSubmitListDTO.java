package org.iti.cxf.practice4.dto;

import java.io.Serializable;

public class SingleSubmitListDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3808162490586095521L;
	
	// 电话号码
	private String phone;
	// 短信内容（使用UTF-8编码）
	private String content;
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
