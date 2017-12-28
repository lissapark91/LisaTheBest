package kr.or.nextit.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class ProfileInterceptor implements HandlerInterceptor{

	//controller로 요청 가기전에 실행
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println("ProfileInterceptor.preHandle() 실행 : " + handler.getClass().getName());
		request.setAttribute("interceptor.startTime", System.currentTimeMillis());	
		
		return true; //false : 그 다음 작업을 안함.
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView mav) throws Exception {
		
		
	}

	//controller 생성 끝날떄 (view까지 끝난 후)
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e)
			throws Exception {
		//컨트롤러 수행 시간 체크, 로깅 처리
		
		Logger logger = LoggerFactory.getLogger(handler.getClass()); //Logger => slf4j (interface)를 사용. 후에 확장성을 위해서
		
		Long startTime = (Long)request.getAttribute("interceptor.startTime");
		long duration = System.currentTimeMillis() - startTime;
		
		logger.info(request.getRequestURI() + " : 수행시간 = " + duration);
		
		//만약에 익셉션 발생을 했다면 null이 아님
		if(e != null) {
			logger.error(e.getMessage(),e);
		}
	
	}

	
}
