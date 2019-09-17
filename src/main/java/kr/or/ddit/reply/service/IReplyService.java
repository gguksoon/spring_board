package kr.or.ddit.reply.service;

import java.util.List;

import kr.or.ddit.reply.model.Reply;

public interface IReplyService {

	/**
	* Method : getReplyList
	* 작성자 : Jo Min-Soo
	* 변경이력 :
	* @param postSeq
	* @return
	* Method 설명 : postSeq에 해당하는 댓글리스트 반환
	*/
	public List<Reply> getReplyList(int postSeq);
	
	/**
	* Method : insertReply
	* 작성자 : Jo Min-Soo
	* 변경이력 :
	* @param reply
	* @return
	* Method 설명 : 댓글 추가
	*/
	public int insertReply(Reply reply);

	/**
	* Method : deleteReply
	* 작성자 : Jo Min-Soo
	* 변경이력 :
	* @param replySeq
	* @return
	* Method 설명 : 댓글 삭제
	*/
	public int deleteReply(int replySeq);

}
