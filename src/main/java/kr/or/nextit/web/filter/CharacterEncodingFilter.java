package kr.or.nextit.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter implements Filter{
	
	private String encoding = "";
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		// 필터 초기화 설정
		encoding = config.getInitParameter("encoding");
		if(encoding == null) {
			encoding = "utf-8";
		}
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		// 1. 실제 로직, 필터 작업 (전처리)
		request.setCharacterEncoding(encoding);
		
		// 다음으로 넘김
		// 2. 실제 기능
		chain.doFilter(request, response);
		
		// 3. 후처리 구간
		
		// 필터는 나누기 애매하지만 인터셉터는 전처리, 실제기능, 후처리가 나뉘어 있다.
	}
	
	@Override
	public void destroy() {
		// 서블릿에서 해당 필터객체를 없앨 때 <자원해제> (특이사항 없으면 안부른다.)
		
	}
}
