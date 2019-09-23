package kr.or.ddit.file.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.file.service.IFileService;

@Controller
public class FileController {

	@Resource(name = "fileService")
	private IFileService fileService;
	
	/**
	* Method : fileDownloadView
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @param model
	* @param fileSeq
	* Method 설명 : 파일 다운로드
	*/
	@RequestMapping("fileDownloadView")
	public void fileDownloadView(Model model, int fileSeq) {
		model.addAttribute("file", fileService.getFile(fileSeq));
	}
	
	/**
	* Method : deleteFile
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @param fileSeq
	* @param postSeq
	* @param boardSeq
	* @return
	* Method 설명 : 파일 삭제
	*/
	@RequestMapping("deleteFile")
	public String deleteFile(int fileSeq, int postSeq, int boardSeq) {
		fileService.deleteFile(fileSeq);
		
		return "redirect:/updatePost?boardSeq=" + boardSeq + "&postSeq=" + postSeq;
	}
}
