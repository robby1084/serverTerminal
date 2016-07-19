package org.iti.cxf.practice4.dto;

import java.io.Serializable;
import java.util.List;

public class BatchsubmitDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9205126903100575846L;
	
	// <desttermid>目的号码,用户手机号码，每个号码单元由usermsgid与手机号码组成，中间通过半角冒号分隔，多个号码单元用半角逗号分隔，如：usermsgid1:138********,usermsgid2:138********，最多一次100个号码，usermsgid为12位由数字和英文字母组成的字符串，在发送应答中通过usermsgid回抄</desttermid>
	private List<String> phones;
	// <msgcontent>base64(utf-8)</msgcontent>
	private String msgcontent;

	public List<String> getPhones() {
		return phones;
	}

	public void setPhones(List<String> phones) {
		this.phones = phones;
	}

	public String getMsgcontent() {
		return msgcontent;
	}

	public void setMsgcontent(String msgcontent) {
		this.msgcontent = msgcontent;
	}

}
