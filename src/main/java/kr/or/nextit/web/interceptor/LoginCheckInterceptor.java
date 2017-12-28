package kr.or.nextit.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//추상 클래스를 상속받아 필요한 것만 핸들링
public class LoginCheckInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession(true); //default : 있으면 그대로 주고 , 없으면 생성해준다. true : 없으면 null
		
		if(session == null) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN); // 403 접근금지
			return false;			
		}
		if(session.getAttribute("LOGIN_USER") == null) {
			response.sendRedirect(request.getContextPath()+"/login/loginForm"); //클라이언트에게 보내는 것이기 때문에 콘택스트 패스 포함
			return false;
		}
		
		return true;
	}

}
