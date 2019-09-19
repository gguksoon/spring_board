package kr.or.ddit.post.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.board.model.Board;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.common.model.Page;
import kr.or.ddit.file.service.IFileService;
import kr.or.ddit.post.model.Post;
import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.reply.service.IReplyService;

@Controller
public class PostController {

	private static final Logger logger = LoggerFactory.getLogger(PostController.class);
	
	@Resource(name = "boardService")
	private IBoardService boardService;
	
	@Resource(name = "postService")
	private IPostService postService;
	
	@Resource(name = "replyService")
	private IReplyService replyService;
	
	@Resource(name = "fileService")
	private IFileService fileService;
	
	
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
	
	@GetMapping("post")
	public String post(Model model, int postSeq, int boardSeq) {
		model.addAttribute("post", postService.getPost(postSeq));
		model.addAttribute("boardSeq", boardSeq);
		model.addAttribute("board", boardService.getBoard(boardSeq));
		model.addAttribute("replyList", replyService.getReplyList(postSeq));
		model.addAttribute("fileList", fileService.getFileList(postSeq));
		
		return "post/post";
		
	}
	
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
	
	@PostMapping("insertPost")
	public String insertPost(int boardSeq, String userId, String postNm, String postContent,
							 Integer postSeq, Integer postGn, @RequestPart("files") MultipartFile files) {
		postGn = postGn == null ? 0 : postGn + 1; 
		Integer parentSeq = postSeq == null ? null : postSeq;
		
		Post post = new Post(0, boardSeq, postNm, postContent, userId,
							 new Date(), new Date(), 1, postGn, parentSeq);
		
		int insertPostSeq = postService.insertPost(post);
		
		//====================================================================
//		List<Part> parts = (List<Part>) request.getParts();
//		
//		for(Part part : parts) {
//			if(part.getName().equalsIgnoreCase("files")) {
//				String filename = "";
//				String path = "";
//				if(part.getSize() > 0) {
//					filename = FileuploadUtil.getFilename(part.getHeader("Content-Disposition"));
//					String realFileName = UUID.randomUUID().toString();
//					String ext = FileuploadUtil.getFileExtension(part.getHeader("Content-Disposition"));
//					path = FileuploadUtil.getPath() + realFileName + ext;
//					
//					part.write(path);
//					
//					// 파일테이블에 추가
//					File file = new File(0, filename, path, postSeq);
//					fileService.insertFile(file);
//				}
//			}
//		}
		return "redirect:/post?boardSeq=" + boardSeq + "&postSeq=" + insertPostSeq;
	}
	
	@GetMapping("updatePost")
	public String updatePostView() {
//		List<Board> boardList = boardService.getBoardList();
//		
//		String boardSeq = request.getParameter("boardSeq");
//		Board board = boardService.getBoard(boardSeq);
//		
//		int postSeq = Integer.parseInt(request.getParameter("postSeq"));
//		
//		Post post = postService.getPost(postSeq);
//		List<File> fileList = fileService.getFileList(postSeq);
//		
//		request.setAttribute("boardList", boardList);
//		request.setAttribute("board", board);
//		request.setAttribute("post", post);
//		request.setAttribute("fileList", fileList);
//		
//		request.getRequestDispatcher("/updatePost.jsp").forward(request, response);
		return "";
	}
	
	@PostMapping("updatePost")
	public String updatePost() {
//		request.setAttribute("boardList", boardService.getBoardList());
//		
//		int boardSeq = Integer.parseInt(request.getParameter("boardSeq"));
//		
//		int postSeq = Integer.parseInt(request.getParameter("postSeq"));
//		String postNm = request.getParameter("postNm");
//		String postContent = request.getParameter("content");
//		
//		Post post = new Post(postSeq, postNm, postContent, new Date());
//		
//		postService.updatePost(post);
//		
//		//====================================================================
//		List<Part> parts = (List<Part>) request.getParts();
//		
//		for(Part part : parts) {
//			if(part.getName().equalsIgnoreCase("files")) {
//				String filename = "";
//				String path = "";
//				if(part.getSize() > 0) {
//					filename = FileuploadUtil.getFilename(part.getHeader("Content-Disposition"));
//					String realFileName = UUID.randomUUID().toString();
//					String ext = FileuploadUtil.getFileExtension(part.getHeader("Content-Disposition"));
//					path = FileuploadUtil.getPath() + realFileName + ext;
//					
//					part.write(path);
//					
//					// 파일테이블에 추가
//					File file = new File(0, filename, path, postSeq);
//					fileService.insertFile(file);
//				}
//			}
//		}
//		
//		response.sendRedirect(request.getContextPath() + "/post?boardSeq=" + boardSeq + "&postSeq=" + postSeq);
//	
		return "";
	}
	
	@PostMapping("deletePost")
	public String deletePost(int postSeq, int boardSeq) {
		postService.deletePost(postSeq);
		
		return "redirect:/postPagingList?boardSeq=" + boardSeq;
	}
}
