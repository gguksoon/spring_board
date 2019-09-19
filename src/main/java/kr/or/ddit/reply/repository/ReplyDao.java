package kr.or.ddit.reply.repository;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.reply.model.Reply;

@Repository
public class ReplyDao implements IReplyDao {

	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSession;
	
	/**
	* Method : getReplyList
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @param ss
	* @param postSeq
	* @return
	* Method 설명 : postSeq에 해당하는 댓글리스트 반환
	*/
	@Override
	public List<Reply> getReplyList(int postSeq) {
		return sqlSession.selectList("reply.getReplyList", postSeq);
	}

	/**
	* Method : insertReply
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @param ss
	* @param reply
	* @return
	* Method 설명 : 댓글 추가
	*/
	@Override
	public int insertReply(Reply reply) {
		return sqlSession.insert("reply.insertReply", reply);
	}

	/**
	* Method : deleteReply
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @param ss
	* @param replySeq
	* @return
	* Method 설명 : 댓글 삭제
	*/
	@Override
	public int deleteReply(int replySeq) {
		return sqlSession.update("reply.deleteReply", replySeq);
	}

}
