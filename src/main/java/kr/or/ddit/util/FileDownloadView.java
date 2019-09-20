package kr.or.ddit.util;

import java.io.FileInputStream;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.view.AbstractView;

import kr.or.ddit.file.model.File;

public class FileDownloadView extends AbstractView {

	private static final Logger logger = LoggerFactory.getLogger(FileDownloadView.class);
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 컨트롤러 메소드에서 추가한 속성
		File file = (File) model.get("file");

//		if(file == null) 
//			file = "noimage.png";
		
		response.setHeader("content-disposition", "attachment;filename=" + file.getFileName());
		response.setContentType("application/octet-stream"); // 바이너리로 요청이 전송됨
		
		FileInputStream fis = new FileInputStream(file.getRealFileName());
		ServletOutputStream sos = response.getOutputStream();
		
		byte[] buffer = new byte[512];
		int len = 0;
		
		while( (len = fis.read(buffer, 0, 512)) != -1 ) {
			sos.write(buffer, 0, len);
		}
		
		sos.flush();
		sos.close();
		fis.close();
	}
	

}
