package kr.or.ddit.post.web;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.board.model.Board;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.common.model.Page;
import kr.or.ddit.file.model.File;
import kr.or.ddit.file.service.IFileService;
import kr.or.ddit.post.model.Post;
import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.reply.service.IReplyService;
import kr.or.ddit.util.FileUtil;
import kr.or.ddit.util.model.FileInfo;

@Controller
public class PostController {

	@Resource(name = "boardService")
	private IBoardService boardService;
	
	@Resource(name = "postService")
	private IPostService postService;
	
	@Resource(name = "replyService")
	private IReplyService replyService;
	
	@Resource(name = "fileService")
	private IFileService fileService;
	
	
	/**
	* Method : postPagingList
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @param model
	* @param boardSeq
	* @param page
	* @param pagesize
	* @return
	* Method 설명 : 게시글 페이징 리스트 반환
	*/
	@GetMapping("postPagingList")
	public String postPagingList(Model model, int boardSeq, Integer page, Integer pagesize) {
		// pagingList에 파라미터로 넘겨주기 위해 받는 현재 게시판 시퀀스
		Board board = boardService.getBoard(boardSeq);
		model.addAttribute("boardSeq", board.getBoardSeq());
		model.addAttribute("board", board);
		
		// pagingList에 파라미터로 넘겨주기 위해 받는 Page
		if(page == null) page = 1;
		if(pagesize == null) pagesize = 10;
		
		// 넘겨줄 파라미터맵
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("boardSeq", board.getBoardSeq());
		map.put("page", page);
		map.put("pagesize", pagesize);

		Page p = new Page(page, pagesize);
		model.addAttribute("pageVo", p);
		
		// 결과로 받은 리스트
		model.addAllAttributes(postService.getPostPagingList(map));

		return "post/postPagingList";
	}
	
	/**
	* Method : postView
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @param model
	* @param postSeq
	* @param boardSeq
	* @return
	* Method 설명 : 게시글 페이지 요청
	*/
	@GetMapping("post")
	public String postView(Model model, int postSeq, int boardSeq) {
		model.addAttribute("post", postService.getPost(postSeq));
		model.addAttribute("boardSeq", boardSeq);
		model.addAttribute("board", boardService.getBoard(boardSeq));
		model.addAttribute("replyList", replyService.getReplyList(postSeq));
		model.addAttribute("fileList", fileService.getFileList(postSeq));
		
		return "post/post";
		
	}
	
	/**
	* Method : insertPostView
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @param model
	* @param boardSeq
	* @param postSeq
	* @param postGn
	* @return
	* Method 설명 : 게시글 작성 페이지 요청
	*/
	@GetMapping("insertPost")
	public String insertPostView(Model model, int boardSeq, Integer postSeq, Integer postGn) {
		// 새글, 답글 모두 적용
		model.addAttribute("board", boardService.getBoard(boardSeq));
		
		// 아래 부터는 답글인 경우만
		if(postSeq == null) postSeq = 0;
		if(postGn == null) postGn = 0;
		
		if(postSeq != 0 && postGn != 0) {
			model.addAttribute("postSeq", postSeq);
			model.addAttribute("postGn", postGn);
		}
		
		return "post/insertPost";
	}
	
	/**
	* Method : insertPost
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @param boardSeq
	* @param userId
	* @param postNm
	* @param postContent
	* @param postSeq
	* @param postGn
	* @param files
	* @return
	* Method 설명 : 게시글 추가
	*/
	@PostMapping("insertPost")
	public String insertPost(int boardSeq, String userId, String postNm, String postContent,
							 Integer postSeq, Integer postGn, @RequestPart("files") List<MultipartFile> files) {
		postGn = postGn == null ? 0 : postGn + 1; 
		Integer parentSeq = postSeq == null ? null : postSeq;
		
		Post post = new Post(0, boardSeq, postNm, postContent, userId,
							 new Date(), new Date(), 1, postGn, parentSeq);
		
		// 게시글 추가
		int insertPostSeq = postService.insertPost(post);
		
		// 파일 업로드
		for(MultipartFile file : files) {
			if(file.getSize() > 0) {
				// 업로드 되는 시점의 년/월 폴더를 생성해주고, 파일 경로와 파일 정보를 FileInfo객체에 담아 리턴
				FileInfo fileInfo = FileUtil.getFileInfo(file.getOriginalFilename());
				
				try {
					// 파일 전송
					file.transferTo(fileInfo.getFile());
					File f = new File(0, fileInfo.getOriginalFileName(), fileInfo.getFile().toString(), insertPostSeq);
					fileService.insertFile(f);
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}
		}
		return "redirect:/post?boardSeq=" + boardSeq + "&postSeq=" + insertPostSeq;
	}
	
	/**
	* Method : updatePostView
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @param model
	* @param boardSeq
	* @param postSeq
	* @return
	* Method 설명 : 게시글 수정 페이지 요청
	*/
	@GetMapping("updatePost")
	public String updatePostView(Model model, int boardSeq, int postSeq) {
		model.addAttribute("board", boardService.getBoard(boardSeq));
		model.addAttribute("post", postService.getPost(postSeq));
		model.addAttribute("fileList", fileService.getFileList(postSeq));

		return "post/updatePost";
	}
	
	/**
	* Method : updatePost
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @param boardSeq
	* @param postSeq
	* @param postNm
	* @param postContent
	* @param files
	* @return
	* Method 설명 : 게시글 수정
	*/
	@PostMapping("updatePost")
	public String updatePost(int boardSeq, int postSeq, String postNm, String postContent, 
							 @RequestPart("files") List<MultipartFile> files) {
		Post post = postService.getPost(postSeq);
		post.setPostNm(postNm);
		post.setPostContent(postContent);
		post.setPostModDate(new Date());
		
		postService.updatePost(post);
		
//		// 파일 업로드
		// 파일 전송을 안해도 filename이 ""인 1개가 생성됨.. 그래서 예외처리 
		for(MultipartFile file : files) {
			if(file.getSize() > 0) {
				// 업로드 되는 시점의 년/월 폴더를 생성해주고, 파일 경로와 파일 정보를 FileInfo객체에 담아 리턴
				FileInfo fileInfo = FileUtil.getFileInfo(file.getOriginalFilename());
				
				try {
					// 파일 전송
					file.transferTo(fileInfo.getFile());
					File f = new File(0, fileInfo.getOriginalFileName(), fileInfo.getFile().toString(), postSeq);
					fileService.insertFile(f);
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}
		}
		return "redirect:/post?boardSeq=" + boardSeq + "&postSeq=" + postSeq;
	}
	
	/**
	* Method : deletePost
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @param postSeq
	* @param boardSeq
	* @return
	* Method 설명 : 게시글 삭제
	*/
	@PostMapping("deletePost")
	public String deletePost(int postSeq, int boardSeq) {
		postService.deletePost(postSeq);
		
		return "redirect:/postPagingList?boardSeq=" + boardSeq;
	}
}
