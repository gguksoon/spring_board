package kr.or.ddit.reply.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.reply.model.Reply;
import kr.or.ddit.reply.repository.IReplyDao;

@Service
public class ReplyService implements IReplyService {

	@Resource(name = "replyDao")
	private IReplyDao replyDao;
	
	/**
	* Method : getReplyList
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @param postSeq
	* @return
	* Method 설명 : postSeq에 해당하는 댓글리스트 반환
	*/
	@Override
	public List<Reply> getReplyList(int postSeq) {
		return replyDao.getReplyList(postSeq);
	}

	/**
	* Method : insertReply
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @param reply
	* @return
	* Method 설명 : 댓글 추가
	*/
	@Override
	public int insertReply(Reply reply) {
		return replyDao.insertReply(reply);
	}

	/**
	* Method : deleteReply
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @param replySeq
	* @return
	* Method 설명 : 댓글 삭제
	*/
	@Override
	public int deleteReply(int replySeq) {
		return replyDao.deleteReply(replySeq);
	}

}
