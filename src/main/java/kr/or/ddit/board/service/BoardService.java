package kr.or.ddit.board.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.board.model.Board;
import kr.or.ddit.board.repository.IBoardDao;

@Service
public class BoardService implements IBoardService {

	@Resource(name="boardDao")
	private IBoardDao boardDao;
	
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
		return boardDao.getBoard(boardSeq);
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
		return boardDao.getBoardList();
	}

	/**
	* Method : insertBoard
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @param board
	* @return
	* Method 설명 : 게시판 추가
	*/
	@Override
	public int insertBoard(Board board) {
		return boardDao.insertBoard(board);
	}

	/**
	* Method : updateBoard
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @param board
	* @return
	* Method 설명 : 게시판 수정
	*/
	@Override
	public int updateBoard(Board board) {
		return boardDao.updateBoard(board);
	}

	/**
	* Method : locationChange
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @param boardSeq
	* @param change
	* Method 설명 : boardSeq의 location을 change에 들어있는 값에따라 증가시키거나 감소시키는 메서드
	*/
	@Override
	public void locationChange(int boardSeq, String change) {
		Board board = boardDao.getBoard(boardSeq); // 위치를 바꿀 board
		Board board2; // board와 위치가 교체될 board2
		int boardLocation = board.getBoardLocation();
		
		if(change.equals("up")) { // up
			board2 = boardDao.getBoardLocation(boardLocation - 1);
			
			board.setBoardLocation(board.getBoardLocation() - 1);
			board2.setBoardLocation(board2.getBoardLocation() + 1);
			
			boardDao.updateBoard(board);
			boardDao.updateBoard(board2);
		} else { // down
			board2 = boardDao.getBoardLocation(boardLocation + 1);
			
			board.setBoardLocation(board.getBoardLocation() + 1);
			board2.setBoardLocation(board2.getBoardLocation() - 1);
			
			boardDao.updateBoard(board);
			boardDao.updateBoard(board2);
		}
		
	}

	
}
