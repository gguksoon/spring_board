package kr.or.ddit.board.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.board.model.Board;
import kr.or.ddit.config.test.WebTestConfig;

public class BoardServiceTest extends WebTestConfig {

	@Resource(name = "boardService")
	private IBoardService boardService;
	
	/**
	* Method : getBoardTest
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* Method 설명 : boardSeq에 해당하는 board객체 반환 테스트
	*/
	@Test
	public void getBoardTest() {
		/***Given***/

		/***When***/
		Board board = boardService.getBoard(1);

		/***Then***/
		assertEquals("게시판1", board.getBoardNm());
	}

	/**
	* Method : getBoardListTest
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* Method 설명 : 전체 게시판 반환 테스트
	*/
	@Test
	public void getBoardListTest() {
		/***Given***/

		/***When***/
		List<Board> boardList = boardService.getBoardList();

		/***Then***/
		assertEquals(5, boardList.size());
	}
	
	/**
	* Method : insertBoardTest
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* Method 설명 : 게시판 추가 테스트
	*/
	@Test
	public void insertBoardTest() {
		/***Given***/
		Board board = new Board(9999, "테스트용 게시판", "brown", 0, 1);

		/***When***/
		int insertCnt = boardService.insertBoard(board);
		
		/***Then***/
		assertEquals(1, insertCnt);
	}
	
	/**
	* Method : updateBoard
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* Method 설명 : 게시판 수정 테스트 
	*/
	@Test
	public void updateBoardTest() {
		/***Given***/
		Board board = new Board(1, "테스트용 게시판222", "brown", 99, 1);

		/***When***/
		int updateCnt = boardService.updateBoard(board);
		
		/***Then***/
		assertEquals(1, updateCnt);
	}
	
	/**
	* Method : locationChangeTest
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* Method 설명 : boardSeq의 location을 change에 들어있는 값에따라 증가시키거나 감소시키는 메서드 테스트
	*/
	@Test
	public void locationChangeTest() {
		/***Given***/
		
		/***When***/
		boardService.locationChange(1, "down");

		/***Then***/
		assertEquals(2, boardService.getBoard(1).getBoardLocation());
	}
}
