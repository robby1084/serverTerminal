package org.iti.ftpserver.client;

import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.iti.common.util.JsonUtil;
import org.iti.ftpserver.utils.ReadFTPFile;
import org.iti.ftpserver.utils.FTPUtil;
import org.iti.ftpserver.utils.WriteFTPFile;
import org.iti.http.interfaces.abstracts.action.AbstractHttpInterfaceAction;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller("FTPMain")
@Scope("prototype")
public class FTPMain extends AbstractHttpInterfaceAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2339032499091183680L;
	
	private static Logger logger = Logger.getLogger(FTPMain.class);

	@Override
	public String defaultExecute() throws Throwable {

		int ftpPort = 21;
		String ftpUserName = "admin";
		String ftpPassword = "admin";
		String ftpHost = "10.1.40.78";
		String ftpPath = "/home/apache-ftpserver-1.0.5/res/home";
		String writeTempFielPath = "E:\\example.txt";
		try {
			/*
			 * InputStream in = FTPUtil.class.getClassLoader()
			 * .getResourceAsStream("env.properties");
			 */

			//Properties properties = new Properties();
			//properties.load(in);
			//ftpUserName = properties.getProperty("ftpUserName");
			//ftpPassword = properties.getProperty("ftpPassword");
			//ftpHost = properties.getProperty("ftpHost");
			//ftpPort = Integer.valueOf(properties.getProperty("ftpPort"))
			//		.intValue();
			//ftpPath = properties.getProperty("ftpPath");
			//writeTempFielPath = properties.getProperty("writeTempFielPath");

			/*WriteFTPFile write = new WriteFTPFile();
			ftpPath = ftpPath + "/" + "huawei_220.248.192.200_new1.cfg";
			write.upload(ftpPath, ftpUserName, ftpPassword, ftpHost, ftpPort,
					result, writeTempFielPath);*/
			
			ReadFTPFile read = new ReadFTPFile();
			String result = read.readConfigFileForFTP(ftpUserName, ftpPassword,
					ftpPath, ftpHost, ftpPort, "README.txt");
			System.out.println("读取配置文件结果为：" + result);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return super.defaultExecute();
	}
	
	@Override
	public String getResponState() {
		return responState;
	}

	@Override
	public String getResponResult() {
		return responResult;
	}
}
