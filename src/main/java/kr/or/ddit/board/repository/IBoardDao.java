package kr.or.ddit.board.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.board.model.Board;

public interface IBoardDao {

	/**
	* Method : getBoard
	* 작성자 : Jo Min-Soo
	* 변경이력 :
	* @param ss
	* @param boardSeq
	* @return
	* Method 설명 : boardSeq에 해당하는 board객체 반환
	*/
	public Board getBoard(SqlSession ss, String boardSeq);
	
	/**
	* Method : getBoardLocation
	* 작성자 : Jo Min-Soo
	* 변경이력 :
	* @param ss
	* @param boardLocation
	* @return
	* Method 설명 : boardLocation에 해당하는 board객체 반환
	*/
	public Board getBoardLocation(SqlSession ss, int boardLocation);
	
	/**
	* Method : getBoardList
	* 작성자 : Jo Min-Soo
	* 변경이력 :
	* @param ss
	* @return
	* Method 설명 : 전체 board객체 반환
	*/
	public List<Board> getBoardList(SqlSession ss);
	
	/**
	* Method : insertBoard
	* 작성자 : Jo Min-Soo
	* 변경이력 :
	* @param ss
	* @param board
	* @return
	* Method 설명 : 게시판 추가
	*/
	public int insertBoard(SqlSession ss, Board board);
	
	/**
	* Method : updateBoard
	* 작성자 : Jo Min-Soo
	* 변경이력 :
	* @param ss
	* @param board
	* @return
	* Method 설명 : 게시판 수정
	*/
	public int updateBoard(SqlSession ss, Board board);
	
}
