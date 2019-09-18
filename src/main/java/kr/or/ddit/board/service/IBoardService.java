package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.model.Board;

public interface IBoardService {

	/**
	* Method : getBoard
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @param boardSeq
	* @return
	* Method 설명 : boardSeq에 해당하는 board객체 반환
	*/
	Board getBoard(int boardSeq);
	
	/**
	* Method : getBoardList
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @return
	* Method 설명 : 전체 board객체 반환
	*/
	List<Board> getBoardList();
	
	/**
	* Method : insertBoard
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @param board
	* @return
	* Method 설명 : 게시판 추가
	*/
	int insertBoard(Board board);
	
	/**
	* Method : updateBoard
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @param board
	* @return
	* Method 설명 : 게시판 수정
	*/
	int updateBoard(Board board);
	
	/**
	* Method : locationChange
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @param boardSeq
	* @param change
	* Method 설명 : boardSeq의 location을 change에 들어있는 값에따라 증가시키거나 감소시키는 메서드
	*/
	void locationChange(int boardSeq, String change);
}
