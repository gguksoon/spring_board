package kr.or.ddit.board.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.board.model.Board;

public class BoardDao implements IBoardDao {

	/**
	* Method : getBoard
	* 작성자 : Jo Min-Soo
	* 변경이력 :
	* @param ss
	* @param boardSeq
	* @return
	* Method 설명 : boardSeq에 해당하는 board객체 반환
	*/
	@Override
	public Board getBoard(SqlSession ss, String boardSeq) {
		return ss.selectOne("board.getBoard", boardSeq);
	}
	
	/**
	* Method : getBoardLocation
	* 작성자 : Jo Min-Soo
	* 변경이력 :
	* @param ss
	* @param boardLocation
	* @return
	* Method 설명 : boardLocation에 해당하는 board객체 반환
	*/
	@Override
	public Board getBoardLocation(SqlSession ss, int boardLocation) {
		return ss.selectOne("board.getBoardLocation", boardLocation);
	}

	/**
	* Method : getBoardList
	* 작성자 : Jo Min-Soo
	* 변경이력 :
	* @param ss
	* @return
	* Method 설명 : 전체 board객체 반환
	*/
	@Override
	public List<Board> getBoardList(SqlSession ss) {
		return ss.selectList("board.getBoardList");
	}

	/**
	* Method : insertBoard
	* 작성자 : Jo Min-Soo
	* 변경이력 :
	* @param ss
	* @return
	* Method 설명 : 게시판 추가
	*/
	@Override
	public int insertBoard(SqlSession ss, Board board) {
		return ss.insert("board.insertBoard", board);
	}

	/**
	* Method : updateBoard
	* 작성자 : Jo Min-Soo
	* 변경이력 :
	* @param ss
	* @return
	* Method 설명 : 게시판 수정
	*/
	@Override
	public int updateBoard(SqlSession ss, Board board) {
		return ss.update("board.updateBoard", board);
	}

}
