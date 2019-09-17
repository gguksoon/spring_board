package kr.or.ddit.file.web;

import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.file.model.File;
import kr.or.ddit.file.service.FileService;
import kr.or.ddit.file.service.IFileService;

@WebServlet("/fileDownload")
public class FileDownloadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private IFileService fileService;
	
	@Override
	public void init() throws ServletException {
		fileService = new FileService();
	}
	
	/**
	* Method : doGet
	* 작성자 : Jo Min-Soo
	* 변경이력 :
	* @param request
	* @param response
	* @throws ServletException
	* @throws IOException
	* Method 설명 : 파일 다운로드, 참고 ==> https://programmingsummaries.tistory.com/85
	*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 파일명과 경로 가져오기
		int fileSeq = Integer.parseInt(request.getParameter("fileSeq"));
		File file = fileService.getFile(fileSeq);
		
		response.setContentType("application/octet-stream");
		
		// 무조건 다운로드 하도록 설정
		response.setHeader("Content-Disposition","attachment;filename=" + file.getFileName());
		
		// 요청된 파일을 읽어서 클라이언트측에 저장
		FileInputStream fis = new FileInputStream(file.getRealFileName());
		ServletOutputStream sos = response.getOutputStream();
		
		byte b [] = new byte[1024];
		int data = 0;
		
		while((data=(fis.read(b, 0, b.length))) != -1)
		{
			sos.write(b, 0, data);
		}
		
		sos.flush();
		sos.close();
		fis.close();
	}

}
