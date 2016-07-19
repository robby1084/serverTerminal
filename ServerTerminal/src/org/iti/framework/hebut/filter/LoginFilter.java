package org.iti.framework.hebut.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.iti.application.param.SystemRuntime;
import org.iti.common.util.PropertiesLoader;
public class LoginFilter implements Filter {
    public String path;
    public String sessionKey;
	@SuppressWarnings("unused")
	private final String sessionPermissionKey = PropertiesLoader
			.getPropertiesValue(SystemRuntime.SESSION_AUTH_PERMISSION,
					SystemRuntime.COMMON_CACHE_HANDLER);

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String filterPath = httpRequest.getServletPath();

		if (checkPermission(filterPath, httpRequest.getSession(), httpResponse)) {
			filterChain.doFilter(request, response);
		} else {
			httpResponse.sendRedirect(path
					+ "/index.html");
		}
	
	}
    private boolean checkPermission(String filterPath, HttpSession session,HttpServletResponse HttpServletResponse){
    	sessionKey=(String) session.getAttribute("sessionKey");
    	if(sessionKey==null||sessionKey.trim().equals("")){
    		return false;
    	}
    	return true;
    	/*AuthUserInfo userInfo=(AuthUserInfo)session.getAttribute("currentAuthUserInfo");
    	if(userInfo==null||!userInfo.getState().equals(EntityState.NORMAL)){
    		return false;
    	}
    	AuthUser authUser = ReadDataUtil.loadCachingEntityGeneralType(
				AuthUser.class, userInfo.getAuthUserId());
    	if (authUser == null || authUser.getUserRoleSignList() == null
				|| authUser.getUserRoleSignList().size() == 0)
			return false;
    	List<?> cachedPermissions = (List<?>) session.getAttribute(sessionPermissionKey);
    	
		return FilterUtil.checkCloseURL(cachedPermissions, filterPath);*/
    }
	@Override
	public void init(FilterConfig arg0) throws ServletException {
       		path=arg0.getServletContext().getContextPath();
	}


}
