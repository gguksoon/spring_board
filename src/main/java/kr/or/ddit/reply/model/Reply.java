package kr.or.ddit.reply.model;

import java.util.Date;

public class Reply {
	private int replySeq;		 // 댓글번호		
	private int postSeq;         // 게시글 번호
	private String userId;       // 사용자 아이디
	private String replyContent; // 내용
	private Date replyRegDate;   // 작성일시
	private Date replyModDate;   // 수정일시
	private int replyStatus;     // 삭제여부
	
	public Reply() { }
	
	public Reply(int replySeq, int postSeq, String userId, String replyContent, Date replyRegDate, Date replyModDate,
			int replyStatus) {
		this.replySeq = replySeq;
		this.postSeq = postSeq;
		this.userId = userId;
		this.replyContent = replyContent;
		this.replyRegDate = replyRegDate;
		this.replyModDate = replyModDate;
		this.replyStatus = replyStatus;
	}
	
	public int getReplySeq() {
		return replySeq;
	}
	public void setReplySeq(int replySeq) {
		this.replySeq = replySeq;
	}
	public int getPostSeq() {
		return postSeq;
	}
	public void setPostSeq(int postSeq) {
		this.postSeq = postSeq;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public Date getReplyRegDate() {
		return replyRegDate;
	}
	public void setReplyRegDate(Date replyRegDate) {
		this.replyRegDate = replyRegDate;
	}
	public Date getReplyModDate() {
		return replyModDate;
	}
	public void setReplyModDate(Date replyModDate) {
		this.replyModDate = replyModDate;
	}
	public int getReplyStatus() {
		return replyStatus;
	}
	public void setReplyStatus(int replyStatus) {
		this.replyStatus = replyStatus;
	}
	
	@Override
	public String toString() {
		return "Reply [replySeq=" + replySeq + ", postSeq=" + postSeq + ", userId=" + userId + ", replyContent="
				+ replyContent + ", replyRegdate=" + replyRegDate + ", replyModdate=" + replyModDate + ", replyStatus="
				+ replyStatus + "]";
	}
}
