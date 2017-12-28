package kr.or.nextit.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig(
	location = "/uploadTemp", //임시 저장 폴더
	maxFileSize=1024 * 1024 * 10, //파일의 최대 크기
	maxRequestSize=1024 * 1024 * 10, //전체 요청데이터의 크리
	
	fileSizeThreshold=1024*1024*10 //임시 저장 파일로 할건지 결정하는 파일 크기
)
@WebServlet(urlPatterns="/upload", loadOnStartup=1)
public class UploadServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("파일 업로드 서블릿 가동");
		
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=utf-8");
		
		PrintWriter writer = resp.getWriter();
		writer.println("<html>");
		writer.println("<head><title>파일 업로드 예제 </title></head>");
		writer.println("</html>");
		String contentType = req.getContentType();
		System.out.println("contentType : " + contentType);
		
		if(contentType != null && contentType.startsWith("multipart/")) {
			Collection<Part> parts =  req.getParts();
			
			for(Part part : parts) {
				String name = part.getName(); //파라미터 이름
				String type = part.getContentType(); // 컨텐트 타입
				String disposition = part.getHeader("Content-Disposition");
				if(disposition.contains("filename=")) {
					//파일 정보	
					writer.println("<br>파일 사이즈 : " + part.getSize());
					writer.println("<br>파일명 : " + part.getSubmittedFileName());
					//파일 저장
					if(part.getSize() > 0) {
						part.write("/uploadFiles/" + part.getSubmittedFileName()); //파일 저장
						part.delete(); // 임시 저장 파일 삭제
					}
				}else {
					//일반 파라밐터
					writer.println("<br>" + name + " = " + req.getParameter(name));
				}
			writer.println("<hr>");
			}
			
		}
		
		
		writer.print("<body>");
		writer.print("<body>");
		writer.print("<body>");
		writer.print("</html>");
		
		writer.close();
	}
	
	
	
	
}
