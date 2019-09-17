package kr.or.ddit.board.model;

public class Board {
	private int boardSeq;		// 게시판 번호
	private String boardNm;     // 게시판 이름
	private String userId;      // 관리자
	private int boardLocation;  // 게시판 순서
	private int boardStatus;    // 사용여부
	
	public Board() {
		super();
	}
	
	public Board(int boardSeq, String boardNm, String userId, int boardLocation, int boardStatus) {
		this.boardSeq = boardSeq;
		this.boardNm = boardNm;
		this.userId = userId;
		this.boardLocation = boardLocation;
		this.boardStatus = boardStatus;
	}

	public int getBoardSeq() {
		return boardSeq;
	}
	public void setBoardSeq(int boardSeq) {
		this.boardSeq = boardSeq;
	}
	public String getBoardNm() {
		return boardNm;
	}
	public void setBoardNm(String boardNm) {
		this.boardNm = boardNm;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getBoardLocation() {
		return boardLocation;
	}
	public void setBoardLocation(int boardLocation) {
		this.boardLocation = boardLocation;
	}
	public int getBoardStatus() {
		return boardStatus;
	}
	public void setBoardStatus(int boardStatus) {
		this.boardStatus = boardStatus;
	}
	
	@Override
	public String toString() {
		return "Board [boardSeq=" + boardSeq + ", boardNm=" + boardNm + ", userId=" + userId + ", boardLocation="
				+ boardLocation + ", boardStatus=" + boardStatus + "]";
	}
}
