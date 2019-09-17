package kr.or.ddit.post.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.post.model.Post;

public interface IPostDao {

	/**
	* Method : getPostPagingList
	* 작성자 : Jo Min-Soo
	* 변경이력 :
	* @param ss
	* @param map
	* @return
	* Method 설명 : 게시글 페이징리스트 반환
	*/
	public List<Map> getPostPagingList(SqlSession ss, Map<String, Object> map);

	/**
	* Method : getPostTotalCnt
	* 작성자 : Jo Min-Soo
	* 변경이력 :
	* @param ss
	* @param boardSeq
	* @return
	* Method 설명 : 전체 게시글 갯수 반환
	*/
	public int getPostTotalCnt(SqlSession ss, String boardSeq);

	/**
	* Method : getPost
	* 작성자 : Jo Min-Soo
	* 변경이력 :
	* @param ss
	* @param postSeq
	* @return
	* Method 설명 : postSeq에 해당하는 post객체 반환
	*/
	public Post getPost(SqlSession ss, int postSeq);

	/**
	* Method : insertPost
	* 작성자 : Jo Min-Soo
	* 변경이력 :
	* @param ss
	* @param post
	* @return
	* Method 설명 : 게시글 추가
	*/
	public int insertPost(SqlSession ss, Post post);

	/**
	* Method : getPostNextSeq
	* 작성자 : Jo Min-Soo
	* 변경이력 :
	* @param ss
	* @return
	* Method 설명 : 게시글 Seq 생성
	*/
	public int getPostNextSeq(SqlSession ss);

	/**
	* Method : getPostGnNextSeq
	* 작성자 : Jo Min-Soo
	* 변경이력 :
	* @param ss
	* @return
	* Method 설명 : 게시글 Gn Seq 생성
	*/
	public int getPostGnNextSeq(SqlSession ss);

	/**
	* Method : deletePost
	* 작성자 : Jo Min-Soo
	* 변경이력 :
	* @param ss
	* @param postSeq
	* @return
	* Method 설명 : 게시글 삭제
	*/
	public int deletePost(SqlSession ss, int postSeq);

	/**
	* Method : updatePost
	* 작성자 : Jo Min-Soo
	* 변경이력 :
	* @param ss
	* @param post
	* @return
	* Method 설명 : 게시글 수정
	*/
	public int updatePost(SqlSession ss, Post post);
}
