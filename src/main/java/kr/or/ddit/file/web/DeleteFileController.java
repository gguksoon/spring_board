package kr.or.ddit.file.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.file.model.File;
import kr.or.ddit.file.service.FileService;
import kr.or.ddit.file.service.IFileService;

@WebServlet("/deleteFile")
public class DeleteFileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	IFileService fileService;
	
	@Override
	public void init() throws ServletException {
		fileService = new FileService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int fileSeq = Integer.parseInt(request.getParameter("fileSeq"));
		int postSeq = Integer.parseInt(request.getParameter("postSeq"));
		int boardSeq = Integer.parseInt(request.getParameter("boardSeq"));
		
		File file = fileService.getFile(fileSeq);
		
		fileService.deleteFile(fileSeq);
		
		response.sendRedirect(request.getContextPath() + "/updatePost?boardSeq=" + boardSeq + "&postSeq=" + postSeq);
	}

}