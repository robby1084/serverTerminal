package org.iti.framework.hebut.testAction;

import javax.annotation.Resource;

import org.iti.common.util.JsonUtil;
import org.iti.framework.hebut.jaxws.service.JaxWsServer;
import org.iti.http.interfaces.abstracts.action.AbstractHttpInterfaceAction;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller("JaxWsTestAction")
@Scope("prototype")
public class JaxWsTestAction extends AbstractHttpInterfaceAction {

	/**
	 * @author chenjunda
	 */
	private static final long serialVersionUID = -7140084946061070763L;

	@Resource(name="jaxWsServer")
	private JaxWsServer jaxWsServer;
	
	@Override
	public String defaultExecute() throws Throwable {

		String name = jaxWsServer.loadUserNameByCode("102458");
		this.responResult = JsonUtil.toJson(name);
		return AbstractHttpInterfaceAction.SUCCESS;
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
