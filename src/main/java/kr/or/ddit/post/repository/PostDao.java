package kr.or.ddit.post.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.post.model.Post;

public class PostDao implements IPostDao {

	/**
	* Method : getPostPagingList
	* 작성자 : Jo Min-Soo
	* 변경이력 :
	* @param ss
	* @param map
	* @return
	* Method 설명 : 게시글 페이징리스트 반환
	*/
	@Override
	public List<Map> getPostPagingList(SqlSession ss, Map<String, Object> map) {
		return ss.selectList("post.getPostPagingList", map);
	}

	/**
	* Method : getPostTotalCnt
	* 작성자 : Jo Min-Soo
	* 변경이력 :
	* @param ss
	* @param boardSeq
	* @return
	* Method 설명 : 전체 게시글 갯수 반환
	*/
	@Override
	public int getPostTotalCnt(SqlSession ss, String boardSeq) {
		return ss.selectOne("post.getPostTotalCnt", boardSeq);
	}

	/**
	* Method : getPost
	* 작성자 : Jo Min-Soo
	* 변경이력 :
	* @param ss
	* @param postSeq
	* @return
	* Method 설명 : postSeq에 해당하는 post객체 반환
	*/
	@Override
	public Post getPost(SqlSession ss, int postSeq) {
		return ss.selectOne("post.getPost", postSeq);
	}

	/**
	* Method : insertPost
	* 작성자 : Jo Min-Soo
	* 변경이력 :
	* @param ss
	* @param post
	* @return
	* Method 설명 : 게시글 추가
	*/
	@Override
	public int insertPost(SqlSession ss, Post post) {
		return ss.insert("post.insertPost", post);
	}

	/**
	* Method : getPostNextSeq
	* 작성자 : Jo Min-Soo
	* 변경이력 :
	* @param ss
	* @return
	* Method 설명 : 게시글 Seq 생성
	*/
	@Override
	public int getPostNextSeq(SqlSession ss) {
		return ss.selectOne("post.getPostNextSeq");
	}

	/**
	* Method : getPostGnNextSeq
	* 작성자 : Jo Min-Soo
	* 변경이력 :
	* @param ss
	* @return
	* Method 설명 : 게시글 Gn Seq 생성
	*/
	@Override
	public int getPostGnNextSeq(SqlSession ss) {
		return ss.selectOne("post.getPostGnNextSeq");
	}

	/**
	* Method : deletePost
	* 작성자 : Jo Min-Soo
	* 변경이력 :
	* @param ss
	* @param postSeq
	* @return
	* Method 설명 : 게시글 삭제
	*/
	@Override
	public int deletePost(SqlSession ss, int postSeq) {
		return ss.update("post.deletePost", postSeq);
	}

	/**
	* Method : updatePost
	* 작성자 : Jo Min-Soo
	* 변경이력 :
	* @param ss
	* @param post
	* @return
	* Method 설명 : 게시글 수정
	*/
	@Override
	public int updatePost(SqlSession ss, Post post) {
		return ss.update("post.updatePost", post);
	}

}
