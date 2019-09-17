package kr.or.ddit.reply.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.reply.model.Reply;
import kr.or.ddit.reply.repository.IReplyDao;
import kr.or.ddit.reply.repository.ReplyDao;
import kr.or.ddit.util.MybatisUtil;

public class ReplyService implements IReplyService {

	private IReplyDao replyDao;
	
	public ReplyService() {
		replyDao = new ReplyDao();
	}

	
	/**
	* Method : getReplyList
	* 작성자 : Jo Min-Soo
	* 변경이력 :
	* @param postSeq
	* @return
	* Method 설명 : postSeq에 해당하는 댓글리스트 반환
	*/
	@Override
	public List<Reply> getReplyList(int postSeq) {
		SqlSession ss = MybatisUtil.getSession();
		List<Reply> replyList = replyDao.getReplyList(ss, postSeq);
		ss.close();
		
		return replyList;
	}

	/**
	* Method : insertReply
	* 작성자 : Jo Min-Soo
	* 변경이력 :
	* @param reply
	* @return
	* Method 설명 : 댓글 추가
	*/
	@Override
	public int insertReply(Reply reply) {
		SqlSession ss = MybatisUtil.getSession();
		int insertCnt = replyDao.insertReply(ss, reply);
		
		ss.commit();
		ss.close();
		
		return insertCnt;
	}

	/**
	* Method : deleteReply
	* 작성자 : Jo Min-Soo
	* 변경이력 :
	* @param replySeq
	* @return
	* Method 설명 : 댓글 삭제
	*/
	@Override
	public int deleteReply(int replySeq) {
		SqlSession ss = MybatisUtil.getSession();
		int deleteCnt = replyDao.deleteReply(ss, replySeq);
		
		ss.commit();
		ss.close();
		
		return deleteCnt;
	}

}
