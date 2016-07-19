package org.iti.framework.hebut.filter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.types.ObjectId;
import org.iti.application.param.SystemRuntime;
import org.iti.application.util.ReadDataUtil;
import org.iti.common.cache.CommonCache;
import org.iti.common.util.PropertiesLoader;
import org.iti.common.util.StringConvert;
import org.iti.common.util.TreeScan;
import org.iti.common.util.TreeScan.TreeNode;
import org.iti.security.entity.AuthResource;
import org.iti.security.entity.AuthResource.ResourceType;
import org.iti.security.vo.AuthUserInfo;
import org.springframework.util.AntPathMatcher;

public class FilterUtil implements Serializable {

	@SuppressWarnings("unused")
	private static final String DEFAULT_INDEX = "/index.*";
	/**
	 * 
	 */
	private static final long serialVersionUID = -3498387935506390025L;
	private static final Log log = LogFactory.getLog(FilterUtil.class);

	public static volatile BlockingDeque<String> OPEN_URLS;

	public static final Boolean checkOpenURL(String requestUrl) {
		initOpenURLs();
		AntPathMatcher urlMatcher = new AntPathMatcher();
		log.debug("OPEN_URL[" + OPEN_URLS + "]");
		for (String openURL : OPEN_URLS) {
			if (urlMatcher.match(
					new StringBuilder(openURL)
							.append(openURL.endsWith("/") ? "" : "/")
							.append("**").toString(), requestUrl))
				return true;
			if(urlMatcher.match(openURL, requestUrl))
				return true;
		}
		return false;
	}

	public static final Boolean checkCloseURL(List<?> permissions,
			String requestUrl) {
		AuthResource self = ReadDataUtil.getCurrentApp();
	//	List<AuthResource> list=new ArrayList<AuthResource>();
		List<ObjectId> currentList=self.getChildren();
		//AntPathMatcher urlMatcher = new AntPathMatcher();
		for (Object o : permissions) {
			AuthResource resource = (AuthResource) o;
			for(ObjectId id:currentList){
				if(id.equals(resource.getId())){
					return true;
				}
			}
			/*	ObjectId currentTestResourceTopId = resource.getTopId() == null ? resource
					.getId() : resource.getTopId();
				
	if (!currentTestResourceTopId.equals(self.getId()))
				continue;
			if (urlMatcher.match(DEFAULT_INDEX, requestUrl))
				return true;
			if (urlMatcher.match(buildMatchStr(requestUrl), resource.getUrl())) {
				log.debug("�����û�����URL[" + requestUrl + "]");
				return true;
			}
			if (urlMatcher.match(
					new StringBuilder().append("/")
							.append(SystemRuntime.PATH_MATCHER_ANY_WORD)
							.append(requestUrl.startsWith("/") ? "" : "/")
							.append(requestUrl).toString(), resource.getUrl())) {
				log.debug("�����û�����URL[" + requestUrl + "]");
				return true;
			}
			if(urlMatcher.match(resource.getUrl(), requestUrl)){
				log.debug("�����û�����URL[" + requestUrl + "]");
				return true;
			}*/
		}
		return false;
	}
	
	public static final Boolean checkHomeGate(List<?> permissions) {
		String appStaticParam = PropertiesLoader.getPropertiesValue(
				SystemRuntime.WEBSITE, SystemRuntime.SYSTEM_STATIC_PARAMS)
				.trim();
		for (Object o : permissions) {
			AuthResource resource = (AuthResource) o;
			if (resource.getType().equals(ResourceType.WEBSITE)
					&& resource.getUrl().trim().equals(appStaticParam)) {
				log.debug("�����û�����URL[HomeGate->AppURL[" + appStaticParam + "]]");
				return true;
			}
		}
		return false;
	}

	@SuppressWarnings("unused")
	private static final String buildMatchStr(String url) {
		String matcher = new StringBuilder(SystemRuntime.PATH_MATCHER_ANY_WORD)
				.append(url.startsWith("/") ? "" : "/").append(url).toString();
		return matcher;
	}

