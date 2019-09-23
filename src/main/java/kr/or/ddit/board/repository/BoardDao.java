package kr.or.ddit.board.repository;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.board.model.Board;

@Repository
public class BoardDao implements IBoardDao {

	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSession;
	
	/**
	* Method : getBoard
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @param boardSeq
	* @return
	* Method 설명 : boardSeq에 해당하는 board객체 반환
	*/
	@Override
	public Board getBoard(int boardSeq) {
		return sqlSession.selectOne("board.getBoard", boardSeq);
	}
	
	/**
	* Method : getBoardLocation
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @param boardLocation
	* @return
	* Method 설명 : boardLocation에 해당하는 board객체 반환
	*/
	@Override
	public Board getBoardLocation(int boardLocation) {
		return sqlSession.selectOne("board.getBoardLocation", boardLocation);
	}

	/**
	* Method : getBoardList
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @return
	* Method 설명 : 전체 board객체 반환
	*/
	@Override
	public List<Board> getBoardList() {
		return sqlSession.selectList("board.getBoardList");
	}

	/**
	* Method : insertBoard
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @return
	* Method 설명 : 게시판 추가
	*/
	@Override
	public int insertBoard(Board board) {
		return sqlSession.insert("board.insertBoard", board);
	}

	/**
	* Method : updateBoard
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @return
	* Method 설명 : 게시판 수정
	*/
	@Override
	public int updateBoard(Board board) {
		return sqlSession.update("board.updateBoard", board);
	}

}
