package kr.or.nextit.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//어노테이션을 달아주면 web.xml에 설정 안해도 자동으로 servlet으로 인지.
@WebServlet(urlPatterns= {"/hello2" ,"/hello3"})
public class HelloServlet extends HttpServlet{
	//tomcat : was, servlet container.. web container.. 

	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init(ServletConfig config)");
		super.init(config);
	}

	@Override
	public void init() throws ServletException {
		System.out.println("init()");
		super.init();
	}

	@Override
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
		System.out.println("service(ServletRequest arg0, ServletResponse arg1)");
		super.service(arg0, arg1);
	}
	
	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		System.out.println("service(HttpServletRequest arg0, HttpServletResponse arg1)");
		super.service(arg0, arg1);
	}
	
	//get방식
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet(HttpServletRequest request, HttpServletResponse response)");
		
		
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8"); //페이지 더렉티브 내용
		
		//클라이언트에게 데이터를 보내는 주체
		PrintWriter writer = response.getWriter();
		
		writer.println("<html>");
		
		writer.println("<header>");
		writer.println("<title>");
		writer.println("Servlet 예제");
		writer.println("</title>");
		writer.println("</header>");

		writer.println("<body>");
		writer.println("안녕하세요, ");
		writer.print(request.getParameter("user_name"));
		writer.println("님 ^^");
		writer.println("</body>");
		
		writer.println("</html>");
		
		writer.close();
		
	}

	//post방식
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doPost(HttpServletRequest request, HttpServletResponse response)");
	}

	//servlet을 수정하면 destroy 불러서 다시 생성
	@Override
	public void destroy() {
		System.out.println("destroy()");
		super.destroy();
	}

	
}
