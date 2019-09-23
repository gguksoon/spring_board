package kr.or.ddit.board.web;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.board.model.Board;
import kr.or.ddit.board.service.IBoardService;

@Controller
public class BoardController {
	
	@Resource(name="boardService")
	private IBoardService boardService;

	/**
	* Method : manageBoard
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @return
	* Method 설명 : 게시판 관리 페이지 요청
	*/
	@RequestMapping("/manageBoard")
	public String manageBoard() {
		return "board/manageBoard";
	}
	
	/**
	* Method : insertBoard
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @param boardNm
	* @param userId
	* @param boardStatus
	* @param request
	* @return
	* Method 설명 : 게시판 추가
	*/
	@RequestMapping("/insertBoard")
	public String insertBoard(String boardNm, String userId, String boardStatus, HttpServletRequest request) {
		Board board = new Board(0, boardNm, userId, 0, (boardStatus.equals("사용") ? 1 : 0));
		
		boardService.insertBoard(board);
		
		// application에 boardList 넣기
		ServletContext application = request.getServletContext();
		application.setAttribute("boardList", boardService.getBoardList());
		
		return "redirect:/manageBoard";
	}
	
	/**
	* Method : updateBoard
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @param boardSeq
	* @param boardNm
	* @param userId
	* @param boardLocation
	* @param boardStatus
	* @param request
	* @return
	* Method 설명 : 게시판 수정
	*/
	@RequestMapping("/updateBoard")
	public String updateBoard(int boardSeq, String boardNm, String userId, int boardLocation, String boardStatus, HttpServletRequest request) {
		Board board = new Board(boardSeq, boardNm, userId, boardLocation, (boardStatus.equals("사용") ? 1 : 0));
		
		boardService.updateBoard(board);
		
		// application에 boardList 넣기
		ServletContext application = request.getServletContext();
		application.setAttribute("boardList", boardService.getBoardList());
		
		return "redirect:/manageBoard";
	}

	/**
	* Method : locationChangeBoard
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @param boardSeq
	* @param change
	* @param request
	* @return
	* Method 설명 : 게시판 위치 변경
	*/
	@RequestMapping("/locationChangeBoard")
	public String locationChangeBoard(int boardSeq, String change, HttpServletRequest request) {
		boardService.locationChange(boardSeq, change);
		
		// application에 boardList 넣기
		ServletContext application = request.getServletContext();
		application.setAttribute("boardList", boardService.getBoardList());
		
		return "redirect:/manageBoard";
	}
}
