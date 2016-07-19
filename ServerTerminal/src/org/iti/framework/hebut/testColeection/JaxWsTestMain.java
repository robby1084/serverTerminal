package org.iti.framework.hebut.testColeection;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.iti.framework.hebut.jaxws.service.JaxWsServer;
import org.springframework.beans.factory.annotation.Autowired;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class JaxWsTestMain {

	@Autowired
	public static JaxWsServer jaxWsServer;
	
	public static String loadUserNameByCode(String code){
		return jaxWsServer.loadUserNameByCode(code);
	}
	
	public static void main(String[] args) throws IOException {
		String str = "55Sw5Lyf5ZCM5a2m5oKo5aW977yB5oiq6Iez5YiwMjAxNeWtpuW5tOesrDHlrabmnJ/vvIzmgqjo&#13vr7liLDnuqLoibLpooTorabvvIzmnInovoPlpKfnlZnnuqfpo47pmanvvIzluIzmnJvnq6/mraPl&#13rabkuaDmgIHluqbvvIzliqrlipvlrabkuaDvvIzku6XpobrliKnlrozmiJDlrabkuJrvvIHjgJDm&#13srPljJflt6XkuJrlpKflrabjgJE=";
		System.out.println(deCodeContentFromBASE64(str));
	
		String content = "田伟同学您好！截至到2015学年第1学期，您达到红色预警，有较大留级风险，希望端正学习态度，努力学习，以顺利完成学业！";
		String encode_content = URLEncoder.encode(content, "UTF-8");
		System.out.println(convertContentToBase64(encode_content));
	} 
	
	public static String deCodeContentFromBASE64(String str) throws IOException{
		return new String(new BASE64Decoder().decodeBuffer(str));
	}
	
	public static String convertContentToBase64(String content)
			throws UnsupportedEncodingException {
		return new BASE64Encoder().encode(content.getBytes());
	}
}
