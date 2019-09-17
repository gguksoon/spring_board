package kr.or.ddit.reply.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.reply.model.Reply;

public interface IReplyDao {

	/**
	* Method : getReplyList
	* 작성자 : Jo Min-Soo
	* 변경이력 :
	* @param ss
	* @param postSeq
	* @return
	* Method 설명 : postSeq에 해당하는 댓글리스트 반환
	*/
	public List<Reply> getReplyList(SqlSession ss, int postSeq);

	/**
	* Method : insertReply
	* 작성자 : Jo Min-Soo
	* 변경이력 :
	* @param ss
	* @param reply
	* @return
	* Method 설명 : 댓글 추가
	*/
	public int insertReply(SqlSession ss, Reply reply);

	/**
	* Method : deleteReply
	* 작성자 : Jo Min-Soo
	* 변경이력 :
	* @param ss
	* @param replySeq
	* @return
	* Method 설명 : 댓글 삭제
	*/
	public int deleteReply(SqlSession ss, int replySeq);

}
