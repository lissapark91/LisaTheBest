package kr.or.nextit.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieBox {

	private Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
	
	public CookieBox(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie co : cookies) {
				cookieMap.put(co.getName(), co);
			}
		}
	}
	
	
	public static Cookie createCookie(String name, String value) throws UnsupportedEncodingException {
		Cookie cookie = new Cookie(name, URLEncoder.encode(value,"utf-8"));
		return cookie;
	}
	public static Cookie createCookie(String name, String value, String path) throws UnsupportedEncodingException {
		Cookie cookie = new Cookie(name, URLEncoder.encode(value,"utf-8"));
		cookie.setPath(path);
		return cookie;
	}
	public static Cookie createCookie(String name, String value, String path, String domain) throws UnsupportedEncodingException {
		Cookie cookie = createCookie(name, value, path);
		cookie.setDomain(domain);
		return cookie;
	}
	public static Cookie createCookie(String name, String value, String path, String domain, int maxAge) throws UnsupportedEncodingException {
		Cookie cookie = createCookie(name, value, path, domain);
		cookie.setMaxAge(maxAge);
		return cookie;
	}
	public static Cookie createCookie(String name, String value, String path, int maxAge) throws UnsupportedEncodingException {
		Cookie cookie = createCookie(name, value, path);
		cookie.setMaxAge(maxAge);
		return cookie;
	}
	
	//객체 생성 후에 불러야 하기 때문에 스태틱 잡으면 안된다.
	public Cookie getCookie(String name) {
		return cookieMap.get(name);
	}
	
	public String getValue(String key) throws UnsupportedEncodingException {
		Cookie cookie = cookieMap.get(key);
		if(cookie!=null) {
			return URLDecoder.decode(cookie.getValue(),"utf-8");
		}else {
			return "";
		}
	}
	
	public boolean exist(String name) {
		return cookieMap.containsKey(name);
	}
	
	
	

}
