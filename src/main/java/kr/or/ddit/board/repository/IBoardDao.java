package kr.or.ddit.board.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.board.model.Board;

public interface IBoardDao {

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
	* Method : getBoardLocation
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @param boardLocation
	* @return
	* Method 설명 : boardLocation에 해당하는 board객체 반환
	*/
	Board getBoardLocation(int boardLocation);
	
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
	
}
