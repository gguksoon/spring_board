package kr.or.ddit.post.web;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import kr.or.ddit.board.model.Board;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.file.model.File;
import kr.or.ddit.file.service.FileService;
import kr.or.ddit.file.service.IFileService;
import kr.or.ddit.post.model.Post;
import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.post.service.PostService;
import kr.or.ddit.util.FileuploadUtil;

@MultipartConfig()
@WebServlet("/updatePost")
public class UpdatePostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	IBoardService boardService;
	IPostService postService;
	IFileService fileService;
	
	@Override
	public void init() throws ServletException {
		boardService = new BoardService();
		postService = new PostService();
		fileService = new FileService();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		List<Board> boardList = boardService.getBoardList();
		
		String boardSeq = request.getParameter("boardSeq");
		Board board = boardService.getBoard(boardSeq);
		
		int postSeq = Integer.parseInt(request.getParameter("postSeq"));
		
		Post post = postService.getPost(postSeq);
		List<File> fileList = fileService.getFileList(postSeq);
		
		request.setAttribute("boardList", boardList);
		request.setAttribute("board", board);
		request.setAttribute("post", post);
		request.setAttribute("fileList", fileList);
		
		request.getRequestDispatcher("/updatePost.jsp").forward(request, response);
	}
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		request.setAttribute("boardList", boardService.getBoardList());
		
		int boardSeq = Integer.parseInt(request.getParameter("boardSeq"));
		
		int postSeq = Integer.parseInt(request.getParameter("postSeq"));
		String postNm = request.getParameter("postNm");
		String postContent = request.getParameter("content");
		
		Post post = new Post(postSeq, postNm, postContent, new Date());
		
		postService.updatePost(post);
		
		//====================================================================
		List<Part> parts = (List<Part>) request.getParts();
		
		for(Part part : parts) {
			if(part.getName().equalsIgnoreCase("files")) {
				String filename = "";
				String path = "";
				if(part.getSize() > 0) {
					filename = FileuploadUtil.getFilename(part.getHeader("Content-Disposition"));
					String realFileName = UUID.randomUUID().toString();
					String ext = FileuploadUtil.getFileExtension(part.getHeader("Content-Disposition"));
					path = FileuploadUtil.getPath() + realFileName + ext;
					
					part.write(path);
					
					// 파일테이블에 추가
					File file = new File(0, filename, path, postSeq);
					fileService.insertFile(file);
				}
			}
		}
		
		response.sendRedirect(request.getContextPath() + "/post?boardSeq=" + boardSeq + "&postSeq=" + postSeq);
	}

}