	private static final void initOpenURLs() {
		if (OPEN_URLS == null) {
			synchronized (SystemRuntime.class) {
				if (OPEN_URLS == null) {
					initOpenURLsCore();
				}
			}
		}
	}

	private static void initOpenURLsCore() {
		OPEN_URLS = new LinkedBlockingDeque<String>();
		String openUrlStr = PropertiesLoader.getPropertiesValue(
				SystemRuntime.OPEN_URL, SystemRuntime.SYSTEM_STATIC_PARAMS);
		List<String> openURLs = parserEnvForOpenURLs(openUrlStr);
		OPEN_URLS.addAll(openURLs);
	}

	private static List<String> parserEnvForOpenURLs(String openUrlStr) {
		List<String> openURLs = TreeScan.treeScanDeep(openUrlStr,
				new TreeNode<String>() {

					@Override
					public List<String> getChildren(String node) {
						List<String> children = new ArrayList<String>();
						if (node == null || node.trim().equals(""))
							return children;
						Boolean refNode = Boolean.FALSE;
						if (node.trim().startsWith(SystemRuntime.ENV_REF_PRE)
								&& !node.trim().contains(
										SystemRuntime.ENV_SEPARATOR)) {
							refNode = Boolean.TRUE;
							node = PropertiesLoader
									.getPropertiesValue(
											node.replaceFirst(
													StringConvert
															.convertWord2Regex(SystemRuntime.ENV_REF_PRE),
													"").trim(),
											SystemRuntime.SYSTEM_STATIC_PARAMS)
									.trim();
						}
						String[] openURLArray = null;
						if (node.contains(SystemRuntime.ENV_SEPARATOR))
							openURLArray = node
									.split(SystemRuntime.ENV_SEPARATOR);
						if (refNode
								&& !node.contains(SystemRuntime.ENV_SEPARATOR)) {
							children.add(node);
						}
						if (openURLArray != null && openURLArray.length > 1
								&& !refNode)
							children.addAll(Arrays.asList(openURLArray));
						return children;
					}

					@Override
					public void setChildren(String node, List<String> children) {
					}

					@Override
					public String clone(String node) {
						return null;
					}
				});
		return openURLs;
	}
	public static final String getUserOnlineInfoCacheHandler(
			HttpServletRequest httpRequest, HttpSession session,String sessionUserInfoKey,String sessionPermissionKey) {
		try {
			String userOnlineInfoCacheHandler = (String) session
					.getAttribute(sessionUserInfoKey);
			String userOnlineInfoCacheHandlerParameter = httpRequest
					.getParameter(sessionUserInfoKey);
			if (isUserOnlineInfoCacheHandlerEmpty(userOnlineInfoCacheHandler)) {
				userOnlineInfoCacheHandler = userOnlineInfoCacheHandlerParameter;
			} else {
				if (!isUserOnlineInfoCacheHandlerEmpty(userOnlineInfoCacheHandlerParameter)
						&& !userOnlineInfoCacheHandler.trim().equals(
								userOnlineInfoCacheHandlerParameter)) {
					userOnlineInfoCacheHandler = userOnlineInfoCacheHandlerParameter;
					session.removeAttribute(sessionUserInfoKey);
					session.removeAttribute(sessionPermissionKey);
				}
			}
			AuthUserInfo userInfo = (AuthUserInfo) CommonCache.getCacheClient()
					.get(userOnlineInfoCacheHandler);
			if (userInfo == null) {
				userOnlineInfoCacheHandler = userOnlineInfoCacheHandlerParameter;
				userInfo = (AuthUserInfo) CommonCache.getCacheClient().get(
						userOnlineInfoCacheHandler);
				if (userInfo == null)
					return null;
			}
			session.setAttribute(sessionUserInfoKey, userOnlineInfoCacheHandler);
			return userOnlineInfoCacheHandler;
		} catch (Throwable e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			return null;
		}
	}

	private static final boolean isUserOnlineInfoCacheHandlerEmpty(
			String userOnlineInfoCacheHandler) {
		return userOnlineInfoCacheHandler == null
				|| userOnlineInfoCacheHandler.trim().equals("");
	}
}
