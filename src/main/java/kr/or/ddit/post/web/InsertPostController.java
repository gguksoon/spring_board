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
@WebServlet("/insertPost")
public class InsertPostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private IBoardService boardService;
	private IPostService postService;
	private IFileService fileService;
	
	@Override
	public void init() throws ServletException {
		boardService = new BoardService();
		postService = new PostService();
		fileService = new FileService();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 새글, 답글 모두 적용
		request.setAttribute("boardList", boardService.getBoardList());
		request.setAttribute("board", boardService.getBoard(request.getParameter("boardSeq")));
		
		// 아래 부터는 답글인 경우만
		String paramPostSeq = request.getParameter("postSeq");
		String paramPostGn = request.getParameter("postGn");
		
		int postSeq = paramPostSeq == null ? 0 : Integer.parseInt(paramPostSeq); 
		int postGn = paramPostGn == null ? 0 : Integer.parseInt(paramPostGn);
		
		if(postSeq != 0 && postGn != 0) {
			request.setAttribute("postSeq", postSeq);
			request.setAttribute("postGn", postGn);
		}
		
		// forward
		request.getRequestDispatcher("/insertPost.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		request.setAttribute("boardList", boardService.getBoardList());
		
		int boardSeq = Integer.parseInt(request.getParameter("boardSeq"));
		String userId = request.getParameter("userId");
		String postNm = request.getParameter("postNm");
		String postContent = request.getParameter("content");
		
		String paramGn = request.getParameter("postGn");
		int postGn = 0;
		if(!paramGn.equals("")) postGn = Integer.parseInt(paramGn) + 1;
		
		String paramParentSeq = request.getParameter("postSeq");
		Integer parentSeq = null;
		if(!paramParentSeq.equals("")) parentSeq = Integer.parseInt(paramParentSeq);
		
		Post post = new Post(0, boardSeq, postNm, postContent, userId,
							 new Date(), new Date(), 1, postGn, parentSeq);
		
		int postSeq = postService.insertPost(post);
		Post resultPost = postService.getPost(postSeq);
		request.setAttribute("post", resultPost);
		
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
		
		response.sendRedirect(request.getContextPath() + "/post?boardSeq=" + boardSeq + "&postSeq=" + resultPost.getPostSeq());
		//====================================================================
	}

}
