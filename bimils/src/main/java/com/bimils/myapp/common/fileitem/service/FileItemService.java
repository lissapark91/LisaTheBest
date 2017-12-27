package com.bimils.myapp.common.fileitem.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.bimils.myapp.common.fileitem.model.FileItem;


public interface FileItemService {
	
	//파일 정보 조회
	public FileItem getFileItem(Map<String, Object> paramMap) throws Exception;

	//다운로드 카운트 업데이트
	public int updateDownloadCnt(Map<String, Object> paramMap) throws Exception;
	
	//파일업로드
	public List<FileItem> uploadFiles(HttpServletRequest request, String biz_type) throws Exception;
	
	
}
