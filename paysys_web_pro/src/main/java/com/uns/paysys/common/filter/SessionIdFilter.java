package com.uns.paysys.common.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.AuthenticationException;

import com.uns.paysys.common.utils.DictConstUtil;
import com.uns.paysys.modules.sys.entity.User;
import com.uns.paysys.modules.sys.utils.UserUtils;

public class SessionIdFilter implements Filter {
	
	private ServletContext context;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		this.context = filterConfig.getServletContext();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		

		HttpServletRequest httpRequest = (HttpServletRequest)request;
		
		User user = UserUtils.getUser();
		if(user.getId() != null){
			//如果该账号又在别处登录
			if (anotherOneLogin(httpRequest, user)) {
				httpRequest.getSession().invalidate();
				throw new AuthenticationException("msg:账号已在其它地方登录，请重新登录。");
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}
	
 	/**
	 * 是否有人已经使用该账号登录
	 * @param request
	 * @param accountSummary
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean anotherOneLogin(HttpServletRequest request, User user) {
		boolean flag = false;
		//获取容器中的 用户id sessionid 映射 Map
		Map<String, Object> userIdSessionIdMap = (Map<String, Object>) (context.getAttribute(DictConstUtil.USERID_SESSIONID_MAP)==null?
				null:context.getAttribute(DictConstUtil.USERID_SESSIONID_MAP));
		
		if(userIdSessionIdMap != null){
			String oldSessionId = userIdSessionIdMap.get(user.getId().toString())==null?
					null:userIdSessionIdMap.get(user.getId().toString()).toString();
			if (oldSessionId != null) {
				if(!request.getRequestedSessionId().equals(oldSessionId)) {//若本次请求sessionId 与之前id不一致
					flag = true;
				}
			}
		}
		return flag;
	}

}
