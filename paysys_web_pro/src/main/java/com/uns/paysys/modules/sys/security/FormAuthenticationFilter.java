/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.uns.paysys.modules.sys.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Service;

import com.uns.paysys.common.utils.DictConstUtil;
import com.uns.paysys.common.utils.SpringContextHolder;
import com.uns.paysys.common.utils.StringUtils;
import com.uns.paysys.modules.sys.entity.User;
import com.uns.paysys.modules.sys.service.SystemService;
import com.uns.paysys.modules.sys.utils.MD5;
import com.uns.paysys.modules.sys.utils.UserUtils;

/**
 * 表单验证（包含验证码）过滤类
 * @author ThinkGem
 * @version 2014-5-19
 */
@Service
public class FormAuthenticationFilter extends org.apache.shiro.web.filter.authc.FormAuthenticationFilter {

	public static final String DEFAULT_CAPTCHA_PARAM = "validateCode";
	public static final String DEFAULT_MOBILE_PARAM = "mobileLogin";
	public static final String DEFAULT_MESSAGE_PARAM = "message";

	private String captchaParam = DEFAULT_CAPTCHA_PARAM;
	private String mobileLoginParam = DEFAULT_MOBILE_PARAM;
	private String messageParam = DEFAULT_MESSAGE_PARAM;
	private SystemService systemService;

	protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
		String username = getUsername(request);
		String password = getPassword(request);
		if (password==null){
			password = "";
		}
		boolean rememberMe = isRememberMe(request);
		String host = StringUtils.getRemoteAddr((HttpServletRequest)request);
		String captcha = getCaptcha(request);
		boolean mobile = isMobileLogin(request);
		return new UsernamePasswordToken(username, password.toCharArray(), rememberMe, host, captcha, mobile);
	}

	public String getCaptchaParam() {
		return captchaParam;
	}

	protected String getCaptcha(ServletRequest request) {
		return WebUtils.getCleanParam(request, getCaptchaParam());
	}

	public String getMobileLoginParam() {
		return mobileLoginParam;
	}
	
	protected boolean isMobileLogin(ServletRequest request) {
        return WebUtils.isTrue(request, getMobileLoginParam());
    }
	
	public String getMessageParam() {
		return messageParam;
	}
	
	/**
	 * 登录成功之后跳转URL
	 */
	public String getSuccessUrl() {
		return super.getSuccessUrl();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	protected void issueSuccessRedirect(ServletRequest request,
			ServletResponse response) throws Exception {//登录成功后跳转方法
		String clearPwd = getPassword(request);//获取明文密码 MD5加密后，存入缓存
		UserUtils.getUser().setClearPwd(new MD5().getMD5ofStr(clearPwd));
		
		WebUtils.issueRedirect(request, response, getSuccessUrl(), null, true);
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		ServletContext context = httpRequest.getSession().getServletContext();
		//登录成功后绑定当前用户 id 和 sessionId，set到容器，用于单点登录校验
		Map<String, Object> userIdSessionIdMap = (Map<String, Object>) (context.getAttribute(DictConstUtil.USERID_SESSIONID_MAP)==null?
				new HashMap<String, Object>():context.getAttribute(DictConstUtil.USERID_SESSIONID_MAP));
		String sessionId = httpRequest.getRequestedSessionId();
		userIdSessionIdMap.put(UserUtils.getUser().getId().toString(), sessionId);
		context.setAttribute(DictConstUtil.USERID_SESSIONID_MAP, userIdSessionIdMap);
		
	}

	/**
	 * 登录失败调用事件
	 */
	@Override
	protected boolean onLoginFailure(AuthenticationToken token,
			AuthenticationException e, ServletRequest request, ServletResponse response) {
		String className = e.getClass().getName(), message = "";
		if (IncorrectCredentialsException.class.getName().equals(className)){
			User user = getSystemService().getUserByLoginName(((org.apache.shiro.authc.UsernamePasswordToken) token).getUsername());
			Long time = user.getPwdErrTimes() == null? 0 :user.getPwdErrTimes();
			if(time+1 == 6){
				message = "密码错误, 用户已被限制登录.";
				user.setLoginFlag(DictConstUtil.LOGIN_FLAG_0);
				user.setUpdateDate(new Date());
			}else{
				user.setPwdErrTimes(time+1);
				message = "密码错误, 请重试.(已错误"+(time+1)+"次, 错误6次用户将被限制登录)";
			}
			systemService.updateUserInfo(user);
		} else if(UnknownAccountException.class.getName().equals(className)){
			message = "用户不存在, 请重试.";
		}else if (e.getMessage() != null && StringUtils.startsWith(e.getMessage(), "msg:")){
			message = StringUtils.replace(e.getMessage(), "msg:", "");
		} else{
			message = "系统出现点问题，请稍后再试！";
			e.printStackTrace(); // 输出到控制台
		}
        request.setAttribute(getFailureKeyAttribute(), className);
        request.setAttribute(getMessageParam(), message);
        return true;
	}
	
	/**
	 * 获取系统业务对象
	 */
	public SystemService getSystemService() {
		if (systemService == null){
			systemService = SpringContextHolder.getBean(SystemService.class);
		}
		return systemService;
	}
	
}