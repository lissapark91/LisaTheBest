package kr.or.nextit.web.handler;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import kr.or.nextit.web.servlet.Controller;

public class URIHandlerMapping {
	
	//하나만 만들려고 static
	public static Map<String, Controller> handlerMap = new HashMap<>();
	
	private URIHandlerMapping() {} // 객체 생성을 막아놈.. 필요하면 init을 불러라.
	
	//핸들러 초기화, 객체 생성 없이 접근 static
	public static void init(String configFilePath) throws Exception {
		//프로퍼티 파일은 디스패쳐가 넘겨 주는 것으로 (경로를 넘겨줌) -> configFilePath
		Properties prop = new Properties();
		prop.load(new FileReader(configFilePath));
		
		//key값을 쭉 뽑아서 나열
		Iterator itr = prop.keySet().iterator();
		
		while(itr.hasNext()) {
			String uri = (String)itr.next();
			String handlerClassName = prop.getProperty(uri);
			//객체 생성 : Class class로..  class name을 알고 있으니까. 임포트 없이
			Class handlerClass = Class.forName(handlerClassName);
			Controller controller = (Controller)handlerClass.newInstance(); // Controller
			
			handlerMap.put(uri, controller);
		}
		
	}
	
	public static Controller getHandler(String uri) {
		return handlerMap.get(uri);
	}
	
}
