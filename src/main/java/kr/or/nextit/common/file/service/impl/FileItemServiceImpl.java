package kr.or.nextit.common.file.service.impl;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.apache.commons.io.FileUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.nextit.common.file.mapper.FileItemMapper;
import kr.or.nextit.common.file.model.FileItem;
import kr.or.nextit.common.file.service.FileItemService;
import kr.or.nextit.common.util.ConstantUtil;
import kr.or.nextit.mybatis.MybatisSqlSessionFactory;

@Service("fileItemService")
public class FileItemServiceImpl implements FileItemService{

	private SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
	private DecimalFormat deciFmt = new DecimalFormat();
	
	@Autowired
	private FileItemMapper fileItemMapper;
	
	@Override
	public FileItem getFileItem(Map<String, Object> paramMap) throws Exception {
	
			FileItem fileItem = fileItemMapper.selectFileItem(paramMap);
			return fileItem;
	}

	@Override
	public int updateDownloadCnt(Map<String, Object> paramMap) throws Exception {
		
			return fileItemMapper.updateDownloadCnt(paramMap);			
	
	}

	@Override
	public List<FileItem> uploadFiles(HttpServletRequest request, String biz_type) throws Exception {

		List<FileItem> fileItems = new ArrayList<>();
		
		Collection<Part> parts = request.getParts();
		
		for(Part part : parts) {
			
			if(part.getHeader("Content-Disposition").contains("filename=")) {
				if(part.getSize() > 0) {
					// FileItem객체 생성 
					FileItem fileItem = new FileItem();
					
					fileItem.setFile_content_type(part.getContentType());
					fileItem.setFile_size(part.getSize());
					fileItem.setFile_fancy_size(getFancySize(part.getSize())); // 1,200KB 형식처럼
					fileItem.setFile_name(part.getSubmittedFileName());
					
					fileItem.setBiz_type(biz_type); 
					fileItem.setFile_path(biz_type+"/"+fmt.format(new Date())); // biz_type/20171208
					fileItem.setFile_save_name(
							UUID.randomUUID().toString() + "_" + part.getSubmittedFileName()
							); //UUID id 생성..
					
					//파일 저장
					FileUtils.copyInputStreamToFile(part.getInputStream(), 
							new File(ConstantUtil.UPLOAD_PATH + "/" + fileItem.getFile_path()+ "/" + fileItem.getFile_save_name()));
					
					fileItems.add(fileItem);
				}
			}
			
		}
		
		
		return fileItems;
	}

	private String getFancySize(long size) {
		
		String fancy = "";
		
		
		if(size < 1024) {
			fancy = deciFmt.format(size) + "bytes";
		}else if(size < 1024 * 1024) {
			fancy = deciFmt.format(size / 1024.0) + "KB";
		}else {
			fancy = deciFmt.format(size / (1024 * 1024.0)) + "MB";
		}
		
		return fancy;
	}
}
