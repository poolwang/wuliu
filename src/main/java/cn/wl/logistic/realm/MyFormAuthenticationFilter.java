package cn.wl.logistic.realm;

import javax.servlet.ServletRequest;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import cn.wl.logistic.pojo.User;


public class MyFormAuthenticationFilter extends FormAuthenticationFilter {
   //验证码核对
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		HttpServletRequest req=(HttpServletRequest) request;
		String verifyCode = req.getParameter("verifyCode");
		String rand = (String) req.getSession().getAttribute("rand");
		if (StringUtils.isNotBlank(verifyCode)) {
			if (!rand.toLowerCase().equals(verifyCode)) {
				request.setAttribute("shiroLoginFailure", "verifyCodeError");
				return true;
			}
		}
		return super.onAccessDenied(request, response);
	}
	
	
	@Override
		protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
				ServletResponse response) throws Exception {
		//清除shiro 在sesion存储的上一次访问地址
		 Session session = subject.getSession(false);
		 if (session !=null) {
			session.removeAttribute(WebUtils.SAVED_REQUEST_KEY);
		}
			return super.onLoginSuccess(token, subject, request, response);
		}
	
	//rememberMe设置
	 @Override
		protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
			 Subject subject = getSubject(request, response);
			 Session session = subject.getSession();
			 if (!subject.isAuthenticated() && subject.isRemembered()) {
				User principal = (User) subject.getPrincipal();
				session.setAttribute("user", principal);
			}
			return subject.isAuthenticated() || subject.isRemembered();
		}
}
