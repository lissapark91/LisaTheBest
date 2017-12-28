package kr.or.nextit.web.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.nextit.web.handler.URIHandlerMapping;

//servlet이기 때문에 프로퍼티에 있는 맵핑을 먼저 만들어 놔야 함..(요청될때마다 만드는 것이 아니라)
public class DispatcherServlet extends HttpServlet{
	
	//서블릿 로딩될 때 톰캣이 부름. 
	@Override
	public void init(ServletConfig config) throws ServletException {
		//web.xml에 initparameter를 불러옴
		String contextConfiglocation = config.getInitParameter("contextConfiglocation");
		String configFilePath = config.getServletContext().getRealPath(contextConfiglocation); //application : sevletContext , 프로퍼티 파일의 실제 경로를 넘겨준다.
		try {
			URIHandlerMapping.init(configFilePath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String uri = request.getRequestURI();
		if(uri.indexOf(request.getContextPath()) == 0) {
			uri = uri.substring(request.getContextPath().length());
		}
		System.out.println("요청 URI : "+ uri);
		String viewPage="";
		try {
			
			//uri에 맞는 컨트롤러를 맵에서 꺼내서 리턴
			Controller controller = URIHandlerMapping.getHandler(uri);
			if(controller != null) {
				viewPage = controller.process(request, response);	
				if(viewPage != null) {
					if(viewPage.startsWith("redirect:")) {
						viewPage = viewPage.substring("redirect:".length());
						response.sendRedirect(request.getContextPath()+viewPage);
					} else {
						
						RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/"+viewPage+".jsp");
						dispatcher.forward(request, response);						
					}
					
				}
				
			}else {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND); //404에러
			}
			
			
			
//			if("/memberDBCP/memberList.do".equals(uri)) {
//				System.out.println("correct");
//				MemberListController memberListController = new MemberListController();
//				viewPage = memberListController.process(request, response);
//				System.out.println(viewPage);
//			}
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new ServletException();
		}
		
		
	}
	
}
