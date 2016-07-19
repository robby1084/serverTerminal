package org.iti.cxf.practice4.client;

import java.net.URLEncoder;

import org.iti.application.context.bean.factory.BeanFactory;
import org.iti.common.util.JsonUtil;
import org.iti.cxf.practice4.server.ICXFMobileSMSService;
import org.iti.http.interfaces.abstracts.action.AbstractHttpInterfaceAction;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller("CXFClientAction")
@Scope("prototype")
public class CXFClientAction extends AbstractHttpInterfaceAction {

	/**
	 * CXF客户端测试
	 */
	private static final long serialVersionUID = -5119258956283279873L;

	private String phone = "18892273099";
	private String singleShotContent = "CXF测试单发短信";
	private String singleShotListContent = "CXF测试单发多条短信";
	private String batchShotContent = "CXF测试群发短信";

	@Override
	public String defaultExecute() throws Throwable {

		String state_1 = getICXFMobileSMSService().singleShotSMS(phone,
				URLEncoder.encode(singleShotContent, "utf-8"));
		String state_2 = getICXFMobileSMSService().singleShotSMS(phone,
				URLEncoder.encode(singleShotListContent, "utf-8"));
		String state_3 = getICXFMobileSMSService().singleShotSMS(phone,
				URLEncoder.encode(batchShotContent, "utf-8"));
		this.responResult = JsonUtil.toJson(new StringBuilder().append(state_1)
				.append(state_2).append(state_3).toString());
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

	private ICXFMobileSMSService getICXFMobileSMSService() {
		return (ICXFMobileSMSService) BeanFactory.getBean("cxfClient");
	}
}
