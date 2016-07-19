package org.iti.framework.hebut.action.abstracts;

import org.iti.application.util.ReadDataUtil;
import org.iti.http.interfaces.abstracts.action.AbstractHttpInterfaceAction;
import org.iti.security.entity.AuthUser;
import org.iti.security.vo.AuthUserInfo;

import com.opensymphony.xwork2.ActionContext;

public abstract class AbstractHebutHttpInterfaceAction extends AbstractHttpInterfaceAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3643736436331449348L;
	public  AuthUserInfo loadCurrentUserInfo(){
    	return (AuthUserInfo)ActionContext.getContext().getSession().get("currentAuthUserInfo");
	}
	public AuthUser loadCurrentUser(){
		AuthUserInfo userInfo=loadCurrentUserInfo();
		if(userInfo==null){
			return null;
		}
		return ReadDataUtil.loadCachingEntityGeneralType(AuthUser.class,userInfo.getAuthUserId());
	}

}
