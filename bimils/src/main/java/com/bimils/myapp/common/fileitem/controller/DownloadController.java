package com.bimils.myapp.common.fileitem.controller;

import java.io.File;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bimils.myapp.common.fileitem.model.FileItem;
import com.bimils.myapp.common.fileitem.service.FileItemService;
import com.bimils.myapp.common.util.ConstantUtil;


@Controller
public class DownloadController {
	
	@Autowired	
	FileItemService fileItemService;

	@RequestMapping("/common/download")
	public void process(HttpServletRequest request, HttpServletResponse response, @RequestParam(value="file_seq_no", defaultValue="0") int file_seq_no) throws Exception {

		
		try {
		
		//기존 리스폰스 정보 초기화
		response.reset();

		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("file_seq_no", file_seq_no);
		
		FileItem fileItem = fileItemService.getFileItem(paramMap);
		
		if(fileItem == null) {
			throw new RuntimeException("해당 파일이 존재하지 않습니다. null");
		}
		
		//헤더 정보를 바꿔준다.
		//콘텐트타입 변경
		response.setHeader("Content-Type", "application/octet-stream;"); //octet-stream : 8진수
		//disposition 변경
		String fileName = URLEncoder.encode(fileItem.getFile_name(),"utf-8");
		response.setHeader("Content-Disposition", "attachment; filename=\""+fileName+"\";");
		//content-Transfer-Encoding
		response.setHeader("Content-Transfer-Encoding", "binary"); //2진 데이타
		//콘텐트 사이즈
		response.setContentLength((int)fileItem.getFile_size());
		//캐쉬 관련
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "-1");
		//파일 전송
		File saveFile = new File(ConstantUtil.UPLOAD_PATH + "/" + fileItem.getFile_path() + 
				"/" + fileItem.getFile_save_name());
		System.out.println(saveFile);
		if(!saveFile.exists()) {
			throw new RuntimeException("해당 파일이 존재하지 않습니다.");
		}
		//응답 데이터로 파일 전송
		FileUtils.copyFile(saveFile, response.getOutputStream());
		response.getOutputStream().close(); //자원해제
		
		fileItemService.updateDownloadCnt(paramMap);
		
		}catch(Exception e) {
			e.printStackTrace();
			response.reset(); //에러가 나면 리스폰스 다시 초기화 한다.
			response.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404에러
		}

	}
}
