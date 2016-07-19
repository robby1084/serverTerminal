package org.iti.framework.hebut.jaxws.serviceImpl;

import org.iti.application.util.ReadDataUtil;
import org.iti.common.cache.CommonCache;
import org.iti.common.util.GlobeKeyBuilder;
import org.iti.framework.hebut.jaxws.service.JaxWsServer;
import org.iti.security.entity.AuthUser;
import org.iti.security.vo.AuthUserInfo;
import org.springframework.stereotype.Service;

@Service("jaxWsServer")
public class JaxWsServerImpl implements JaxWsServer{

	/**
	 * @author chenjunda
	 */
	private static final long serialVersionUID = -1942438908986112673L;

	@Override
	public String loadUserNameByCode(String code) {
		
		String key = GlobeKeyBuilder.keyBuilder(AuthUserInfo.class, code);
		AuthUserInfo userInfo = (AuthUserInfo) CommonCache.getCacheClient()
				.get(key);
    	if(userInfo==null){
    		return "NO_USER_FOUND";
    	}
    	String userName = ReadDataUtil.loadCachingEntityGeneralType(AuthUser.class, userInfo.getAuthUserId()).getUserName();
		
		return userName;
	}

	
}
