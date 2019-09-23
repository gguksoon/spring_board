package kr.or.ddit.board.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.board.model.Board;
import kr.or.ddit.config.test.WebTestConfig;

public class BoardDaoTest extends WebTestConfig {

	@Resource(name = "boardDao")
	private IBoardDao boardDao;
	
	/**
	* Method : getBoardTest
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* Method 설명 : boardSeq에 해당하는 board객체 반환 Test
	*/
	@Test
	public void getBoardTest() {
		/***Given***/
		int boardSeq = 1;

		/***When***/
		Board board = boardDao.getBoard(boardSeq);

		/***Then***/
		assertEquals("게시판1", board.getBoardNm());
	}
	
	/**
	* Method : getBoardLocation
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* Method 설명 : boardLocation에 해당하는 board객체 반환 
	*/
	@Test
	public void getBoardLocationTest() {
		/***Given***/
		int boardLocation = 1;

		/***When***/
		Board board = boardDao.getBoardLocation(boardLocation);

		/***Then***/
		assertEquals("게시판1", board.getBoardNm());
	}
	
	/**
	* Method : getBoardListTest
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* Method 설명 : 전체 board객체 반환 Test 
	*/
	@Test
	public void getBoardListTest() {
		/***Given***/

		/***When***/
		List<Board> boardList = boardDao.getBoardList();

		/***Then***/
		assertEquals(5, boardList.size());
	}
	
	/**
	* Method : insertBoard
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @param ss
	* @param board
	* @return
	* Method 설명 : 게시판 추가 Test
	*/
	@Test
	public void insertBoardTest() {
		/***Given***/
		Board board = new Board(10000, "테스트용게시판", "brown", 0, 1);

		/***When***/
		int insertCnt = boardDao.insertBoard(board);

		/***Then***/
		assertEquals(1, insertCnt);
	}
	
	/**
	* Method : updateBoard
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @param ss
	* @param board
	* @return
	* Method 설명 : 게시판 수정 Test
	*/
	@Test
	public void updateBoardTest() {
		/***Given***/
		Board board = new Board(1, "테스트용게시판", "brown", 0, 1);
		
		/***When***/
		int updateCnt = boardDao.updateBoard(board);

		/***Then***/
		assertEquals(1, updateCnt);
	}
	
}
