package kr.or.ddit.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileuploadUtil {

	/**
	* Method : getFilename
	* 작성자 : Jo Min-Soo
	* @param contentDisposition
	* @return
	* 변경이력 :
	* Method 설명 : Content-disposition 헤더 문자열로 부터 파일이름 추출 
	*/
	public static String getFilename(String contentDisposition) {
		// 메소드 인자: form-data; name="file"; filename="cony.png"
		String[] attrs = contentDisposition.split("; ");
		// attrs[0] = form-data
		// attrs[1] = name="file"
		// attrs[2] = filename="cony.png"
		//		==> keyValue[0] = filename
		//		==> keyValue[1] = "brown.png"
		
		String filename = "";
		for(String attr : attrs) {
			if(attr.startsWith("filename")) {
				String[] keyValue = attr.split("=");
				filename = keyValue[1].substring( keyValue[1].indexOf("\"") + 1,
												  keyValue[1].lastIndexOf("\"") );
				break;
			}
		}
		
		return filename;
	}

	/**
	* Method : getFileExtension
	* 작성자 : Jo Min-Soo
	* 변경이력 :
	* @param contentDisposition
	* @return
	* Method 설명 : Content-disposition 헤더 문자열로 부터 파일확장자 추출 
	*/
	public static String getFileExtension(String contentDisposition) {
		String filename = getFilename(contentDisposition);
		
		String fileExtension = "";
		if(filename.contains("."))
			fileExtension = filename.substring(filename.lastIndexOf("."));
		
		return fileExtension;
	}

	/**
	* Method : getPath
	* 작성자 : Jo Min-Soo
	* 변경이력 :
	* @return
	* Method 설명 : 파일을 업로드할 경로를 조회한다.
	*/
	public static String getPath() {
		String basicPath = "e:\\upload";
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String yyyyMM = sdf.format(new Date());
		String yyyy = yyyyMM.substring(0, 4);
		String mm = yyyyMM.substring(4, 6);
		
		File yyyyDirectory = new File(basicPath + "\\" + yyyy + "\\" + mm);
		if(!yyyyDirectory.exists())
			yyyyDirectory.mkdirs();
		
		return basicPath + "\\" + yyyy + "\\" + mm + "\\";
	}
}
