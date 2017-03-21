package com.uns.paysys.modules.sys.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.uns.paysys.common.service.BaseService;
import com.uns.paysys.modules.sys.entity.User;
import com.uns.paysys.modules.sys.utils.UserUtils;

public class PwdInterceptor extends BaseService implements HandlerInterceptor {
	
	@Value("${adminPath}")
	protected String adminPath;

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		

		User user = UserUtils.getUser();
		if(user.getId() != null){//只进行登录后拦截
			user = UserUtils.getDBUser(user.getId());//直接到数据库查询用户对象，避免缓存数据影响
			String url = request.getRequestURI();
			if(url.indexOf("modifyPwd") < 0){//不拦截修改密码方法
				if(user.getUpdatePwdTime() == null 
						|| new Date().getTime()-user.getUpdatePwdTime().getTime() > 90L*24L*60L*60L*1000L
						|| user.getLoginDate() == null){//上次修改密码时间在90天之前的、首次登录的、未修改过密码的  强制修改密码
					String requestContextPath = request.getContextPath();
					response.sendRedirect(requestContextPath+adminPath+"/sys/user/modifyPwd");
					return false;
				}
			}
		}

		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
	}

}
