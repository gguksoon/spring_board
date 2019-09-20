package kr.or.ddit.file.web;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.file.service.IFileService;

@Controller
public class FileController {

	private static final Logger logger = LoggerFactory.getLogger(FileController.class);
	
	@Resource(name = "fileService")
	private IFileService fileService;
	
	@RequestMapping("fileDownloadView")
	public void fileDownloadView(Model model, int fileSeq) {
		model.addAttribute("file", fileService.getFile(fileSeq));
	}
	
	@RequestMapping("deleteFile")
	public String deleteFile(int fileSeq, int postSeq, int boardSeq) {
		logger.debug("fileSeq: {}", fileSeq);
		logger.debug("postSeq: {}", postSeq);
		logger.debug("boardSeq: {}", boardSeq);
		fileService.deleteFile(fileSeq);
		
		return "redirect:/updatePost?boardSeq=" + boardSeq + "&postSeq=" + postSeq;
	}
}
