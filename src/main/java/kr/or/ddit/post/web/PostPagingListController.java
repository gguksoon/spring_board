package kr.or.ddit.post.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.model.Board;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.common.model.Page;
import kr.or.ddit.post.model.Post;
import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.post.service.PostService;

@WebServlet("/postPagingList")
public class PostPagingListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private IBoardService boardService;
	private IPostService postService;
	
	@Override
	public void init() throws ServletException {
		boardService = new BoardService();
		postService = new PostService();
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// left.jsp에 list를 출력하기 위해 받는 boardList
		List<Board> boardList = boardService.getBoardList();
		request.setAttribute("boardList", boardList);
		
		// pagingList에 파라미터로 넘겨주기 위해 받는 현재 게시판 시퀀스
		String boardSeq = request.getParameter("boardSeq");
		Board board = boardService.getBoard(boardSeq);
		
		// pagingList에 파라미터로 넘겨주기 위해 받는 Page
		String paramPage = request.getParameter("page");
		String paramPagesize = request.getParameter("pagesize");
		
		int page = paramPage == null ? 1 : Integer.parseInt(paramPage);
		int pagesize = paramPagesize == null ? 10 : Integer.parseInt(paramPagesize);
		
		// 넘겨줄 파라미터맵
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("boardSeq", board.getBoardSeq());
		map.put("page", page);
		map.put("pagesize", pagesize);
		
		Page p = new Page(page, pagesize);
		request.setAttribute("pageVo", p);
		
		// 결과로 받은 리스트
		Map<String, Object> resultMap = postService.getPostPagingList(map);
		List<Post> postList = (List<Post>) resultMap.get("postList");
		int paginationSize = (Integer) resultMap.get("paginationSize");

		// 위에서 받은 리스트를 request에 저장
		
		request.setAttribute("board", board);
		request.setAttribute("boardSeq", boardSeq);
		request.setAttribute("postList", postList);
		request.setAttribute("paginationSize", paginationSize);
		
		request.getRequestDispatcher("/postPagingList.jsp").forward(request, response);
	}

}
