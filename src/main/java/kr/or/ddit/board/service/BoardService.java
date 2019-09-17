package kr.or.ddit.board.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.board.model.Board;
import kr.or.ddit.board.repository.BoardDao;
import kr.or.ddit.board.repository.IBoardDao;
import kr.or.ddit.util.MybatisUtil;

public class BoardService implements IBoardService {

	private IBoardDao boardDao;
	
	public BoardService() {
		boardDao = new BoardDao();
	}
	
	/**
	* Method : getBoard
	* 작성자 : Jo Min-Soo
	* 변경이력 :
	* @param boardSeq
	* @return
	* Method 설명 : boardSeq에 해당하는 board객체 반환
	*/
	@Override
	public Board getBoard(String boardSeq) {
		SqlSession ss = MybatisUtil.getSession();
		Board board = boardDao.getBoard(ss, boardSeq);
		ss.close();
		
		return board;
	}

	/**
	* Method : getBoardList
	* 작성자 : Jo Min-Soo
	* 변경이력 :
	* @return
	* Method 설명 : 전체 board객체 반환
	*/
	@Override
	public List<Board> getBoardList() {
		SqlSession ss = MybatisUtil.getSession();
		List<Board> boardList = boardDao.getBoardList(ss);
		ss.close();
		
		return boardList;
	}

	/**
	* Method : insertBoard
	* 작성자 : Jo Min-Soo
	* 변경이력 :
	* @param board
	* @return
	* Method 설명 : 게시판 추가
	*/
	@Override
	public int insertBoard(Board board) {
		SqlSession ss = MybatisUtil.getSession();
		int insertCnt = boardDao.insertBoard(ss, board);
		
		ss.commit();
		ss.close();
		
		return insertCnt;
	}

	/**
	* Method : updateBoard
	* 작성자 : Jo Min-Soo
	* 변경이력 :
	* @param board
	* @return
	* Method 설명 : 게시판 수정
	*/
	@Override
	public int updateBoard(Board board) {
		SqlSession ss = MybatisUtil.getSession();
		int updateCnt = boardDao.updateBoard(ss, board);
		
		ss.commit();
		ss.close();
		
		return updateCnt;
	}

	/**
	* Method : locationChange
	* 작성자 : Jo Min-Soo
	* 변경이력 :
	* @param boardSeq
	* @param change
	* Method 설명 : boardSeq의 location을 change에 들어있는 값에따라 증가시키거나 감소시키는 메서드
	*/
	@Override
	public void locationChange(String boardSeq, String change) {
		SqlSession ss = MybatisUtil.getSession();
		Board board = boardDao.getBoard(ss, boardSeq); // 위치를 바꿀 board
		Board board2; // board와 위치가 교체될 board2
		int boardLocation = board.getBoardLocation();
		
		if(change.equals("up")) { // up
			board2 = boardDao.getBoardLocation(ss, boardLocation - 1);
			
			board.setBoardLocation(board.getBoardLocation() - 1);
			board2.setBoardLocation(board2.getBoardLocation() + 1);
			
			boardDao.updateBoard(ss, board);
			boardDao.updateBoard(ss, board2);
		} else { // down
			board2 = boardDao.getBoardLocation(ss, boardLocation + 1);
			
			board.setBoardLocation(board.getBoardLocation() + 1);
			board2.setBoardLocation(board2.getBoardLocation() - 1);
			
			boardDao.updateBoard(ss, board);
			boardDao.updateBoard(ss, board2);
		}
		
		ss.commit();
		ss.close();
	}

	
}
